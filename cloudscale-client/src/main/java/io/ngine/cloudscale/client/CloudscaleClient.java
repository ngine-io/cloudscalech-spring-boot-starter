package io.ngine.cloudscale.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.Flavor;
import io.ngine.cloudscale.client.model.Image;
import io.ngine.cloudscale.client.model.Region;
import io.ngine.cloudscale.client.resource.CustomImageResource;
import io.ngine.cloudscale.client.resource.FloatingIpResource;
import io.ngine.cloudscale.client.resource.ListResource;
import io.ngine.cloudscale.client.resource.LoadBalancerHealthMonitorResource;
import io.ngine.cloudscale.client.resource.LoadBalancerListenerResource;
import io.ngine.cloudscale.client.resource.LoadBalancerPoolMemberResource;
import io.ngine.cloudscale.client.resource.LoadBalancerPoolResource;
import io.ngine.cloudscale.client.resource.LoadBalancerResource;
import io.ngine.cloudscale.client.resource.MetricsResource;
import io.ngine.cloudscale.client.resource.NetworkResource;
import io.ngine.cloudscale.client.resource.ObjectsUserKeyResource;
import io.ngine.cloudscale.client.resource.ObjectsUserResource;
import io.ngine.cloudscale.client.resource.ProjectLogResource;
import io.ngine.cloudscale.client.resource.RouterResource;
import io.ngine.cloudscale.client.resource.ServerGroupResource;
import io.ngine.cloudscale.client.resource.ServerResource;
import io.ngine.cloudscale.client.resource.SubnetResource;
import io.ngine.cloudscale.client.resource.VolumeResource;
import io.ngine.cloudscale.client.resource.VolumeSnapshotResource;
import org.jspecify.annotations.Nullable;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.web.client.RestClient;

/**
 * Client for the <a href="https://www.cloudscale.ch/en/api/v1">cloudscale.ch API</a>.
 * <p>
 * Each API resource type is available through an accessor, e.g. {@link #servers()}:
 * <pre class="code">
 * CloudscaleClient cloudscale = CloudscaleClient.builder().apiToken(token).build();
 * List&lt;Server&gt; servers = cloudscale.servers().list(TagFilter.of("env", "prod"));
 * </pre>
 * API errors are raised as {@link CloudscaleApiException}. Instances are thread-safe.
 */
public class CloudscaleClient {

    /** The default API base URL. */
    public static final String DEFAULT_API_URL = "https://api.cloudscale.ch/v1";

    private final ListResource<Region> regions;

    private final ListResource<Flavor> flavors;

    private final ListResource<Image> images;

    private final ServerResource servers;

    private final ServerGroupResource serverGroups;

    private final CustomImageResource customImages;

    private final VolumeResource volumes;

    private final VolumeSnapshotResource volumeSnapshots;

    private final FloatingIpResource floatingIps;

    private final NetworkResource networks;

    private final SubnetResource subnets;

    private final RouterResource routers;

    private final LoadBalancerResource loadBalancers;

    private final LoadBalancerPoolResource loadBalancerPools;

    private final LoadBalancerPoolMemberResource loadBalancerPoolMembers;

    private final LoadBalancerListenerResource loadBalancerListeners;

    private final LoadBalancerHealthMonitorResource loadBalancerHealthMonitors;

    private final ObjectsUserResource objectsUsers;

    private final ObjectsUserKeyResource objectsUserKeys;

    private final MetricsResource metrics;

    private final ProjectLogResource projectLogs;

    public CloudscaleClient(ApiTransport transport) {
        this.regions = new ListResource<>(transport, "/regions", Region.class);
        this.flavors = new ListResource<>(transport, "/flavors", Flavor.class);
        this.images = new ListResource<>(transport, "/images", Image.class);
        this.servers = new ServerResource(transport);
        this.serverGroups = new ServerGroupResource(transport);
        this.customImages = new CustomImageResource(transport);
        this.volumes = new VolumeResource(transport);
        this.volumeSnapshots = new VolumeSnapshotResource(transport);
        this.floatingIps = new FloatingIpResource(transport);
        this.networks = new NetworkResource(transport);
        this.subnets = new SubnetResource(transport);
        this.routers = new RouterResource(transport);
        this.loadBalancers = new LoadBalancerResource(transport);
        this.loadBalancerPools = new LoadBalancerPoolResource(transport);
        this.loadBalancerPoolMembers = new LoadBalancerPoolMemberResource(transport);
        this.loadBalancerListeners = new LoadBalancerListenerResource(transport);
        this.loadBalancerHealthMonitors = new LoadBalancerHealthMonitorResource(transport);
        this.objectsUsers = new ObjectsUserResource(transport);
        this.objectsUserKeys = new ObjectsUserKeyResource(transport);
        this.metrics = new MetricsResource(transport);
        this.projectLogs = new ProjectLogResource(transport);
    }

    public static Builder builder() {
        return new Builder();
    }

    public ListResource<Region> regions() {
        return regions;
    }

    public ListResource<Flavor> flavors() {
        return flavors;
    }

    public ListResource<Image> images() {
        return images;
    }

    public ServerResource servers() {
        return servers;
    }

    public ServerGroupResource serverGroups() {
        return serverGroups;
    }

    public CustomImageResource customImages() {
        return customImages;
    }

    public VolumeResource volumes() {
        return volumes;
    }

