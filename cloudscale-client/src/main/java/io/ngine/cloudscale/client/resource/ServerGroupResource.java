package io.ngine.cloudscale.client.resource;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.ServerGroup;
import io.ngine.cloudscale.client.model.ServerGroupCreateRequest;
import io.ngine.cloudscale.client.model.ServerGroupUpdateRequest;

/** Server groups, see <a href="https://www.cloudscale.ch/en/api/v1#server-groups">API docs</a>. */
public class ServerGroupResource
        extends CrudResource<ServerGroup, ServerGroupCreateRequest, ServerGroupUpdateRequest> {

    public ServerGroupResource(ApiTransport transport) {
        super(transport, "/server-groups", ServerGroup.class);
    }
}
