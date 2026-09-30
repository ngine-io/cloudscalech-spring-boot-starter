package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to update a load balancer pool member. The API only allows one attribute per update.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class LoadBalancerPoolMemberUpdateRequest {

    private @Nullable String name;

    private @Nullable Boolean enabled;

    private @Nullable Map<String, String> tags;

    public LoadBalancerPoolMemberUpdateRequest() {
    }

    /**
     * The display name.
     */
    public LoadBalancerPoolMemberUpdateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * Whether the member receives traffic.
     */
    public LoadBalancerPoolMemberUpdateRequest enabled(@Nullable Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public LoadBalancerPoolMemberUpdateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public LoadBalancerPoolMemberUpdateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable Boolean getEnabled() {
        return this.enabled;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
