package io.ngine.cloudscale.autoconfigure;

import io.ngine.cloudscale.client.CloudscaleClient;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;

/**
 * Auto-configuration of the {@link CloudscaleClient}.
 * <p>
 * If Spring Boot provides a {@link RestClient.Builder} (via
 * {@code spring-boot-starter-restclient}), it is used as base so that observability and
 * other {@code RestClientCustomizer}s apply to cloudscale.ch API calls as well.
 */
@AutoConfiguration(afterName = "org.springframework.boot.restclient.autoconfigure.RestClientAutoConfiguration")
@ConditionalOnClass({ CloudscaleClient.class, RestClient.class })
@ConditionalOnBooleanProperty(name = "cloudscale.enabled", matchIfMissing = true)
@EnableConfigurationProperties(CloudscaleProperties.class)
public final class CloudscaleAutoConfiguration {

    static final String API_TOKEN_ENV = "CLOUDSCALE_API_TOKEN";

    @Bean
    @ConditionalOnMissingBean
    CloudscaleClient cloudscaleClient(CloudscaleProperties properties, Environment environment,
            ObjectProvider<RestClient.Builder> restClientBuilder,
            ObjectProvider<CloudscaleClientBuilderCustomizer> customizers) {
        CloudscaleClient.Builder builder = CloudscaleClient.builder()
            .apiToken(resolveApiToken(properties, environment))
            .apiUrl(properties.getApiUrl())
            .connectTimeout(properties.getConnectTimeout())
            .readTimeout(properties.getReadTimeout());
        restClientBuilder.ifAvailable(builder::restClientBuilder);
        customizers.orderedStream().forEach((customizer) -> customizer.customize(builder));
        return builder.build();
    }

    private static String resolveApiToken(CloudscaleProperties properties, Environment environment) {
        String token = properties.getApiToken();
        if (!StringUtils.hasText(token)) {
            token = environment.getProperty(API_TOKEN_ENV);
        }
        if (!StringUtils.hasText(token)) {
            throw new IllegalStateException("No cloudscale.ch API token configured. Set the 'cloudscale.api-token' "
                    + "property or the " + API_TOKEN_ENV + " environment variable, "
                    + "or disable the client with 'cloudscale.enabled=false'.");
        }
        return token;
    }
}
