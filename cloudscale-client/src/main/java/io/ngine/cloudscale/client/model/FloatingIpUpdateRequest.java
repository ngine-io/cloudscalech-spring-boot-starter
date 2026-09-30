package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to update a Floating IP. The API only allows one attribute per update.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class FloatingIpUpdateRequest {

    private @Nullable String server;

    private @Nullable String loadBalancer;

    private @Nullable String reversePtr;

    private @Nullable Map<String, String> tags;

    public FloatingIpUpdateRequest() {
    }

    /**
     * UUID of the new destination server.
     */
    public FloatingIpUpdateRequest server(@Nullable String server) {
        this.server = server;
        return this;
    }

    /**
     * UUID of the new destination load balancer.
     */
    public FloatingIpUpdateRequest loadBalancer(@Nullable String loadBalancer) {
        this.loadBalancer = loadBalancer;
        return this;
    }

    /**
     * The reverse pointer.
     */
    public FloatingIpUpdateRequest reversePtr(@Nullable String reversePtr) {
        this.reversePtr = reversePtr;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public FloatingIpUpdateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public FloatingIpUpdateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getServer() {
        return this.server;
    }

    public @Nullable String getLoadBalancer() {
        return this.loadBalancer;
    }

    public @Nullable String getReversePtr() {
        return this.reversePtr;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
