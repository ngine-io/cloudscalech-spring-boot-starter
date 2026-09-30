package io.ngine.cloudscale.client.model;

import java.time.OffsetDateTime;
import java.util.Map;

import org.jspecify.annotations.Nullable;

/**
 * A health monitor checking the pool members of a load balancer pool.
 *
 * @param href URL of the resource
 * @param uuid unique identifier
 * @param createdAt creation date and time
 * @param pool the pool being monitored
 * @param loadBalancer the load balancer of the health monitor
 * @param delayS delay between two checks in seconds
 * @param timeoutS maximum time of a single check in seconds
 * @param upThreshold successful checks before a member is considered up
 * @param downThreshold failed checks before a member is considered down
 * @param type {@code ping}, {@code tcp}, {@code http}, {@code https}, {@code tls-hello} or
 * {@code udp-connect}
 * @param http HTTP options for {@code http} and {@code https} monitors
 * @param tags tags assigned to the health monitor
 */
public record LoadBalancerHealthMonitor(String href, String uuid, OffsetDateTime createdAt, ResourceStub pool,
        ResourceStub loadBalancer, Integer delayS, Integer timeoutS, Integer upThreshold, Integer downThreshold,
        String type, @Nullable HealthMonitorHttp http, Map<String, String> tags) {
}
