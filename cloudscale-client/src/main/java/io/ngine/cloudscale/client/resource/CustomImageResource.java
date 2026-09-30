package io.ngine.cloudscale.client.resource;

import java.util.List;
import java.util.Map;

import io.ngine.cloudscale.client.TagFilter;
import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.CustomImage;
import io.ngine.cloudscale.client.model.CustomImageImport;
import io.ngine.cloudscale.client.model.CustomImageImportRequest;
import io.ngine.cloudscale.client.model.CustomImageUpdateRequest;

/**
 * Custom images, see <a href="https://www.cloudscale.ch/en/api/v1#custom-images">API docs</a>.
 * Custom images cannot be created directly, use {@link #importImage} instead.
 */
public class CustomImageResource extends ReadResource<CustomImage> {

    private static final String IMPORT_PATH = "/custom-images/import";

    public CustomImageResource(ApiTransport transport) {
        super(transport, "/custom-images", CustomImage.class);
    }

    /** Updates a custom image. The API only allows one attribute per update. */
    public void update(String uuid, CustomImageUpdateRequest request) {
        transport.patch(path + "/{id}", request, uuid);
    }

    /** Updates a custom image with a raw payload. */
    public void update(String uuid, Map<String, ?> payload) {
        transport.patch(path + "/{id}", payload, uuid);
    }

    public void delete(String uuid) {
        transport.delete(path + "/{id}", uuid);
    }

    /** Starts downloading and importing a custom image from a URL. */
    public CustomImageImport importImage(CustomImageImportRequest request) {
        return transport.post(IMPORT_PATH, request, CustomImageImport.class);
    }

    /** Lists the custom image imports of the last 7 days. */
    public List<CustomImageImport> listImports() {
        return transport.list(IMPORT_PATH, CustomImageImport.class, null);
    }

    /** Lists the custom image imports of the last 7 days matching the given tag filter. */
    public List<CustomImageImport> listImports(TagFilter filter) {
        return transport.list(IMPORT_PATH, CustomImageImport.class, filter);
    }

    /** Gets the status of a custom image import. */
    public CustomImageImport getImport(String uuid) {
        return transport.get(IMPORT_PATH + "/{id}", CustomImageImport.class, uuid);
    }
}
