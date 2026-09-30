package io.ngine.cloudscale.client.model;

import java.time.OffsetDateTime;
import java.util.Map;

import org.jspecify.annotations.Nullable;

/**
 * A Floating IP address or network that can be moved between servers or load balancers.
 *
 * @param href URL of the resource
 * @param network the Floating IP in CIDR notation
 * @param createdAt creation date and time
 * @param ipVersion 4 or 6
 * @param server destination server, if any
 * @param loadBalancer destination load balancer, if any
 * @param type {@code regional} or {@code global}
 * @param region region of a regional Floating IP, {@code null} for global ones
 * @param nextHop destination IP address
 * @param reversePtr reverse pointer
 * @param tags tags assigned to the Floating IP
 */
public record FloatingIp(String href, String network, OffsetDateTime createdAt, Integer ipVersion,
        @Nullable ResourceStub server, @Nullable ResourceStub loadBalancer, String type, @Nullable RegionStub region,
        @Nullable String nextHop, @Nullable String reversePtr, Map<String, String> tags) {

    /**
     * The identifier used in the API URL of this Floating IP, e.g. {@code 192.0.2.123}.
     */
    public String networkId() {
        int idx = href.lastIndexOf("/floating-ips/");
        return (idx >= 0) ? href.substring(idx + "/floating-ips/".length()) : network.split("/")[0];
    }
}
