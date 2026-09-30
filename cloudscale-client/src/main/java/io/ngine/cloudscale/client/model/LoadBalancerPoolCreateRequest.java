package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to create a load balancer pool.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class LoadBalancerPoolCreateRequest {

    private @Nullable String name;

    private @Nullable String loadBalancer;

    private @Nullable String algorithm;

    private @Nullable String protocol;

    private @Nullable Map<String, String> tags;

    public LoadBalancerPoolCreateRequest(String name, String loadBalancer, String algorithm, String protocol) {
        this.name = name;
        this.loadBalancer = loadBalancer;
        this.algorithm = algorithm;
        this.protocol = protocol;
    }

    /**
     * The display name.
     */
    public LoadBalancerPoolCreateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The UUID of the load balancer.
     */
    public LoadBalancerPoolCreateRequest loadBalancer(@Nullable String loadBalancer) {
        this.loadBalancer = loadBalancer;
        return this;
    }

    /**
     * {@code round_robin}, {@code least_connections} or {@code source_ip}.
     */
    public LoadBalancerPoolCreateRequest algorithm(@Nullable String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    /**
     * {@code tcp}, {@code proxy}, {@code proxyv2} or {@code udp}.
     */
    public LoadBalancerPoolCreateRequest protocol(@Nullable String protocol) {
        this.protocol = protocol;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public LoadBalancerPoolCreateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public LoadBalancerPoolCreateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable String getLoadBalancer() {
        return this.loadBalancer;
    }

    public @Nullable String getAlgorithm() {
        return this.algorithm;
    }

    public @Nullable String getProtocol() {
        return this.protocol;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
