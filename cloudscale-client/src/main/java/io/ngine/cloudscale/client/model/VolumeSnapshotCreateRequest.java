package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to create a volume snapshot.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class VolumeSnapshotCreateRequest {

    private @Nullable String name;

    private @Nullable String sourceVolume;

    private @Nullable Map<String, String> tags;

    public VolumeSnapshotCreateRequest(String name, String sourceVolume) {
        this.name = name;
        this.sourceVolume = sourceVolume;
    }

    /**
     * The display name.
     */
    public VolumeSnapshotCreateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The UUID of the volume to snapshot.
     */
    public VolumeSnapshotCreateRequest sourceVolume(@Nullable String sourceVolume) {
        this.sourceVolume = sourceVolume;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public VolumeSnapshotCreateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public VolumeSnapshotCreateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable String getSourceVolume() {
        return this.sourceVolume;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
