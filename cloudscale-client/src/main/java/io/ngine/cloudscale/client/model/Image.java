package io.ngine.cloudscale.client.model;

import java.util.List;

import org.jspecify.annotations.Nullable;

/**
 * An operating system image provided by cloudscale.ch.
 *
 * @param slug unique string identifying the image, used when creating servers
 * @param name display name including release version
 * @param operatingSystem display name of the distribution without version
 * @param defaultUsername user that is set up during server creation
 * @param zones zones in which the image is available (not set when embedded in a server)
 */
public record Image(String slug, String name, @Nullable String operatingSystem, @Nullable String defaultUsername,
        @Nullable List<ZoneStub> zones) {
}
