package io.ngine.cloudscale.client.resource;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.LoadBalancerListener;
import io.ngine.cloudscale.client.model.LoadBalancerListenerCreateRequest;
import io.ngine.cloudscale.client.model.LoadBalancerListenerUpdateRequest;

/** Load balancer listeners. */
public class LoadBalancerListenerResource extends
        CrudResource<LoadBalancerListener, LoadBalancerListenerCreateRequest, LoadBalancerListenerUpdateRequest> {

    public LoadBalancerListenerResource(ApiTransport transport) {
        super(transport, "/load-balancers/listeners", LoadBalancerListener.class);
    }
}
