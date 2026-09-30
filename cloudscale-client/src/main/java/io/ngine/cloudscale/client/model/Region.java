package io.ngine.cloudscale.client.model;

import java.util.List;

/**
 * A region, consisting of one or more zones.
 *
 * @param slug unique string identifying the region
 * @param zones the zones that are part of this region
 */
public record Region(String slug, List<ZoneStub> zones) {
}
