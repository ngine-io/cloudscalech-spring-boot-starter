package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to create a subnet.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class SubnetCreateRequest {

    private @Nullable String cidr;

    private @Nullable String network;

    private @Nullable String gatewayAddress;

    private @Nullable List<String> dnsServers;

    private @Nullable Map<String, String> tags;

    public SubnetCreateRequest(String cidr, String network) {
        this.cidr = cidr;
        this.network = network;
    }

    /**
     * The address range in CIDR notation, at least /24.
     */
    public SubnetCreateRequest cidr(@Nullable String cidr) {
        this.cidr = cidr;
        return this;
    }

    /**
     * The UUID of the network.
     */
    public SubnetCreateRequest network(@Nullable String network) {
        this.network = network;
        return this;
    }

    /**
     * The gateway address, outside of the DHCP range.
     */
    public SubnetCreateRequest gatewayAddress(@Nullable String gatewayAddress) {
        this.gatewayAddress = gatewayAddress;
        return this;
    }

    /**
     * DNS resolver addresses, an empty list removes all resolvers.
     */
    public SubnetCreateRequest dnsServers(@Nullable List<String> dnsServers) {
        this.dnsServers = dnsServers;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public SubnetCreateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public SubnetCreateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getCidr() {
        return this.cidr;
    }

    public @Nullable String getNetwork() {
        return this.network;
    }

    public @Nullable String getGatewayAddress() {
        return this.gatewayAddress;
    }

    public @Nullable List<String> getDnsServers() {
        return this.dnsServers;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
