package io.ngine.cloudscale.client.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

/**
 * A custom image imported by the user.
 *
 * @param href URL of the resource
 * @param uuid unique identifier
 * @param name display name
 * @param createdAt creation date and time
 * @param slug string identifying the custom image, use {@code custom:<slug>} when creating servers
 * @param sizeGb size after conversion to raw format in GiB
 * @param checksums checksums keyed by algorithm, e.g. {@code sha256}
 * @param userDataHandling {@code pass-through} or {@code extend-cloud-config}
 * @param firmwareType {@code bios} or {@code uefi}
 * @param zones zones in which the custom image is available
 * @param tags tags assigned to the custom image
 */
public record CustomImage(String href, String uuid, String name, OffsetDateTime createdAt, String slug,
        Integer sizeGb, Map<String, String> checksums, String userDataHandling, String firmwareType,
        List<ZoneStub> zones, Map<String, String> tags) {
}
