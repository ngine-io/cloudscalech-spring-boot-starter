package io.ngine.cloudscale.client.resource;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.Network;
import io.ngine.cloudscale.client.model.NetworkCreateRequest;
import io.ngine.cloudscale.client.model.NetworkUpdateRequest;

/** Private networks, see <a href="https://www.cloudscale.ch/en/api/v1#networks">API docs</a>. */
public class NetworkResource extends CrudResource<Network, NetworkCreateRequest, NetworkUpdateRequest> {

    public NetworkResource(ApiTransport transport) {
        super(transport, "/networks", Network.class);
    }
}
