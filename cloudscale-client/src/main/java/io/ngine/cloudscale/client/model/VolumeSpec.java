package io.ngine.cloudscale.client.model;

/**
 * A volume to be created and attached along with a new server.
 *
 * @param sizeGb size in GiB
 * @param type {@code ssd} or {@code bulk}
 */
public record VolumeSpec(int sizeGb, String type) {

    public static VolumeSpec ssd(int sizeGb) {
        return new VolumeSpec(sizeGb, "ssd");
    }

    public static VolumeSpec bulk(int sizeGb) {
        return new VolumeSpec(sizeGb, "bulk");
    }
}
