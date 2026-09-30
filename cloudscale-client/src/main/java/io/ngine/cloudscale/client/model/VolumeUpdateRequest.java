package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to update a volume. The API only allows one attribute per update. Pass an empty list of servers to detach the volume.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class VolumeUpdateRequest {

    private @Nullable String name;

    private @Nullable Integer sizeGb;

    private @Nullable List<String> servers;

    private @Nullable Map<String, String> tags;

    public VolumeUpdateRequest() {
    }

    /**
     * The display name.
     */
    public VolumeUpdateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The new size in GiB.
     */
    public VolumeUpdateRequest sizeGb(@Nullable Integer sizeGb) {
        this.sizeGb = sizeGb;
        return this;
    }

    /**
     * UUIDs of the servers to attach the volume to, empty to detach.
     */
    public VolumeUpdateRequest servers(@Nullable List<String> servers) {
        this.servers = servers;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public VolumeUpdateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public VolumeUpdateRequest tag(String key, String value) {
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

    public @Nullable List<String> getServers() {
        return this.servers;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
