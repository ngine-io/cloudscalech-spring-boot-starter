package io.ngine.cloudscale.client.model;

import org.jspecify.annotations.Nullable;

/**
 * Short reference to another resource, e.g. the server a volume is attached to.
 *
 * @param href the URL of the referenced resource
 * @param uuid the unique identifier of the referenced resource
 * @param name the display name of the referenced resource
 */
public record ResourceStub(@Nullable String href, @Nullable String uuid, @Nullable String name) {
}
