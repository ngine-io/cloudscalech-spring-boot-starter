package io.ngine.cloudscale.client.model;

/**
 * GPU specification of a flavor.
 *
 * @param name display name of the GPU
 * @param count number of GPUs provided by the flavor
 * @param vramPerGpuGb VRAM in GiB per GPU
 */
public record Gpu(String name, Integer count, Integer vramPerGpuGb) {
}
