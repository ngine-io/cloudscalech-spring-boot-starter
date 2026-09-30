package io.ngine.cloudscale.client.model;

/**
 * Short reference to a subnet.
 *
 * @param href the URL of the subnet
 * @param uuid the unique identifier of the subnet
 * @param cidr the address range in CIDR notation
 */
public record SubnetStub(String href, String uuid, String cidr) {
}
