package io.ngine.cloudscale.client.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;

/**
 * A block storage volume.
 *
 * @param href URL of the resource
 * @param uuid unique identifier
 * @param name display name
 * @param createdAt creation date and time
 * @param sizeGb size in GiB
 * @param type {@code ssd} or {@code bulk}
 * @param servers servers the volume is attached to
 * @param serverUuids deprecated, use {@code servers}
 * @param currentOperation ongoing operation such as {@code snapshotting}, or {@code null}
 * @param zone zone the volume is located in
 * @param tags tags assigned to the volume
 */
public record Volume(String href, String uuid, String name, OffsetDateTime createdAt, Integer sizeGb, String type,
        List<ResourceStub> servers, @Nullable List<String> serverUuids, @Nullable String currentOperation,
        ZoneStub zone, Map<String, String> tags) {
}
