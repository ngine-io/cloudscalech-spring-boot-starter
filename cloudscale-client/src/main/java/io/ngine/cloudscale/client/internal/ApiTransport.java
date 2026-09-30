package io.ngine.cloudscale.client.internal;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import io.ngine.cloudscale.client.TagFilter;
import org.jspecify.annotations.Nullable;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriBuilder;

/**
 * Thin HTTP layer translating resource calls into API requests.
 * <p>
 * Paths are URI templates relative to the API base URL, e.g. {@code /servers/{uuid}}.
 * Template variables are always encoded, so identifiers and query values are safe to pass
 * as-is. This class is internal API and may change without notice.
 */
public final class ApiTransport {

    private static final MultiValueMap<String, String> NO_PARAMS = new LinkedMultiValueMap<>();

    private final RestClient restClient;

    private final URI baseUri;

    /**
     * @param restClient client configured with the API base URL, authentication and error handling
     * @param baseUri the API base URL, absolute URLs are only followed if they point to the same origin
     */
    public ApiTransport(RestClient restClient, URI baseUri) {
        this.restClient = restClient;
        this.baseUri = baseUri;
    }

    public <T> List<T> list(String path, Class<T> type, @Nullable TagFilter filter, Object... uriVariables) {
        return list(path, type, filter, NO_PARAMS, uriVariables);
    }

    public <T> List<T> list(String path, Class<T> type, @Nullable TagFilter filter,
            MultiValueMap<String, String> queryParams, Object... uriVariables) {
        List<T> result = restClient.get()
            .uri(builder -> buildUri(builder, path, filter, queryParams, uriVariables))
            .retrieve()
            .body(listOf(type));
        return (result != null) ? result : List.of();
    }

    public <T> T get(String path, Class<T> type, Object... uriVariables) {
        return requireBody(restClient.get().uri(path, uriVariables).retrieve().body(type));
    }

    public <T> T get(String path, Class<T> type, MultiValueMap<String, String> queryParams,
            Object... uriVariables) {
        return requireBody(restClient.get()
            .uri(builder -> buildUri(builder, path, null, queryParams, uriVariables))
            .retrieve()
            .body(type));
    }

    /** Fetches an absolute URL as returned by the API, e.g. a pagination link. */
    public <T> T get(URI uri, Class<T> type) {
        if (!sameOrigin(uri)) {
            throw new IllegalArgumentException("Refusing to send API credentials to " + uri.getHost()
                    + ", URL does not belong to " + baseUri);
        }
        return requireBody(restClient.get().uri(uri).retrieve().body(type));
    }

    public <T> T post(String path, Object body, Class<T> type, Object... uriVariables) {
        return requireBody(restClient.post().uri(path, uriVariables).body(body).retrieve().body(type));
    }

    /** Sends a POST without expecting a response body, e.g. to trigger an action. */
    public void post(String path, @Nullable Object body, Object... uriVariables) {
        RestClient.RequestBodySpec spec = restClient.post().uri(path, uriVariables);
        if (body != null) {
            spec.body(body);
        }
        spec.retrieve().toBodilessEntity();
    }

    public void patch(String path, Object body, Object... uriVariables) {
        restClient.patch().uri(path, uriVariables).body(body).retrieve().toBodilessEntity();
    }

    public void delete(String path, Object... uriVariables) {
        restClient.delete().uri(path, uriVariables).retrieve().toBodilessEntity();
    }

    private static URI buildUri(UriBuilder builder, String path, @Nullable TagFilter filter,
            MultiValueMap<String, String> queryParams, Object[] uriVariables) {
        builder.path(path);
        // Query values are passed as URI variables so that they get fully encoded
        // (e.g. '+' in timestamps), the API treats a literal '+' as a space.
        int valueCount = queryParams.values().stream().mapToInt(List::size).sum();
        Object[] variables = new Object[uriVariables.length + valueCount + 1];
        System.arraycopy(uriVariables, 0, variables, 0, uriVariables.length);
        int index = uriVariables.length;
        for (var param : queryParams.entrySet()) {
            for (String value : param.getValue()) {
                builder.queryParam(param.getKey(), "{qv" + index + "}");
                variables[index++] = value;
            }
        }
        if (filter != null) {
            if (filter.value() == null) {
                builder.queryParam(filter.parameterName());
            }
            else {
                builder.queryParam(filter.parameterName(), "{qv" + index + "}");
                variables[index++] = filter.value();
            }
        }
        return builder.build(Arrays.copyOf(variables, index));
    }

    private boolean sameOrigin(URI uri) {
        return Objects.equals(baseUri.getScheme(), uri.getScheme()) && Objects.equals(baseUri.getHost(), uri.getHost())
                && baseUri.getPort() == uri.getPort();
    }

    private static <T> ParameterizedTypeReference<List<T>> listOf(Class<T> type) {
        return ParameterizedTypeReference.forType(ResolvableType.forClassWithGenerics(List.class, type).getType());
    }

    private static <T> T requireBody(@Nullable T body) {
        if (body == null) {
            throw new IllegalStateException("Expected a response body from the cloudscale.ch API but got none");
        }
        return body;
    }
}
