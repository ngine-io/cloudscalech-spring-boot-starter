package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to create a volume. To create a volume from a snapshot, only set {@code name} and {@code volumeSnapshotUuid}.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class VolumeCreateRequest {

    private @Nullable String name;

    private @Nullable Integer sizeGb;

    private @Nullable String type;

    private @Nullable List<String> servers;

    private @Nullable String zone;

    private @Nullable String volumeSnapshotUuid;

    private @Nullable Map<String, String> tags;

    public VolumeCreateRequest(String name) {
        this.name = name;
    }

    /**
     * The display name.
     */
    public VolumeCreateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The size in GiB.
     */
    public VolumeCreateRequest sizeGb(@Nullable Integer sizeGb) {
        this.sizeGb = sizeGb;
        return this;
    }

    /**
     * {@code ssd} or {@code bulk}.
     */
    public VolumeCreateRequest type(@Nullable String type) {
        this.type = type;
        return this;
    }

    /**
     * UUIDs of the servers to attach the volume to (currently at most one).
     */
    public VolumeCreateRequest servers(@Nullable List<String> servers) {
        this.servers = servers;
        return this;
    }

    /**
     * The slug of the zone.
     */
    public VolumeCreateRequest zone(@Nullable String zone) {
        this.zone = zone;
        return this;
    }

    /**
     * The snapshot to create the volume from.
     */
    public VolumeCreateRequest volumeSnapshotUuid(@Nullable String volumeSnapshotUuid) {
        this.volumeSnapshotUuid = volumeSnapshotUuid;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public VolumeCreateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public VolumeCreateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable Integer getSizeGb() {
        return this.sizeGb;
    }

    public @Nullable String getType() {
        return this.type;
    }

    public @Nullable List<String> getServers() {
        return this.servers;
    }

    public @Nullable String getZone() {
        return this.zone;
    }

    public @Nullable String getVolumeSnapshotUuid() {
        return this.volumeSnapshotUuid;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
