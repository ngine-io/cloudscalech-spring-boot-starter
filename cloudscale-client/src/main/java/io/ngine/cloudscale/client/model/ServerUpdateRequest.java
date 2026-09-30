package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to update a server. The API only allows one attribute per update.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class ServerUpdateRequest {

    private @Nullable String name;

    private @Nullable String flavor;

    private @Nullable List<InterfaceSpec> interfaces;

    private @Nullable Map<String, String> tags;

    public ServerUpdateRequest() {
    }

    /**
     * The new name of the server.
     */
    public ServerUpdateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The slug of the new flavor, the server must be stopped.
     */
    public ServerUpdateRequest flavor(@Nullable String flavor) {
        this.flavor = flavor;
        return this;
    }

    /**
     * The new interfaces of the server.
     */
    public ServerUpdateRequest interfaces(@Nullable List<InterfaceSpec> interfaces) {
        this.interfaces = interfaces;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public ServerUpdateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public ServerUpdateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable String getFlavor() {
        return this.flavor;
    }

    public @Nullable List<InterfaceSpec> getInterfaces() {
        return this.interfaces;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
