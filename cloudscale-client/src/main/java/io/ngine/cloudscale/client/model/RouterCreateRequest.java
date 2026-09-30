package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to create a router.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class RouterCreateRequest {

    private @Nullable String name;

    private @Nullable String zone;

    private @Nullable Boolean internetGateway;

    private @Nullable Map<String, String> tags;

    public RouterCreateRequest(String name) {
        this.name = name;
    }

    /**
     * The display name, an FQDN is used as reverse PTR of the gateway address.
     */
    public RouterCreateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The slug of the zone.
     */
    public RouterCreateRequest zone(@Nullable String zone) {
        this.zone = zone;
        return this;
    }

    /**
     * Act as internet gateway (SNAT).
     */
    public RouterCreateRequest internetGateway(@Nullable Boolean internetGateway) {
        this.internetGateway = internetGateway;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public RouterCreateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public RouterCreateRequest tag(String key, String value) {
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

    public @Nullable Boolean getInternetGateway() {
        return this.internetGateway;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
