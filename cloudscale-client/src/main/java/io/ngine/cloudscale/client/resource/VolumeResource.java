package io.ngine.cloudscale.client.resource;

import java.util.Map;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.Volume;
import io.ngine.cloudscale.client.model.VolumeCreateRequest;
import io.ngine.cloudscale.client.model.VolumeUpdateRequest;

/** Volumes, see <a href="https://www.cloudscale.ch/en/api/v1#volumes">API docs</a>. */
public class VolumeResource extends CrudResource<Volume, VolumeCreateRequest, VolumeUpdateRequest> {

    public VolumeResource(ApiTransport transport) {
        super(transport, "/volumes", Volume.class);
    }

    /**
     * Reverts a volume to its latest snapshot. Root volumes require the server to be
     * stopped, other volumes must be detached.
     */
    public void revert(String uuid, String snapshotUuid) {
        transport.post(path + "/{id}/revert", Map.of("snapshot", snapshotUuid), uuid);
    }
}
