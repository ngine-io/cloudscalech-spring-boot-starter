package io.ngine.cloudscale.client.resource;

import java.util.Map;

import io.ngine.cloudscale.client.internal.ApiTransport;

/**
 * A taggable resource type that can be created, read, updated and deleted.
 *
 * @param <T> the resource type
 * @param <C> the create request type
 * @param <U> the update request type
 */
public class CrudResource<T, C, U> extends ReadResource<T> {

    public CrudResource(ApiTransport transport, String path, Class<T> type) {
        super(transport, path, type);
    }

    /** Creates a resource and returns its representation. */
    public T create(C request) {
        return transport.post(path, request, type);
    }

    /**
     * Updates a resource. Note that the API only allows one attribute to be updated at a
     * time.
     */
    public void update(String id, U request) {
        transport.patch(path + "/{id}", request, id);
    }

    /**
     * Updates a resource with a raw payload. Useful to set attributes explicitly to
     * {@code null}, e.g. to remove the gateway of a subnet, or for attributes not yet
     * supported by this client.
     */
    public void update(String id, Map<String, ?> payload) {
        transport.patch(path + "/{id}", payload, id);
    }

    /** Deletes a resource. */
    public void delete(String id) {
        transport.delete(path + "/{id}", id);
    }
}
