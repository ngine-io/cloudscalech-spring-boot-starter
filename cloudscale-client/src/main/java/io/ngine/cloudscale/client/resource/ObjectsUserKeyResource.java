package io.ngine.cloudscale.client.resource;

import java.util.List;
import java.util.Map;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.ObjectsUserKey;

/** Access keys of objects users, identified by their access key. */
public class ObjectsUserKeyResource {

    private static final String PATH = "/objects-user-keys";

    private final ApiTransport transport;

    public ObjectsUserKeyResource(ApiTransport transport) {
        this.transport = transport;
    }

    /** Lists the keys of all objects users. */
    public List<ObjectsUserKey> list() {
        return transport.list(PATH, ObjectsUserKey.class, null);
    }

    public ObjectsUserKey get(String accessKey) {
        return transport.get(PATH + "/{id}", ObjectsUserKey.class, accessKey);
    }

    /** Adds a new key to the given objects user. */
    public ObjectsUserKey create(String objectsUserId) {
        return transport.post(PATH, Map.of("objects_user", objectsUserId), ObjectsUserKey.class);
    }

    public void delete(String accessKey) {
        transport.delete(PATH + "/{id}", accessKey);
    }
}
