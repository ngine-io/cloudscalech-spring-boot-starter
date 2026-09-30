package io.ngine.cloudscale.client.model;

import java.time.OffsetDateTime;
import java.util.Map;

import org.jspecify.annotations.Nullable;

/**
 * A member of a load balancer pool.
 *
 * @param href URL of the resource
 * @param uuid unique identifier
 * @param name display name
 * @param createdAt creation date and time
 * @param enabled whether the member receives traffic
 * @param pool the pool of the member
 * @param loadBalancer the load balancer of the member
 * @param subnet subnet through which the member is reached
 * @param address IP address traffic is sent to
 * @param protocolPort port traffic is sent to
 * @param monitorPort port health checks are sent to, {@code null} to use {@code protocolPort}
 * @param monitorStatus one of {@code up}, {@code down}, {@code changing}, {@code no_monitor},
 * {@code unknown}
 * @param tags tags assigned to the pool member
 */
public record LoadBalancerPoolMember(String href, String uuid, String name, OffsetDateTime createdAt,
        Boolean enabled, ResourceStub pool, ResourceStub loadBalancer, SubnetStub subnet, String address,
        Integer protocolPort, @Nullable Integer monitorPort, String monitorStatus, Map<String, String> tags) {
}
