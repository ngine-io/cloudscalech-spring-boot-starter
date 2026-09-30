package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to create a private network.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetworkCreateRequest {

    private @Nullable String name;

    private @Nullable String zone;

    private @Nullable Integer mtu;

    private @Nullable Boolean autoCreateIpv4Subnet;

    private @Nullable Map<String, String> tags;

    public NetworkCreateRequest(String name) {
        this.name = name;
    }

    /**
     * The display name.
     */
    public NetworkCreateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The slug of the zone.
     */
    public NetworkCreateRequest zone(@Nullable String zone) {
        this.zone = zone;
        return this;
    }

    /**
     * The MTU size.
     */
    public NetworkCreateRequest mtu(@Nullable Integer mtu) {
        this.mtu = mtu;
        return this;
    }

    /**
     * Automatically create an IPv4 subnet.
     */
    public NetworkCreateRequest autoCreateIpv4Subnet(@Nullable Boolean autoCreateIpv4Subnet) {
        this.autoCreateIpv4Subnet = autoCreateIpv4Subnet;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public NetworkCreateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public NetworkCreateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable String getZone() {
        return this.zone;
    }

    public @Nullable Integer getMtu() {
        return this.mtu;
    }

    public @Nullable Boolean getAutoCreateIpv4Subnet() {
        return this.autoCreateIpv4Subnet;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
