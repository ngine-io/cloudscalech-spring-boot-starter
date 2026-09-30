package io.ngine.cloudscale.client.resource;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.LoadBalancer;
import io.ngine.cloudscale.client.model.LoadBalancerCreateRequest;
import io.ngine.cloudscale.client.model.LoadBalancerUpdateRequest;

/**
 * Load balancers, see <a href="https://www.cloudscale.ch/en/api/v1#load-balancers">API docs</a>.
 * Deleting a load balancer also deletes its pools, listeners and health monitors.
 */
public class LoadBalancerResource
        extends CrudResource<LoadBalancer, LoadBalancerCreateRequest, LoadBalancerUpdateRequest> {

    public LoadBalancerResource(ApiTransport transport) {
        super(transport, "/load-balancers", LoadBalancer.class);
    }
}
