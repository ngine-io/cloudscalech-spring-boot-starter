package io.ngine.cloudscale.autoconfigure;

import java.time.Duration;

import io.ngine.cloudscale.client.CloudscaleClient;
import org.junit.jupiter.api.Test;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.restclient.autoconfigure.RestClientAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

class CloudscaleAutoConfigurationTests {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(RestClientAutoConfiguration.class, CloudscaleAutoConfiguration.class));

    @Test
    void createsClientWhenTokenIsConfigured() {
        contextRunner.withPropertyValues("cloudscale.api-token=secret").run((context) -> {
            assertThat(context).hasSingleBean(CloudscaleClient.class);
            assertThat(context.getBean(CloudscaleClient.class).servers()).isNotNull();
        });
    }

    @Test
    void fallsBackToEnvironmentVariableName() {
        contextRunner.withPropertyValues("CLOUDSCALE_API_TOKEN=secret")
            .run((context) -> assertThat(context).hasSingleBean(CloudscaleClient.class));
    }

    @Test
    void failsWithHelpfulMessageWithoutToken() {
        contextRunner.run((context) -> assertThat(context).hasFailed()
            .getFailure()
            .rootCause()
            .hasMessageContaining("cloudscale.api-token")
            .hasMessageContaining("CLOUDSCALE_API_TOKEN"));
    }

    @Test
    void canBeDisabled() {
        contextRunner.withPropertyValues("cloudscale.enabled=false")
            .run((context) -> assertThat(context).doesNotHaveBean(CloudscaleClient.class));
    }

    @Test
    void worksWithoutSpringBootRestClientBuilder() {
        new ApplicationContextRunner().withConfiguration(AutoConfigurations.of(CloudscaleAutoConfiguration.class))
            .withPropertyValues("cloudscale.api-token=secret")
            .run((context) -> assertThat(context).hasSingleBean(CloudscaleClient.class));
    }

    @Test
    void bindsProperties() {
        contextRunner
            .withPropertyValues("cloudscale.api-token=secret", "cloudscale.api-url=http://localhost:8080/v1",
                    "cloudscale.connect-timeout=2s", "cloudscale.read-timeout=5m")
            .run((context) -> {
                CloudscaleProperties properties = context.getBean(CloudscaleProperties.class);
                assertThat(properties.getApiUrl()).isEqualTo("http://localhost:8080/v1");
                assertThat(properties.getConnectTimeout()).isEqualTo(Duration.ofSeconds(2));
                assertThat(properties.getReadTimeout()).isEqualTo(Duration.ofMinutes(5));
            });
    }

    @Test
    void appliesCustomizers() {
        contextRunner.withPropertyValues("cloudscale.api-token=secret")
            .withUserConfiguration(CustomizerConfiguration.class)
            .run((context) -> assertThat(context.getBean(CustomizerConfiguration.class).called).isTrue());
    }

    @Test
    void backsOffForUserDefinedClient() {
        contextRunner.withUserConfiguration(UserClientConfiguration.class)
            .run((context) -> assertThat(context).getBean(CloudscaleClient.class)
                .isSameAs(context.getBean(UserClientConfiguration.class).client));
    }

    @Configuration(proxyBeanMethods = false)
    static class CustomizerConfiguration {

        boolean called;

        @Bean
        CloudscaleClientBuilderCustomizer customizer() {
            return (builder) -> {
                this.called = true;
                builder.userAgent("my-app");
            };
        }
    }

    @Configuration(proxyBeanMethods = false)
    static class UserClientConfiguration {

        final CloudscaleClient client = CloudscaleClient.builder().apiToken("mine").build();

        @Bean
        CloudscaleClient cloudscaleClient() {
            return client;
        }
    }
}
