package io.ngine.cloudscale.client.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

/**
 * A load balancer.
 *
 * @param href URL of the resource
 * @param uuid unique identifier
 * @param name display name
 * @param createdAt creation date and time
 * @param status one of {@code changing}, {@code degraded}, {@code running}, {@code stopped},
 * {@code error}, {@code unknown}
 * @param zone zone the load balancer is located in
 * @param flavor flavor of the load balancer
 * @param vipAddresses virtual IP addresses receiving traffic
 * @param tags tags assigned to the load balancer
 */
public record LoadBalancer(String href, String uuid, String name, OffsetDateTime createdAt, String status,
        ZoneStub zone, LoadBalancerFlavor flavor, List<IpAddress> vipAddresses, Map<String, String> tags) {
}
