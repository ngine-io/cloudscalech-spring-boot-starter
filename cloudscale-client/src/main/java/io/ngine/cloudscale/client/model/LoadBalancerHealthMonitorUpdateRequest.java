package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to update a load balancer health monitor. The API only allows one attribute per update.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class LoadBalancerHealthMonitorUpdateRequest {

    private @Nullable Integer delayS;

    private @Nullable Integer timeoutS;

    private @Nullable Integer upThreshold;

    private @Nullable Integer downThreshold;

    private @Nullable HealthMonitorHttp http;

    private @Nullable Map<String, String> tags;

    public LoadBalancerHealthMonitorUpdateRequest() {
    }

    /**
     * Delay between two checks in seconds.
     */
    public LoadBalancerHealthMonitorUpdateRequest delayS(@Nullable Integer delayS) {
        this.delayS = delayS;
        return this;
    }

    /**
     * Maximum time of a single check in seconds.
     */
    public LoadBalancerHealthMonitorUpdateRequest timeoutS(@Nullable Integer timeoutS) {
        this.timeoutS = timeoutS;
        return this;
    }

    /**
     * Successful checks before a member is considered up.
     */
    public LoadBalancerHealthMonitorUpdateRequest upThreshold(@Nullable Integer upThreshold) {
        this.upThreshold = upThreshold;
        return this;
    }

    /**
     * Failed checks before a member is considered down.
     */
    public LoadBalancerHealthMonitorUpdateRequest downThreshold(@Nullable Integer downThreshold) {
        this.downThreshold = downThreshold;
        return this;
    }

    /**
     * HTTP options for {@code http} and {@code https} monitors.
     */
    public LoadBalancerHealthMonitorUpdateRequest http(@Nullable HealthMonitorHttp http) {
        this.http = http;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public LoadBalancerHealthMonitorUpdateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public LoadBalancerHealthMonitorUpdateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable Integer getDelayS() {
        return this.delayS;
    }

    public @Nullable Integer getTimeoutS() {
        return this.timeoutS;
    }

    public @Nullable Integer getUpThreshold() {
        return this.upThreshold;
    }

    public @Nullable Integer getDownThreshold() {
        return this.downThreshold;
    }

    public @Nullable HealthMonitorHttp getHttp() {
        return this.http;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
