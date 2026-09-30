package io.ngine.cloudscale.client.resource;

import java.util.List;

import io.ngine.cloudscale.client.internal.ApiTransport;

/**
 * A read-only resource type that can only be listed, e.g. regions or flavors.
 *
 * @param <T> the resource type
 */
public class ListResource<T> {

    protected final ApiTransport transport;

    protected final String path;

    protected final Class<T> type;

    public ListResource(ApiTransport transport, String path, Class<T> type) {
        this.transport = transport;
        this.path = path;
        this.type = type;
    }

    /** Lists all resources. */
    public List<T> list() {
        return transport.list(path, type, null);
    }
}
