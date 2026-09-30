package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to update a load balancer listener. The API only allows one attribute per update.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class LoadBalancerListenerUpdateRequest {

    private @Nullable String name;

    private @Nullable List<String> allowedCidrs;

    private @Nullable Integer timeoutClientDataMs;

    private @Nullable Integer timeoutMemberConnectMs;

    private @Nullable Integer timeoutMemberDataMs;

    private @Nullable Map<String, String> tags;

    public LoadBalancerListenerUpdateRequest() {
    }

    /**
     * The display name.
     */
    public LoadBalancerListenerUpdateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * Allowed source ranges, empty allows any source.
     */
    public LoadBalancerListenerUpdateRequest allowedCidrs(@Nullable List<String> allowedCidrs) {
        this.allowedCidrs = allowedCidrs;
        return this;
    }

    /**
     * Client inactivity timeout in milliseconds.
     */
    public LoadBalancerListenerUpdateRequest timeoutClientDataMs(@Nullable Integer timeoutClientDataMs) {
        this.timeoutClientDataMs = timeoutClientDataMs;
        return this;
    }

    /**
     * Pool member connect timeout in milliseconds.
     */
    public LoadBalancerListenerUpdateRequest timeoutMemberConnectMs(@Nullable Integer timeoutMemberConnectMs) {
        this.timeoutMemberConnectMs = timeoutMemberConnectMs;
        return this;
    }

    /**
     * Pool member inactivity timeout in milliseconds.
     */
    public LoadBalancerListenerUpdateRequest timeoutMemberDataMs(@Nullable Integer timeoutMemberDataMs) {
        this.timeoutMemberDataMs = timeoutMemberDataMs;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public LoadBalancerListenerUpdateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public LoadBalancerListenerUpdateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable List<String> getAllowedCidrs() {
        return this.allowedCidrs;
    }

    public @Nullable Integer getTimeoutClientDataMs() {
        return this.timeoutClientDataMs;
    }

    public @Nullable Integer getTimeoutMemberConnectMs() {
        return this.timeoutMemberConnectMs;
    }

    public @Nullable Integer getTimeoutMemberDataMs() {
        return this.timeoutMemberDataMs;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
