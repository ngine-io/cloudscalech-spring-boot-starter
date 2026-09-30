package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to update an objects user. The API only allows one attribute per update.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class ObjectsUserUpdateRequest {

    private @Nullable String displayName;

    private @Nullable Map<String, String> tags;

    public ObjectsUserUpdateRequest() {
    }

    /**
     * The display name.
     */
    public ObjectsUserUpdateRequest displayName(@Nullable String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public ObjectsUserUpdateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public ObjectsUserUpdateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getDisplayName() {
        return this.displayName;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
