package io.ngine.cloudscale.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.json.JsonMapper;

/**
 * JSON mapping conventions of the cloudscale.ch API.
 * <p>
 * The client uses its own mapper so it is not affected by the application's Jackson
 * configuration: properties are snake_case, unknown properties are ignored so new API
 * attributes do not break existing clients, and {@code null} properties are not sent.
 */
public final class CloudscaleJson {

    private CloudscaleJson() {
    }

    public static JsonMapper createMapper() {
        return JsonMapper.builder()
            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .changeDefaultPropertyInclusion((incl) -> incl.withValueInclusion(JsonInclude.Include.NON_NULL))
            .build();
    }
}
