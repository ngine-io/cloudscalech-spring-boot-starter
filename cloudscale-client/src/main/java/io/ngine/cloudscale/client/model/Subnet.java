package io.ngine.cloudscale.client.model;

import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;

/**
 * A subnet of a private network.
 *
 * @param href URL of the resource
 * @param uuid unique identifier
 * @param cidr address range in CIDR notation
 * @param network network the subnet belongs to
 * @param gatewayAddress gateway address, {@code null} if no gateway is used
 * @param dnsServers DNS resolver addresses
 * @param tags tags assigned to the subnet
 */
public record Subnet(String href, String uuid, String cidr, ResourceStub network, @Nullable String gatewayAddress,
        @Nullable List<String> dnsServers, Map<String, String> tags) {
}
