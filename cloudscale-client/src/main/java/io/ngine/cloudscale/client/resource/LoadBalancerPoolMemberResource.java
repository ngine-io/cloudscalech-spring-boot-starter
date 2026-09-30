package io.ngine.cloudscale.client.resource;

import java.util.List;
import java.util.Map;

import io.ngine.cloudscale.client.TagFilter;
import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.LoadBalancerPoolMember;
import io.ngine.cloudscale.client.model.LoadBalancerPoolMemberCreateRequest;
import io.ngine.cloudscale.client.model.LoadBalancerPoolMemberUpdateRequest;

/** Members of a load balancer pool, always addressed through their pool. */
public class LoadBalancerPoolMemberResource {

    private static final String PATH = "/load-balancers/pools/{poolId}/members";

    private final ApiTransport transport;

    public LoadBalancerPoolMemberResource(ApiTransport transport) {
        this.transport = transport;
    }

    public List<LoadBalancerPoolMember> list(String poolUuid) {
        return transport.list(PATH, LoadBalancerPoolMember.class, null, poolUuid);
    }

    public List<LoadBalancerPoolMember> list(String poolUuid, TagFilter filter) {
        return transport.list(PATH, LoadBalancerPoolMember.class, filter, poolUuid);
    }

    public LoadBalancerPoolMember get(String poolUuid, String uuid) {
        return transport.get(PATH + "/{id}", LoadBalancerPoolMember.class, poolUuid, uuid);
    }

    public LoadBalancerPoolMember create(String poolUuid, LoadBalancerPoolMemberCreateRequest request) {
        return transport.post(PATH, request, LoadBalancerPoolMember.class, poolUuid);
    }

    /** Updates a pool member. The API only allows one attribute per update. */
    public void update(String poolUuid, String uuid, LoadBalancerPoolMemberUpdateRequest request) {
        transport.patch(PATH + "/{id}", request, poolUuid, uuid);
    }

    /** Updates a pool member with a raw payload. */
    public void update(String poolUuid, String uuid, Map<String, ?> payload) {
        transport.patch(PATH + "/{id}", payload, poolUuid, uuid);
    }

    public void delete(String poolUuid, String uuid) {
        transport.delete(PATH + "/{id}", poolUuid, uuid);
    }
}
