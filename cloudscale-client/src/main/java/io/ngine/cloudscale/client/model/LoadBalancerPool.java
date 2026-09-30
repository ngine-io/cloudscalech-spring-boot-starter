package io.ngine.cloudscale.client.model;

import java.time.OffsetDateTime;
import java.util.Map;

/**
 * A load balancer pool, i.e. a group of pool members receiving traffic.
 *
 * @param href URL of the resource
 * @param uuid unique identifier
 * @param name display name
 * @param createdAt creation date and time
 * @param loadBalancer the load balancer of the pool
 * @param algorithm {@code round_robin}, {@code least_connections} or {@code source_ip}
 * @param protocol {@code tcp}, {@code proxy}, {@code proxyv2} or {@code udp}
 * @param tags tags assigned to the pool
 */
public record LoadBalancerPool(String href, String uuid, String name, OffsetDateTime createdAt,
        ResourceStub loadBalancer, String algorithm, String protocol, Map<String, String> tags) {
}
