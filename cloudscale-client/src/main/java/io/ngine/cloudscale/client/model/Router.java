package io.ngine.cloudscale.client.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

/**
 * A router connecting private networks, optionally acting as internet gateway.
 *
 * @param href URL of the resource
 * @param uuid unique identifier
 * @param name display name
 * @param createdAt creation date and time
 * @param status one of {@code active}, {@code error}, {@code unknown}
 * @param zone zone the router is located in
 * @param internetGateway whether the router SNATs private traffic to its public IPv4 address
 * @param internetGatewayAddresses public addresses of the router
 * @param interfaces private interfaces of the router
 * @param tags tags assigned to the router
 */
public record Router(String href, String uuid, String name, OffsetDateTime createdAt, String status, ZoneStub zone,
        Boolean internetGateway, List<IpAddress> internetGatewayAddresses, List<Interface> interfaces,
        Map<String, String> tags) {
}
