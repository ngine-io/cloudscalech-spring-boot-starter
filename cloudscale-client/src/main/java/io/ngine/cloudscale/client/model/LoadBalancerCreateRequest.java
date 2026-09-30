package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to create a load balancer.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class LoadBalancerCreateRequest {

    private @Nullable String name;

    private @Nullable String flavor;

    private @Nullable String zone;

    private @Nullable List<AddressSpec> vipAddresses;

    private @Nullable Map<String, String> tags;

    public LoadBalancerCreateRequest(String name, String flavor) {
        this.name = name;
        this.flavor = flavor;
    }

    /**
     * The display name.
     */
    public LoadBalancerCreateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The slug of the flavor, e.g. {@code lb-standard}.
     */
    public LoadBalancerCreateRequest flavor(@Nullable String flavor) {
        this.flavor = flavor;
        return this;
    }

    /**
     * The slug of the zone.
     */
    public LoadBalancerCreateRequest zone(@Nullable String zone) {
        this.zone = zone;
        return this;
    }

    /**
     * VIP addresses on a private subnet, defaults to public IPv4 and IPv6 addresses.
     */
    public LoadBalancerCreateRequest vipAddresses(@Nullable List<AddressSpec> vipAddresses) {
        this.vipAddresses = vipAddresses;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public LoadBalancerCreateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public LoadBalancerCreateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable String getFlavor() {
        return this.flavor;
    }

    public @Nullable String getZone() {
        return this.zone;
    }

    public @Nullable List<AddressSpec> getVipAddresses() {
        return this.vipAddresses;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
