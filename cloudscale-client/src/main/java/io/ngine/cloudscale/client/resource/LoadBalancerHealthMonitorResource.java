package io.ngine.cloudscale.client.resource;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.LoadBalancerHealthMonitor;
import io.ngine.cloudscale.client.model.LoadBalancerHealthMonitorCreateRequest;
import io.ngine.cloudscale.client.model.LoadBalancerHealthMonitorUpdateRequest;

/** Load balancer health monitors. */
public class LoadBalancerHealthMonitorResource extends CrudResource<LoadBalancerHealthMonitor,
        LoadBalancerHealthMonitorCreateRequest, LoadBalancerHealthMonitorUpdateRequest> {

    public LoadBalancerHealthMonitorResource(ApiTransport transport) {
        super(transport, "/load-balancers/health-monitors", LoadBalancerHealthMonitor.class);
    }
}
