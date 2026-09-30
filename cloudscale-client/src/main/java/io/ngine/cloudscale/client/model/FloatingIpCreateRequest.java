package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to create a Floating IP.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class FloatingIpCreateRequest {

    private @Nullable Integer ipVersion;

    private @Nullable String server;

    private @Nullable String loadBalancer;

    private @Nullable String type;

    private @Nullable String region;

    private @Nullable String reversePtr;

    private @Nullable Integer prefixLength;

    private @Nullable Map<String, String> tags;

    public FloatingIpCreateRequest(Integer ipVersion) {
        this.ipVersion = ipVersion;
    }

    /**
     * 4 or 6.
     */
    public FloatingIpCreateRequest ipVersion(@Nullable Integer ipVersion) {
        this.ipVersion = ipVersion;
        return this;
    }

    /**
     * UUID of the destination server.
     */
    public FloatingIpCreateRequest server(@Nullable String server) {
        this.server = server;
        return this;
    }

    /**
     * UUID of the destination load balancer (single addresses only).
     */
    public FloatingIpCreateRequest loadBalancer(@Nullable String loadBalancer) {
        this.loadBalancer = loadBalancer;
        return this;
    }

    /**
     * {@code regional} or {@code global}.
     */
    public FloatingIpCreateRequest type(@Nullable String type) {
        this.type = type;
        return this;
    }

    /**
     * Slug of the region of a regional Floating IP.
     */
    public FloatingIpCreateRequest region(@Nullable String region) {
        this.region = region;
        return this;
    }

    /**
     * The reverse pointer.
     */
    public FloatingIpCreateRequest reversePtr(@Nullable String reversePtr) {
        this.reversePtr = reversePtr;
        return this;
    }

    /**
     * The prefix length, defaults to 32 (IPv4) or 128 (IPv6), 56 is supported for IPv6.
     */
    public FloatingIpCreateRequest prefixLength(@Nullable Integer prefixLength) {
        this.prefixLength = prefixLength;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public FloatingIpCreateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public FloatingIpCreateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable Integer getIpVersion() {
        return this.ipVersion;
    }

    public @Nullable String getServer() {
        return this.server;
    }

    public @Nullable String getLoadBalancer() {
        return this.loadBalancer;
    }

    public @Nullable String getType() {
        return this.type;
    }

    public @Nullable String getRegion() {
        return this.region;
    }

    public @Nullable String getReversePtr() {
        return this.reversePtr;
    }

    public @Nullable Integer getPrefixLength() {
        return this.prefixLength;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
