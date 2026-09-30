package io.ngine.cloudscale.client.model;

import java.util.List;

import org.jspecify.annotations.Nullable;

/**
 * Advanced options for health monitors of type {@code http} or {@code https}. Used in
 * requests and responses; {@code null} values are omitted from requests so the API
 * defaults apply.
 *
 * @param expectedCodes accepted status codes, e.g. {@code ["200", "202"]} or {@code ["200-204"]}
 * @param method HTTP method, default {@code GET}
 * @param urlPath URL path, default {@code /}
 * @param version {@code 1.0} or {@code 1.1}, default {@code 1.1}
 * @param host value of the {@code Host} header, requires version {@code 1.1}
 */
public record HealthMonitorHttp(@Nullable List<String> expectedCodes, @Nullable String method,
        @Nullable String urlPath, @Nullable String version, @Nullable String host) {
}
