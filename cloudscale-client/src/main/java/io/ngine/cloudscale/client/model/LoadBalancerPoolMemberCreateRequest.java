package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to create a load balancer pool member.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class LoadBalancerPoolMemberCreateRequest {

    private @Nullable String name;

    private @Nullable String subnet;

    private @Nullable String address;

    private @Nullable Integer protocolPort;

    private @Nullable Integer monitorPort;

    private @Nullable Boolean enabled;

    private @Nullable Map<String, String> tags;

    public LoadBalancerPoolMemberCreateRequest(String name, String subnet, String address, Integer protocolPort) {
        this.name = name;
        this.subnet = subnet;
        this.address = address;
        this.protocolPort = protocolPort;
    }

    /**
     * The display name.
     */
    public LoadBalancerPoolMemberCreateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The UUID of the subnet of the address.
     */
    public LoadBalancerPoolMemberCreateRequest subnet(@Nullable String subnet) {
        this.subnet = subnet;
        return this;
    }

    /**
     * The IP address traffic is sent to.
     */
    public LoadBalancerPoolMemberCreateRequest address(@Nullable String address) {
        this.address = address;
        return this;
    }

    /**
     * The port traffic is sent to.
     */
    public LoadBalancerPoolMemberCreateRequest protocolPort(@Nullable Integer protocolPort) {
        this.protocolPort = protocolPort;
        return this;
    }

    /**
     * The port health checks are sent to.
     */
    public LoadBalancerPoolMemberCreateRequest monitorPort(@Nullable Integer monitorPort) {
        this.monitorPort = monitorPort;
        return this;
    }

    /**
     * Whether the member receives traffic.
     */
    public LoadBalancerPoolMemberCreateRequest enabled(@Nullable Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public LoadBalancerPoolMemberCreateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public LoadBalancerPoolMemberCreateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable String getSubnet() {
        return this.subnet;
    }

    public @Nullable String getAddress() {
        return this.address;
    }

    public @Nullable Integer getProtocolPort() {
        return this.protocolPort;
    }

    public @Nullable Integer getMonitorPort() {
        return this.monitorPort;
    }

    public @Nullable Boolean getEnabled() {
        return this.enabled;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
