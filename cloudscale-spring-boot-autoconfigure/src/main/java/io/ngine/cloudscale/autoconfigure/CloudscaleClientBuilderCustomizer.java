package io.ngine.cloudscale.autoconfigure;

import io.ngine.cloudscale.client.CloudscaleClient;

/**
 * Callback to customize the auto-configured {@link CloudscaleClient} before it is built,
 * e.g. to set a custom user agent or request factory.
 */
@FunctionalInterface
public interface CloudscaleClientBuilderCustomizer {

    void customize(CloudscaleClient.Builder builder);
}
