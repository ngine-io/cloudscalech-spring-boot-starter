package io.ngine.cloudscale.autoconfigure;

import java.time.Duration;

import io.ngine.cloudscale.client.CloudscaleClient;
import org.jspecify.annotations.Nullable;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties of the cloudscale.ch API client.
 */
@ConfigurationProperties("cloudscale")
public class CloudscaleProperties {

    /**
     * Whether to auto-configure the cloudscale.ch API client.
     */
    private boolean enabled = true;

    /**
     * API token, created in the cloudscale.ch control panel. Falls back to the
     * CLOUDSCALE_API_TOKEN environment variable.
     */
    private @Nullable String apiToken;

    /**
     * Base URL of the cloudscale.ch API.
     */
    private String apiUrl = CloudscaleClient.DEFAULT_API_URL;

    /**
     * Connect timeout of API requests.
     */
    private Duration connectTimeout = Duration.ofSeconds(10);

    /**
     * Read timeout of API requests.
     */
    private Duration readTimeout = Duration.ofSeconds(60);

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public @Nullable String getApiToken() {
        return apiToken;
    }

    public void setApiToken(@Nullable String apiToken) {
        this.apiToken = apiToken;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
    }
}
