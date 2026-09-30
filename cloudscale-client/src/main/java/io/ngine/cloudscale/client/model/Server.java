package io.ngine.cloudscale.client.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;

/**
 * A server (virtual machine).
 *
 * @param href URL of the resource
 * @param uuid unique identifier
 * @param name display name
 * @param createdAt creation date and time
 * @param status one of {@code paused}, {@code changing}, {@code running}, {@code stopped},
 * {@code rescue_running}, {@code rescue_stopped}, {@code error}, {@code unknown}
 * @param zone zone the server is located in
 * @param flavor flavor of the server
 * @param image image or custom image of the server
 * @param volumes volumes attached to the server
 * @param interfaces network interfaces including assigned addresses
 * @param sshFingerprints SSH host key fingerprints, {@code null} until retrieved
 * @param sshHostKeys SSH host keys, {@code null} until retrieved
 * @param antiAffinityWith deprecated, use {@code serverGroups}
 * @param serverGroups server groups the server is part of
 * @param tags tags assigned to the server
 */
public record Server(String href, String uuid, String name, OffsetDateTime createdAt, String status, ZoneStub zone,
        Flavor flavor, Image image, List<VolumeStub> volumes, List<Interface> interfaces,
        @Nullable List<String> sshFingerprints, @Nullable List<String> sshHostKeys,
        @Nullable List<ResourceStub> antiAffinityWith, List<ResourceStub> serverGroups, Map<String, String> tags) {

    public static final String STATUS_RUNNING = "running";

    public static final String STATUS_STOPPED = "stopped";

    public static final String STATUS_CHANGING = "changing";

    public boolean isRunning() {
        return STATUS_RUNNING.equals(status);
    }

    public boolean isStopped() {
        return STATUS_STOPPED.equals(status);
    }
}
