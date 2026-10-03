# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Java client and Spring Boot starter (libraries, not an app) for the cloudscale.ch API (https://www.cloudscale.ch/en/api/v1). There is no OpenAPI spec; the API docs page and the Python SDK (`cloudscale-ch/cloudscale-python-sdk`) are the references. Targets Java 25 and Spring Boot 4 (Jackson 3, i.e. `tools.jackson.*` packages for databind; annotations stay in `com.fasterxml.jackson.annotation`). Maven coordinates: `io.ngine.cloudscale:cloudscale-spring-boot-starter`.

## Commands

```sh
./mvnw -B verify                                              # what CI runs: compile, test, source + javadoc jars
./mvnw test                                                   # tests only
./mvnw test -pl cloudscale-client -Dtest=CloudscaleClientTests               # single test class
./mvnw test -pl cloudscale-client -Dtest=CloudscaleClientTests#serverActions # single test method
```

The javadoc jar is built in the default lifecycle (not only on release), so broken javadoc can break `./mvnw verify`.

Releases: pushing a `v*` tag triggers `.github/workflows/release.yml`, which sets the version of all modules from the tag and runs `./mvnw -P release deploy` (GPG signing + Maven Central). The `pom.xml` versions stay at `-SNAPSHOT`.

## Modules

- `cloudscale-client`: plain Java client, no Spring Boot dependency (only `spring-web` + Jackson 3).
- `cloudscale-spring-boot-autoconfigure`: `CloudscaleProperties` (`cloudscale.*`) and `CloudscaleAutoConfiguration`, registered in `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`.
- `cloudscale-spring-boot-starter`: dependencies only. It publishes placeholder sources/javadoc jars (from `src/main/placeholder`) because Maven Central requires them.

## Architecture

- `CloudscaleClient` is the facade with one accessor per resource type (`servers()`, `volumes()`, ...) and a `Builder` (token, URL, timeouts, optional `RestClient.Builder`, optional request factory).
- `internal/ApiTransport` wraps `RestClient`. Paths are URI templates; ids and query values are always passed as URI variables so they get fully encoded (the API treats a literal `+` as space). Tag filters become `?tag:key=value` or a bare `?tag:key`. `get(URI)` only follows absolute URLs (pagination links) on the same origin as the base URL, because the bearer token is sent with every request.
- `resource/`: `ListResource` (list) → `ReadResource` (+ tag filter, get) → `CrudResource` (+ create, update, raw-map update, delete). Resources with extra actions or nested paths (servers, volumes, routers, custom images, pool members, objects user keys, metrics, project logs) add or define methods explicitly.
- `model/`: responses are records; requests are fluent classes whose private fields are serialized (`@JsonAutoDetect(fieldVisibility = ANY)`). `CloudscaleJson` configures snake_case naming, ignores unknown properties and omits `null` values. The raw `Map` update overload exists to send explicit `null`s.
- Errors: any 4xx/5xx becomes `CloudscaleApiException` (status code, `detail` if present, raw body).
- Auto-config: fails fast without a token (`cloudscale.api-token`, fallback env `CLOUDSCALE_API_TOKEN`), backs off with `cloudscale.enabled=false` or a user-defined `CloudscaleClient` bean, uses Spring Boot's `RestClient.Builder` if present and applies `CloudscaleClientBuilderCustomizer` beans.

Adding a resource means adding response records and request classes in `model/`, a resource class (usually extending `CrudResource`), an accessor in `CloudscaleClient`, a test and a README table row.

## Testing conventions

- Client tests bind `MockRestServiceServer` to a `RestClient.Builder` passed via `CloudscaleClient.builder().restClientBuilder(...)`, using JSON samples from the API docs. They make no real HTTP calls.
- Auto-configuration tests use `ApplicationContextRunner` with `AutoConfigurations.of(RestClientAutoConfiguration.class, CloudscaleAutoConfiguration.class)` and property values.
