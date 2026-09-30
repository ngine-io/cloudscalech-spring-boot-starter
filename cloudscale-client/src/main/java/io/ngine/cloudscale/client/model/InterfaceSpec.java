package io.ngine.cloudscale.client.model;

import java.util.List;

import org.jspecify.annotations.Nullable;

/**
 * An interface specification used when creating or updating servers.
 *
 * @param network {@code public}, a network UUID, or {@code null} if derived from the subnet
 * of the addresses
 * @param addresses addresses to assign; an empty list assigns no address, {@code null}
 * assigns one from the DHCP range
 */
public record InterfaceSpec(@Nullable String network, @Nullable List<AddressSpec> addresses) {

    /** A public network interface. */
    public static InterfaceSpec publicNetwork() {
        return new InterfaceSpec("public", null);
    }

    /** A private interface on the given network with an address from the DHCP range. */
    public static InterfaceSpec privateNetwork(String networkUuid) {
        return new InterfaceSpec(networkUuid, null);
    }

    /** A private interface on the given network without any address. */
    public static InterfaceSpec privateNetworkWithoutAddress(String networkUuid) {
        return new InterfaceSpec(networkUuid, List.of());
    }

    /** A private interface with the given addresses, the network is derived from the subnets. */
    public static InterfaceSpec withAddresses(AddressSpec... addresses) {
        return new InterfaceSpec(null, List.of(addresses));
    }
}
