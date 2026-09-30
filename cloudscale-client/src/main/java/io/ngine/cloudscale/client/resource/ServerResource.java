package io.ngine.cloudscale.client.resource;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.Server;
import io.ngine.cloudscale.client.model.ServerCreateRequest;
import io.ngine.cloudscale.client.model.ServerUpdateRequest;

/** Servers, see <a href="https://www.cloudscale.ch/en/api/v1#servers">API docs</a>. */
public class ServerResource extends CrudResource<Server, ServerCreateRequest, ServerUpdateRequest> {

    public ServerResource(ApiTransport transport) {
        super(transport, "/servers", Server.class);
    }

    /** Initiates the boot process of a stopped server. */
    public void start(String uuid) {
        transport.post(path + "/{id}/start", null, uuid);
    }

    /** Initiates a clean shutdown of the server. */
    public void stop(String uuid) {
        transport.post(path + "/{id}/stop", null, uuid);
    }

    /** Initiates a clean reboot of the server. */
    public void reboot(String uuid) {
        transport.post(path + "/{id}/reboot", null, uuid);
    }
}
