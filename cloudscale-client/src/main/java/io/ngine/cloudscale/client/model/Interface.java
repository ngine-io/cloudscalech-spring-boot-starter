package io.ngine.cloudscale.client.model;

import java.util.List;

import org.jspecify.annotations.Nullable;

/**
 * A network interface of a server or router.
 *
 * @param uuid unique identifier of the interface (router interfaces only)
 * @param type {@code public} or {@code private}
 * @param network the network the interface is attached to
 * @param macAddress the MAC address of the interface
 * @param addresses the IP addresses assigned to the interface
 */
public record Interface(@Nullable String uuid, String type, ResourceStub network, @Nullable String macAddress,
        List<IpAddress> addresses) {
}
