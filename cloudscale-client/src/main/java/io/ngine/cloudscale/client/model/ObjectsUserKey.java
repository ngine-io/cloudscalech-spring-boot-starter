package io.ngine.cloudscale.client.model;

import org.jspecify.annotations.Nullable;

/**
 * An access/secret key pair of an objects user (S3-compatible object storage).
 *
 * @param href URL of the resource
 * @param accessKey the access key
 * @param secretKey the secret key, {@code null} when using a read-only token
 * @param objectsUser the objects user the key belongs to (not set when embedded in a user)
 */
public record ObjectsUserKey(String href, String accessKey, @Nullable String secretKey,
        @Nullable ObjectsUserStub objectsUser) {
}
