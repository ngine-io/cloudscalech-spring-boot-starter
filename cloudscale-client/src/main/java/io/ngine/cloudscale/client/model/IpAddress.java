package io.ngine.cloudscale.client.model;

import org.jspecify.annotations.Nullable;

/**
 * An IP address assigned to a server interface, router or load balancer.
 *
 * @param version IP protocol version, 4 or 6
 * @param address the IP address
 * @param prefixLength the prefix length of the subnet (servers only)
 * @param gateway the gateway of the subnet (servers only)
 * @param reversePtr the reverse pointer of the address
 * @param subnet the subnet the address belongs to
 */
public record IpAddress(Integer version, String address, @Nullable Integer prefixLength, @Nullable String gateway,
        @Nullable String reversePtr, @Nullable SubnetStub subnet) {
}
