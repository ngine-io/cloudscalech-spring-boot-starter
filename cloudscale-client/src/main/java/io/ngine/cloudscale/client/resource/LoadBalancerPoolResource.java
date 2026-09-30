package io.ngine.cloudscale.client.resource;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.LoadBalancerPool;
import io.ngine.cloudscale.client.model.LoadBalancerPoolCreateRequest;
import io.ngine.cloudscale.client.model.LoadBalancerPoolUpdateRequest;

/** Load balancer pools. */
public class LoadBalancerPoolResource
        extends CrudResource<LoadBalancerPool, LoadBalancerPoolCreateRequest, LoadBalancerPoolUpdateRequest> {

    public LoadBalancerPoolResource(ApiTransport transport) {
        super(transport, "/load-balancers/pools", LoadBalancerPool.class);
    }
}
