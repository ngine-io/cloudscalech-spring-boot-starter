package io.ngine.cloudscale.client.model;

import org.jspecify.annotations.Nullable;

/**
 * An IP address specification used in requests, e.g. for server or router interfaces
 * and load balancer VIP addresses.
 *
 * @param subnet UUID of the subnet the address belongs to
 * @param address a concrete address within the subnet, or {@code null} to get one from
 * the DHCP range
 */
public record AddressSpec(String subnet, @Nullable String address) {

    /** An address automatically assigned from the DHCP range of the subnet. */
    public static AddressSpec dhcp(String subnetUuid) {
        return new AddressSpec(subnetUuid, null);
    }

    /** A fixed address in the subnet. */
    public static AddressSpec fixed(String subnetUuid, String address) {
        return new AddressSpec(subnetUuid, address);
    }
}
