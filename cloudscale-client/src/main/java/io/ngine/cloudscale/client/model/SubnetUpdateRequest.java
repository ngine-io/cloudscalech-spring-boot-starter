package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to update a subnet. The API only allows one attribute per update.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class SubnetUpdateRequest {

    private @Nullable String gatewayAddress;

    private @Nullable List<String> dnsServers;

    private @Nullable Map<String, String> tags;

    public SubnetUpdateRequest() {
    }

    /**
     * The gateway address.
     */
    public SubnetUpdateRequest gatewayAddress(@Nullable String gatewayAddress) {
        this.gatewayAddress = gatewayAddress;
        return this;
    }

    /**
     * DNS resolver addresses, an empty list removes all resolvers.
     */
    public SubnetUpdateRequest dnsServers(@Nullable List<String> dnsServers) {
        this.dnsServers = dnsServers;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public SubnetUpdateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public SubnetUpdateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
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
