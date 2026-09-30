package io.ngine.cloudscale.client.resource;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.Subnet;
import io.ngine.cloudscale.client.model.SubnetCreateRequest;
import io.ngine.cloudscale.client.model.SubnetUpdateRequest;

/** Subnets, see <a href="https://www.cloudscale.ch/en/api/v1#subnets">API docs</a>. */
public class SubnetResource extends CrudResource<Subnet, SubnetCreateRequest, SubnetUpdateRequest> {

    public SubnetResource(ApiTransport transport) {
        super(transport, "/subnets", Subnet.class);
    }
}
