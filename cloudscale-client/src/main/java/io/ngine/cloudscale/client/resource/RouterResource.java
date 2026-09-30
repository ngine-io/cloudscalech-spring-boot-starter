package io.ngine.cloudscale.client.resource;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.Interface;
import io.ngine.cloudscale.client.model.Router;
import io.ngine.cloudscale.client.model.RouterCreateRequest;
import io.ngine.cloudscale.client.model.RouterInterfaceCreateRequest;
import io.ngine.cloudscale.client.model.RouterUpdateRequest;

/** Routers, see <a href="https://www.cloudscale.ch/en/api/v1#routers">API docs</a>. */
public class RouterResource extends CrudResource<Router, RouterCreateRequest, RouterUpdateRequest> {

    public RouterResource(ApiTransport transport) {
        super(transport, "/routers", Router.class);
    }

    /** Creates an interface and attaches it to the router. */
    public Interface createInterface(String routerUuid, RouterInterfaceCreateRequest request) {
        return transport.post(path + "/{id}/interfaces", request, Interface.class, routerUuid);
    }

    /** Detaches an interface from the router and deletes it. */
    public void deleteInterface(String routerUuid, String interfaceUuid) {
        transport.delete(path + "/{id}/interfaces/{interfaceId}", routerUuid, interfaceUuid);
    }
}
