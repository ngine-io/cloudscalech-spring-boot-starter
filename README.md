# cloudscale.ch Spring Boot Starter

A typed Java client and Spring Boot starter for the [cloudscale.ch API](https://www.cloudscale.ch/en/api/v1).

Requires Java 25 and Spring Boot 4.

## Modules

| Module | Description |
| --- | --- |
| `cloudscale-client` | Plain Java client based on Spring's `RestClient` and Jackson 3. Usable without Spring Boot. |
| `cloudscale-spring-boot-autoconfigure` | Auto-configuration of a `CloudscaleClient` bean. |
| `cloudscale-spring-boot-starter` | The starter to add to your application. |

## Usage

```xml
<dependency>
    <groupId>io.ngine.cloudscale</groupId>
    <artifactId>cloudscale-spring-boot-starter</artifactId>
    <version>0.1.0-SNAPSHOT</version>
</dependency>
```

Configure the API token, either as property or with the `CLOUDSCALE_API_TOKEN` environment variable
used by the other cloudscale.ch tools:

```yaml
cloudscale:
  api-token: ${CLOUDSCALE_API_TOKEN}
  # api-url: https://api.cloudscale.ch/v1
  # connect-timeout: 10s
  # read-timeout: 60s
  # enabled: true
```

Then inject the client:

```java
@Service
class Infrastructure {

    private final CloudscaleClient cloudscale;

    Infrastructure(CloudscaleClient cloudscale) {
        this.cloudscale = cloudscale;
    }

    Server createWebServer() {
        return cloudscale.servers().create(new ServerCreateRequest("web-1.example.com", "flex-4-2", "debian-13")
            .zone("lpg1")
            .volumeSizeGb(50)
            .sshKeys(List.of("ssh-ed25519 AAAA..."))
            .tag("env", "prod"));
    }

    List<Server> productionServers() {
        return cloudscale.servers().list(TagFilter.of("env", "prod"));
    }
}
```

Without Spring Boot, build the client yourself:

```java
CloudscaleClient cloudscale = CloudscaleClient.builder()
    .apiToken(System.getenv("CLOUDSCALE_API_TOKEN"))
    .build();
```

## Supported resources

| Accessor | Operations |
| --- | --- |
| `regions()`, `flavors()`, `images()` | list |
| `servers()` | list, get, create, update, delete, start, stop, reboot |
| `serverGroups()` | list, get, create, update, delete |
| `customImages()` | list, get, update, delete, importImage, listImports, getImport |
| `volumes()` | list, get, create (also from snapshot), update, delete, revert |
| `volumeSnapshots()` | list, get, create, update, delete |
| `floatingIps()` | list, get, create, update, delete (identified by address, e.g. `192.0.2.123`) |
| `networks()`, `subnets()` | list, get, create, update, delete |
| `routers()` | list, get, create, update, delete, createInterface, deleteInterface |
| `loadBalancers()`, `loadBalancerPools()`, `loadBalancerListeners()`, `loadBalancerHealthMonitors()` | list, get, create, update, delete |
| `loadBalancerPoolMembers()` | list, get, create, update, delete (scoped to a pool) |
| `objectsUsers()` | list, get, create, update, delete |
| `objectsUserKeys()` | list, get, create, delete |
| `metrics()` | bucket metrics |
| `projectLogs()` | list, follow pagination (`next` / `poll_more`), listAll |

Taggable resources can be filtered with `TagFilter.of("key", "value")` or `TagFilter.hasKey("key")`.

### Updates

Request objects only send the attributes that were set. The API only accepts one attribute per
`PATCH`, so set a single attribute per update call:

```java
cloudscale.servers().update(uuid, new ServerUpdateRequest().flavor("flex-8-4"));
```

To explicitly send `null` (e.g. to remove the gateway of a subnet), use the raw-payload overload:

```java
Map<String, Object> payload = new HashMap<>();
payload.put("gateway_address", null);
cloudscale.subnets().update(uuid, payload);
```

### Errors

HTTP errors are raised as `CloudscaleApiException`, exposing the status code and response body:

```java
try {
    cloudscale.servers().get(uuid);
}
catch (CloudscaleApiException ex) {
    if (ex.isNotFound()) {
        // ...
    }
}
```

## Customization

- If `spring-boot-starter-restclient` is present (it is pulled in by the starter), the client is built
  from Spring Boot's `RestClient.Builder`, so observability and `RestClientCustomizer`s apply.
- Declare a `CloudscaleClientBuilderCustomizer` bean to tweak the client builder (user agent, request factory, ...).
- Declare your own `CloudscaleClient` bean to replace the auto-configured one.

## Building

```shell
./mvnw -B verify
```

## Releasing

Releases are published to Maven Central by the `Release` GitHub workflow when a `v*` tag is pushed:

```
git tag v0.1.0 && git push origin v0.1.0
```

## License

Apache License 2.0
