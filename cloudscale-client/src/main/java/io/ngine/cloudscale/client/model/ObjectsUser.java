package io.ngine.cloudscale.client.model;

import java.util.List;
import java.util.Map;

/**
 * A user of the S3-compatible object storage.
 *
 * @param href URL of the resource
 * @param id unique identifier (not a UUID)
 * @param displayName display name
 * @param keys access and secret keys of the user
 * @param tags tags assigned to the objects user
 */
public record ObjectsUser(String href, String id, String displayName, List<ObjectsUserKey> keys,
        Map<String, String> tags) {
}
