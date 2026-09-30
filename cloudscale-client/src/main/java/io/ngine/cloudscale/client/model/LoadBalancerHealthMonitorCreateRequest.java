package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to create a load balancer health monitor.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class LoadBalancerHealthMonitorCreateRequest {

    private @Nullable String pool;

    private @Nullable String type;

    private @Nullable Integer delayS;

    private @Nullable Integer timeoutS;

    private @Nullable Integer upThreshold;

    private @Nullable Integer downThreshold;

    private @Nullable HealthMonitorHttp http;

    private @Nullable Map<String, String> tags;

    public LoadBalancerHealthMonitorCreateRequest(String pool, String type) {
        this.pool = pool;
        this.type = type;
    }

    /**
     * The UUID of the pool.
     */
    public LoadBalancerHealthMonitorCreateRequest pool(@Nullable String pool) {
        this.pool = pool;
        return this;
    }

    /**
     * {@code ping}, {@code tcp}, {@code http}, {@code https}, {@code tls-hello} or {@code udp-connect}.
     */
    public LoadBalancerHealthMonitorCreateRequest type(@Nullable String type) {
        this.type = type;
        return this;
    }

    /**
     * Delay between two checks in seconds.
     */
    public LoadBalancerHealthMonitorCreateRequest delayS(@Nullable Integer delayS) {
        this.delayS = delayS;
        return this;
    }

    /**
     * Maximum time of a single check in seconds.
     */
    public LoadBalancerHealthMonitorCreateRequest timeoutS(@Nullable Integer timeoutS) {
        this.timeoutS = timeoutS;
        return this;
    }

    /**
     * Successful checks before a member is considered up.
     */
    public LoadBalancerHealthMonitorCreateRequest upThreshold(@Nullable Integer upThreshold) {
        this.upThreshold = upThreshold;
        return this;
    }

    /**
     * Failed checks before a member is considered down.
     */
    public LoadBalancerHealthMonitorCreateRequest downThreshold(@Nullable Integer downThreshold) {
        this.downThreshold = downThreshold;
        return this;
    }

    /**
     * HTTP options for {@code http} and {@code https} monitors.
     */
    public LoadBalancerHealthMonitorCreateRequest http(@Nullable HealthMonitorHttp http) {
        this.http = http;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public LoadBalancerHealthMonitorCreateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public LoadBalancerHealthMonitorCreateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getPool() {
        return this.pool;
    }

    public @Nullable String getType() {
        return this.type;
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
