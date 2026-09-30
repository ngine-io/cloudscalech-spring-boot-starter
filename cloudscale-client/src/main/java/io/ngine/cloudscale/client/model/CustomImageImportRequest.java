package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to import a custom image from a URL.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class CustomImageImportRequest {

    private @Nullable String url;

    private @Nullable String name;

    private @Nullable String slug;

    private @Nullable String userDataHandling;

    private @Nullable List<String> zones;

    private @Nullable String sourceFormat;

    private @Nullable String firmwareType;

    private @Nullable Map<String, String> tags;

    public CustomImageImportRequest(String url, String name) {
        this.url = url;
        this.name = name;
    }

    /**
     * The URL to download the image from.
     */
    public CustomImageImportRequest url(@Nullable String url) {
        this.url = url;
        return this;
    }

    /**
     * The display name.
     */
    public CustomImageImportRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The slug, use {@code custom:<slug>} when creating servers.
     */
    public CustomImageImportRequest slug(@Nullable String slug) {
        this.slug = slug;
        return this;
    }

    /**
     * {@code pass-through} or {@code extend-cloud-config}.
     */
    public CustomImageImportRequest userDataHandling(@Nullable String userDataHandling) {
        this.userDataHandling = userDataHandling;
        return this;
    }

    /**
     * Slugs of the zones in which the image will be available.
     */
    public CustomImageImportRequest zones(@Nullable List<String> zones) {
        this.zones = zones;
        return this;
    }

    /**
     * {@code raw}, {@code qcow2} or {@code iso}.
     */
    public CustomImageImportRequest sourceFormat(@Nullable String sourceFormat) {
        this.sourceFormat = sourceFormat;
        return this;
    }

    /**
     * {@code bios} or {@code uefi}.
     */
    public CustomImageImportRequest firmwareType(@Nullable String firmwareType) {
        this.firmwareType = firmwareType;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public CustomImageImportRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public CustomImageImportRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getUrl() {
        return this.url;
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

    public @Nullable List<String> getZones() {
        return this.zones;
    }

    public @Nullable String getSourceFormat() {
        return this.sourceFormat;
    }

    public @Nullable String getFirmwareType() {
        return this.firmwareType;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
