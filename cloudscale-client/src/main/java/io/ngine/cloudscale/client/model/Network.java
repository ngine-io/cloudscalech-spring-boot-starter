package io.ngine.cloudscale.client.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

/**
 * A private network.
 *
 * @param href URL of the resource
 * @param uuid unique identifier
 * @param name display name
 * @param createdAt creation date and time
 * @param zone zone the network is located in
 * @param mtu MTU size of the network
 * @param subnets subnets configured in the network
 * @param tags tags assigned to the network
 */
public record Network(String href, String uuid, String name, OffsetDateTime createdAt, ZoneStub zone, Integer mtu,
        List<SubnetStub> subnets, Map<String, String> tags) {
}
