package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to update a custom image. The API only allows one attribute per update.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class CustomImageUpdateRequest {

    private @Nullable String name;

    private @Nullable String slug;

    private @Nullable String userDataHandling;

    private @Nullable Map<String, String> tags;

    public CustomImageUpdateRequest() {
    }

    /**
     * The display name.
     */
    public CustomImageUpdateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The slug.
     */
    public CustomImageUpdateRequest slug(@Nullable String slug) {
        this.slug = slug;
        return this;
    }

    /**
     * {@code pass-through} or {@code extend-cloud-config}.
     */
    public CustomImageUpdateRequest userDataHandling(@Nullable String userDataHandling) {
        this.userDataHandling = userDataHandling;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public CustomImageUpdateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public CustomImageUpdateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable String getSlug() {
        return this.slug;
    }

    public @Nullable String getUserDataHandling() {
        return this.userDataHandling;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
