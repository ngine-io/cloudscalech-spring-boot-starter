package io.ngine.cloudscale.client.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

/**
 * A load balancer listener defining the protocol and port for incoming traffic.
 *
 * @param href URL of the resource
 * @param uuid unique identifier
 * @param name display name
 * @param createdAt creation date and time
 * @param pool the pool traffic is forwarded to
 * @param loadBalancer the load balancer of the listener
 * @param protocol {@code tcp} or {@code udp}
 * @param protocolPort port traffic is received on
 * @param allowedCidrs allowed source ranges, empty means any source is allowed
 * @param timeoutClientDataMs client inactivity timeout in milliseconds
 * @param timeoutMemberConnectMs pool member connect timeout in milliseconds
 * @param timeoutMemberDataMs pool member inactivity timeout in milliseconds
 * @param tags tags assigned to the listener
 */
public record LoadBalancerListener(String href, String uuid, String name, OffsetDateTime createdAt,
        ResourceStub pool, ResourceStub loadBalancer, String protocol, Integer protocolPort, List<String> allowedCidrs,
        Integer timeoutClientDataMs, Integer timeoutMemberConnectMs, Integer timeoutMemberDataMs,
        Map<String, String> tags) {
}
