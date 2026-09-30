package io.ngine.cloudscale.client.model;

import java.util.List;
import java.util.Map;

/**
 * A server group providing a placement policy for its servers.
 *
 * @param href URL of the resource
 * @param uuid unique identifier
 * @param name display name
 * @param type the type, currently only {@code anti-affinity}
 * @param servers servers that are part of the group
 * @param zone zone the group is located in
 * @param tags tags assigned to the server group
 */
public record ServerGroup(String href, String uuid, String name, String type, List<ResourceStub> servers,
        ZoneStub zone, Map<String, String> tags) {
}
