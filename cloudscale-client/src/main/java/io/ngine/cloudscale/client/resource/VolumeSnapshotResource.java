package io.ngine.cloudscale.client.resource;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.VolumeSnapshot;
import io.ngine.cloudscale.client.model.VolumeSnapshotCreateRequest;
import io.ngine.cloudscale.client.model.VolumeSnapshotUpdateRequest;

/** Volume snapshots, see <a href="https://www.cloudscale.ch/en/api/v1#volume-snapshots">API docs</a>. */
public class VolumeSnapshotResource
        extends CrudResource<VolumeSnapshot, VolumeSnapshotCreateRequest, VolumeSnapshotUpdateRequest> {

    public VolumeSnapshotResource(ApiTransport transport) {
        super(transport, "/volume-snapshots", VolumeSnapshot.class);
    }
}
