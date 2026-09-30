package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to create a server group.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class ServerGroupCreateRequest {

    private @Nullable String name;

    private @Nullable String type;

    private @Nullable String zone;

    private @Nullable Map<String, String> tags;

    public ServerGroupCreateRequest(String name) {
        this.name = name;
    }

    /**
     * The display name.
     */
    public ServerGroupCreateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The type, currently only {@code anti-affinity}.
     */
    public ServerGroupCreateRequest type(@Nullable String type) {
        this.type = type;
        return this;
    }

    /**
     * The slug of the zone.
     */
    public ServerGroupCreateRequest zone(@Nullable String zone) {
        this.zone = zone;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public ServerGroupCreateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public ServerGroupCreateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable String getType() {
        return this.type;
    }

    public @Nullable String getZone() {
        return this.zone;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
