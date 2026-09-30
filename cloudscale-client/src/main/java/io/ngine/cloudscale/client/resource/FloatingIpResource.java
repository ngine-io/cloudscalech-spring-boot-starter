package io.ngine.cloudscale.client.resource;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.FloatingIp;
import io.ngine.cloudscale.client.model.FloatingIpCreateRequest;
import io.ngine.cloudscale.client.model.FloatingIpUpdateRequest;

/**
 * Floating IPs, see <a href="https://www.cloudscale.ch/en/api/v1#floating-ips">API docs</a>.
 * Floating IPs are identified by their network address, e.g. {@code 192.0.2.123}, see
 * {@link FloatingIp#networkId()}.
 */
public class FloatingIpResource extends CrudResource<FloatingIp, FloatingIpCreateRequest, FloatingIpUpdateRequest> {

    public FloatingIpResource(ApiTransport transport) {
        super(transport, "/floating-ips", FloatingIp.class);
    }
}
