package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to update a network. The API only allows one attribute per update.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetworkUpdateRequest {

    private @Nullable String name;

    private @Nullable Integer mtu;

    private @Nullable Map<String, String> tags;

    public NetworkUpdateRequest() {
    }

    /**
     * The display name.
     */
    public NetworkUpdateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The MTU size.
     */
    public NetworkUpdateRequest mtu(@Nullable Integer mtu) {
        this.mtu = mtu;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public NetworkUpdateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public NetworkUpdateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable Integer getMtu() {
        return this.mtu;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
