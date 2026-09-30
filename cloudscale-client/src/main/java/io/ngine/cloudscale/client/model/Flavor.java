package io.ngine.cloudscale.client.model;

import java.util.List;

import org.jspecify.annotations.Nullable;

/**
 * A server flavor, i.e. a combination of vCPUs/cores, memory and GPUs.
 *
 * @param slug unique string identifying the flavor, used when creating or scaling servers
 * @param name display name of the flavor
 * @param vcpuCount number of shared vCPUs or dedicated CPU cores
 * @param memoryGb memory in GiB
 * @param gpu GPU specification, {@code null} if the flavor does not provide GPUs
 * @param zones zones in which the flavor is available (not set when embedded in a server)
 */
public record Flavor(String slug, String name, Integer vcpuCount, Integer memoryGb, @Nullable Gpu gpu,
        @Nullable List<ZoneStub> zones) {
}
