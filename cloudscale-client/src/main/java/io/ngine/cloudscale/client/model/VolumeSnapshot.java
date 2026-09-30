package io.ngine.cloudscale.client.model;

import java.time.OffsetDateTime;
import java.util.Map;

/**
 * A snapshot of a volume.
 *
 * @param href URL of the resource
 * @param uuid unique identifier
 * @param name display name
 * @param createdAt creation date and time
 * @param sizeGb size in GiB (the source volume's size when the snapshot was taken)
 * @param sourceVolume the volume the snapshot belongs to
 * @param status one of {@code available}, {@code deleting}, {@code reverting}, {@code unknown}
 * @param tags tags assigned to the snapshot
 */
public record VolumeSnapshot(String href, String uuid, String name, OffsetDateTime createdAt, Integer sizeGb,
        ResourceStub sourceVolume, String status, Map<String, String> tags) {
}
