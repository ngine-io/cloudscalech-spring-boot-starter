package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to update a router. The API only allows one attribute per update.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class RouterUpdateRequest {

    private @Nullable String name;

    private @Nullable Boolean internetGateway;

    private @Nullable Map<String, String> tags;

    public RouterUpdateRequest() {
    }

    /**
     * The display name.
     */
    public RouterUpdateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * Act as internet gateway (SNAT).
     */
    public RouterUpdateRequest internetGateway(@Nullable Boolean internetGateway) {
        this.internetGateway = internetGateway;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public RouterUpdateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public RouterUpdateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable Boolean getInternetGateway() {
        return this.internetGateway;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
