package io.ngine.cloudscale.client.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jspecify.annotations.Nullable;

/**
 * Request to create a server.
 * <p>
 * Only attributes that are set are sent to the API.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class ServerCreateRequest {

    private @Nullable String name;

    private @Nullable String flavor;

    private @Nullable String image;

    private @Nullable String zone;

    private @Nullable Integer volumeSizeGb;

    private @Nullable List<VolumeSpec> volumes;

    private @Nullable List<InterfaceSpec> interfaces;

    private @Nullable List<String> sshKeys;

    private @Nullable String password;

    private @Nullable Boolean usePublicNetwork;

    private @Nullable Boolean usePrivateNetwork;

    private @Nullable Boolean useIpv6;

    private @Nullable List<String> serverGroups;

    private @Nullable String userData;

    private @Nullable Map<String, String> tags;

    public ServerCreateRequest(String name, String flavor, String image) {
        this.name = name;
        this.flavor = flavor;
        this.image = image;
    }

    /**
     * A valid host name or FQDN; an FQDN also sets the reverse PTR.
     */
    public ServerCreateRequest name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The slug of the flavor, e.g. {@code flex-4-2}.
     */
    public ServerCreateRequest flavor(@Nullable String flavor) {
        this.flavor = flavor;
        return this;
    }

    /**
     * The image slug, {@code custom:<slug>} or the UUID of a custom image.
     */
    public ServerCreateRequest image(@Nullable String image) {
        this.image = image;
        return this;
    }

    /**
     * The slug of the zone, defaults to the project's default zone.
     */
    public ServerCreateRequest zone(@Nullable String zone) {
        this.zone = zone;
        return this;
    }

    /**
     * The size of the root volume in GiB.
     */
    public ServerCreateRequest volumeSizeGb(@Nullable Integer volumeSizeGb) {
        this.volumeSizeGb = volumeSizeGb;
        return this;
    }

    /**
     * Additional volumes to be created and attached.
     */
    public ServerCreateRequest volumes(@Nullable List<VolumeSpec> volumes) {
        this.volumes = volumes;
        return this;
    }

    /**
     * Interfaces to attach, cannot be combined with {@code usePublicNetwork}/{@code usePrivateNetwork}.
     */
    public ServerCreateRequest interfaces(@Nullable List<InterfaceSpec> interfaces) {
        this.interfaces = interfaces;
        return this;
    }

    /**
     * SSH public keys to be placed on the server.
     */
    public ServerCreateRequest sshKeys(@Nullable List<String> sshKeys) {
        this.sshKeys = sshKeys;
        return this;
    }

    /**
     * The password of the default user.
     */
    public ServerCreateRequest password(@Nullable String password) {
        this.password = password;
        return this;
    }

    /**
     * Attach a public network interface.
     */
    public ServerCreateRequest usePublicNetwork(@Nullable Boolean usePublicNetwork) {
        this.usePublicNetwork = usePublicNetwork;
        return this;
    }

    /**
     * Attach a private network interface.
     */
    public ServerCreateRequest usePrivateNetwork(@Nullable Boolean usePrivateNetwork) {
        this.usePrivateNetwork = usePrivateNetwork;
        return this;
    }

    /**
     * Enable IPv6 on the public network interface.
     */
    public ServerCreateRequest useIpv6(@Nullable Boolean useIpv6) {
        this.useIpv6 = useIpv6;
        return this;
    }

    /**
     * UUIDs of the server groups the server is added to.
     */
    public ServerCreateRequest serverGroups(@Nullable List<String> serverGroups) {
        this.serverGroups = serverGroups;
        return this;
    }

    /**
     * cloud-init (YAML) or Ignition (JSON) user data.
     */
    public ServerCreateRequest userData(@Nullable String userData) {
        this.userData = userData;
        return this;
    }

    /**
     * The tags assigned to the resource.
     */
    public ServerCreateRequest tags(@Nullable Map<String, String> tags) {
        this.tags = (tags != null) ? new LinkedHashMap<>(tags) : null;
        return this;
    }

    /**
     * Adds a single tag, keeping the ones already set.
     */
    public ServerCreateRequest tag(String key, String value) {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<>();
        }
        this.tags.put(key, value);
        return this;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable String getFlavor() {
        return this.flavor;
    }

    public @Nullable String getImage() {
        return this.image;
    }

    public @Nullable String getZone() {
        return this.zone;
    }

    public @Nullable Integer getVolumeSizeGb() {
        return this.volumeSizeGb;
    }

    public @Nullable List<VolumeSpec> getVolumes() {
        return this.volumes;
    }

    public @Nullable List<InterfaceSpec> getInterfaces() {
        return this.interfaces;
    }

    public @Nullable List<String> getSshKeys() {
        return this.sshKeys;
    }

    public @Nullable String getPassword() {
        return this.password;
    }

    public @Nullable Boolean getUsePublicNetwork() {
        return this.usePublicNetwork;
    }

    public @Nullable Boolean getUsePrivateNetwork() {
        return this.usePrivateNetwork;
    }

    public @Nullable Boolean getUseIpv6() {
        return this.useIpv6;
    }

    public @Nullable List<String> getServerGroups() {
        return this.serverGroups;
    }

    public @Nullable String getUserData() {
        return this.userData;
    }

    public @Nullable Map<String, String> getTags() {
        return this.tags;
    }
}
