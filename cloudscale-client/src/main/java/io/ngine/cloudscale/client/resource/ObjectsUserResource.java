package io.ngine.cloudscale.client.resource;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.ObjectsUser;
import io.ngine.cloudscale.client.model.ObjectsUserCreateRequest;
import io.ngine.cloudscale.client.model.ObjectsUserUpdateRequest;

/**
 * Users of the S3-compatible object storage, see
 * <a href="https://www.cloudscale.ch/en/api/v1#objects-users">API docs</a>. Objects users
 * are identified by an {@code id}, not a UUID.
 */
public class ObjectsUserResource extends CrudResource<ObjectsUser, ObjectsUserCreateRequest, ObjectsUserUpdateRequest> {

    public ObjectsUserResource(ApiTransport transport) {
        super(transport, "/objects-users", ObjectsUser.class);
    }
}
