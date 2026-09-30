package io.ngine.cloudscale.client.resource;

import java.util.List;

import io.ngine.cloudscale.client.TagFilter;
import io.ngine.cloudscale.client.internal.ApiTransport;

/**
 * A taggable resource type that can be listed and fetched by its identifier.
 *
 * @param <T> the resource type
 */
public class ReadResource<T> extends ListResource<T> {

    public ReadResource(ApiTransport transport, String path, Class<T> type) {
        super(transport, path, type);
    }

    /** Lists all resources matching the given tag filter. */
    public List<T> list(TagFilter filter) {
        return transport.list(path, type, filter);
    }

    /**
     * Gets a resource by its identifier.
     * @throws io.ngine.cloudscale.client.CloudscaleApiException with status 404 if not found
     */
    public T get(String id) {
        return transport.get(path + "/{id}", type, id);
    }
}