    public VolumeSnapshotResource volumeSnapshots() {
        return volumeSnapshots;
    }

    public FloatingIpResource floatingIps() {
        return floatingIps;
    }

    public NetworkResource networks() {
        return networks;
    }

    public SubnetResource subnets() {
        return subnets;
    }

    public RouterResource routers() {
        return routers;
    }

    public LoadBalancerResource loadBalancers() {
        return loadBalancers;
    }

    public LoadBalancerPoolResource loadBalancerPools() {
        return loadBalancerPools;
    }

    public LoadBalancerPoolMemberResource loadBalancerPoolMembers() {
        return loadBalancerPoolMembers;
    }

    public LoadBalancerListenerResource loadBalancerListeners() {
        return loadBalancerListeners;
    }

    public LoadBalancerHealthMonitorResource loadBalancerHealthMonitors() {
        return loadBalancerHealthMonitors;
    }

    public ObjectsUserResource objectsUsers() {
        return objectsUsers;
    }

    public ObjectsUserKeyResource objectsUserKeys() {
        return objectsUserKeys;
    }

    public MetricsResource metrics() {
        return metrics;
    }

    public ProjectLogResource projectLogs() {
        return projectLogs;
    }

    /** Builder for {@link CloudscaleClient}. */
    public static final class Builder {

        private @Nullable String apiToken;

        private String apiUrl = DEFAULT_API_URL;

        private @Nullable Duration connectTimeout;

        private @Nullable Duration readTimeout;

        private RestClient.@Nullable Builder restClientBuilder;

        private @Nullable ClientHttpRequestFactory requestFactory;

        private String userAgent = defaultUserAgent();

        private Builder() {
        }

        /** The API token, created in the cloudscale.ch control panel. Required. */
        public Builder apiToken(String apiToken) {
            this.apiToken = apiToken;
            return this;
        }

        /** The API base URL, defaults to {@value CloudscaleClient#DEFAULT_API_URL}. */
        public Builder apiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
            return this;
        }

        /** Connect timeout, defaults to 10 seconds unless a custom request factory is used. */
        public Builder connectTimeout(@Nullable Duration connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        /** Read timeout, defaults to 60 seconds unless a custom request factory is used. */
        public Builder readTimeout(@Nullable Duration readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        /**
         * A {@link RestClient.Builder} to start from, e.g. one provided by Spring Boot so
         * that observability and other customizations apply. The builder is cloned and not
         * modified. Its request factory is kept unless timeouts or a request factory are
         * set on this builder.
         */
        public Builder restClientBuilder(RestClient.Builder restClientBuilder) {
            this.restClientBuilder = restClientBuilder;
            return this;
        }

        /** A custom request factory, overrides the timeouts. */
        public Builder requestFactory(ClientHttpRequestFactory requestFactory) {
            this.requestFactory = requestFactory;
            return this;
        }

        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public CloudscaleClient build() {
            if (apiToken == null || apiToken.isBlank()) {
                throw new IllegalStateException("A cloudscale.ch API token is required");
            }
            String baseUrl = apiUrl.endsWith("/") ? apiUrl.substring(0, apiUrl.length() - 1) : apiUrl;
            JsonMapper mapper = CloudscaleJson.createMapper();

            RestClient.Builder builder = (restClientBuilder != null) ? restClientBuilder.clone() : RestClient.builder();
            ClientHttpRequestFactory factory = requestFactory();
            if (factory != null) {
                builder.requestFactory(factory);
            }
            RestClient restClient = builder.baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiToken)
                .defaultHeader(HttpHeaders.USER_AGENT, userAgent)
                .configureMessageConverters(
                        (converters) -> converters.withJsonConverter(new JacksonJsonHttpMessageConverter(mapper)))
                .defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
                    throw toException(response, mapper);
                })
                .build();
            return new CloudscaleClient(new ApiTransport(restClient, URI.create(baseUrl)));
        }

        private @Nullable ClientHttpRequestFactory requestFactory() {
            if (requestFactory != null) {
                return requestFactory;
            }
            boolean timeoutsSet = connectTimeout != null || readTimeout != null;
            if (restClientBuilder != null && !timeoutsSet) {
                return null;
            }
            HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Objects.requireNonNullElse(connectTimeout, Duration.ofSeconds(10)))
                .build();
            JdkClientHttpRequestFactory factory = new JdkClientHttpRequestFactory(httpClient);
            factory.setReadTimeout(Objects.requireNonNullElse(readTimeout, Duration.ofSeconds(60)));
            return factory;
        }

        private static CloudscaleApiException toException(ClientHttpResponse response, JsonMapper mapper)
                throws IOException {
            String body;
            try (InputStream in = response.getBody()) {
                body = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            }
            return new CloudscaleApiException(response.getStatusCode().value(), extractDetail(body, mapper), body);
        }

        private static @Nullable String extractDetail(String body, JsonMapper mapper) {
            try {
                Object parsed = mapper.readValue(body, Object.class);
                if (parsed instanceof Map<?, ?> map && map.get("detail") instanceof String detail) {
                    return detail;
                }
            }
            catch (JacksonException ex) {
                // not JSON, use the raw body
            }
            return null;
        }

        private static String defaultUserAgent() {
            String version = CloudscaleClient.class.getPackage().getImplementationVersion();
            return "cloudscale-java-client/" + ((version != null) ? version : "dev");
        }
    }
}
