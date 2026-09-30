package io.ngine.cloudscale.client.model;

import org.jspecify.annotations.Nullable;

/**
 * A volume attached to a server. {@code href} and {@code uuid} are {@code null} until the
 * volume has been allocated.
 *
 * @param href URL of the volume
 * @param uuid unique identifier of the volume
 * @param name display name of the volume
 * @param sizeGb size in GiB
 * @param type {@code ssd} or {@code bulk}
 */
public record VolumeStub(@Nullable String href, @Nullable String uuid, String name, Integer sizeGb, String type) {
}
