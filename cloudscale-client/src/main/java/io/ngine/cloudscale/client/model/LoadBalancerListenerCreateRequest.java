package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to create a load balancer listener.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class LoadBalancerListenerCreateRequest {

    private @Nullable String name;

    private @Nullable String pool;

    private @Nullable String protocol;

    private @Nullable Integer protocolPort;

    private @Nullable List<String> allowedCidrs;

    private @Nullable Integer timeoutClientDataMs;

    private @Nullable Integer timeoutMemberConnectMs;

    private @Nullable Integer timeoutMemberDataMs;

    private @Nullable Map<String, String> tags;

    public LoadBalancerListenerCreateRequest(String name, String pool, String protocol, Integer protocolPort) {
        this.name = name;
        this.pool = pool;
        this.protocol = protocol;
        this.protocolPort = protocolPort;
    }

    /**
     * The display name.
     */
    public LoadBalancerListenerCreateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The UUID of the pool.
     */
    public LoadBalancerListenerCreateRequest pool(@Nullable String pool) {
        this.pool = pool;
        return this;
    }

    /**
     * {@code tcp} or {@code udp}.
     */
    public LoadBalancerListenerCreateRequest protocol(@Nullable String protocol) {
        this.protocol = protocol;
        return this;
    }

    /**
     * The port traffic is received on.
     */
    public LoadBalancerListenerCreateRequest protocolPort(@Nullable Integer protocolPort) {
        this.protocolPort = protocolPort;
        return this;
    }

    /**
     * Allowed source ranges, empty allows any source.
     */
    public LoadBalancerListenerCreateRequest allowedCidrs(@Nullable List<String> allowedCidrs) {
        this.allowedCidrs = allowedCidrs;
        return this;
    }

    /**
     * Client inactivity timeout in milliseconds.
     */
    public LoadBalancerListenerCreateRequest timeoutClientDataMs(@Nullable Integer timeoutClientDataMs) {
        this.timeoutClientDataMs = timeoutClientDataMs;
        return this;
    }

    /**
     * Pool member connect timeout in milliseconds.
     */
    public LoadBalancerListenerCreateRequest timeoutMemberConnectMs(@Nullable Integer timeoutMemberConnectMs) {
        this.timeoutMemberConnectMs = timeoutMemberConnectMs;
        return this;
    }

    /**
     * Pool member inactivity timeout in milliseconds.
     */
    public LoadBalancerListenerCreateRequest timeoutMemberDataMs(@Nullable Integer timeoutMemberDataMs) {
        this.timeoutMemberDataMs = timeoutMemberDataMs;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public LoadBalancerListenerCreateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public LoadBalancerListenerCreateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable String getPool() {
        return this.pool;
    }

    public @Nullable String getProtocol() {
        return this.protocol;
    }

    public @Nullable Integer getProtocolPort() {
        return this.protocolPort;
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
