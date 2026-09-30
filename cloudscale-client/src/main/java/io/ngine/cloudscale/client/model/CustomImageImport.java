package io.ngine.cloudscale.client.model;

import java.util.Map;

import org.jspecify.annotations.Nullable;

/**
 * The status of a custom image import.
 *
 * @param href URL of the resource
 * @param uuid unique identifier of the import
 * @param customImage the image being imported, only valid once {@code status} is {@code success}
 * @param url URL the image is downloaded from
 * @param status one of {@code started}, {@code in_progress}, {@code failed}, {@code success}
 * @param errorMessage error message if the status is {@code failed}
 * @param tags tags assigned to the custom image
 */
public record CustomImageImport(String href, String uuid, @Nullable ResourceStub customImage, String url,
        String status, @Nullable String errorMessage, Map<String, String> tags) {

    public boolean isFinished() {
        return "success".equals(status) || "failed".equals(status);
    }
}
