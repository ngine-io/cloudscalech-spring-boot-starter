package io.ngine.cloudscale.client.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to attach an interface to a router.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class RouterInterfaceCreateRequest {

    private @Nullable String network;

    private @Nullable List<AddressSpec> addresses;

    public RouterInterfaceCreateRequest(String network) {
        this.network = network;
    }

    /**
     * The UUID of the network.
     */
    public RouterInterfaceCreateRequest network(@Nullable String network) {
        this.network = network;
        return this;
    }

    /**
     * The addresses assigned to the router in the network.
     */
    public RouterInterfaceCreateRequest addresses(@Nullable List<AddressSpec> addresses) {
        this.addresses = addresses;
        return this;
    }

    public @Nullable String getNetwork() {
        return this.network;
    }

    public @Nullable List<AddressSpec> getAddresses() {
        return this.addresses;
    }
}
