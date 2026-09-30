package io.ngine.cloudscale.client;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class TagFilterTests {

    @Test
    void parseKeyOnly() {
        assertThat(TagFilter.parse("env")).isEqualTo(new TagFilter("env", null));
    }

    @Test
    void parseKeyAndValue() {
        assertThat(TagFilter.parse("env=prod")).isEqualTo(new TagFilter("env", "prod"));
    }

    @Test
    void parseEmptyValue() {
        assertThat(TagFilter.parse("env=")).isEqualTo(new TagFilter("env", ""));
    }

    @Test
    void prefixIsStripped() {
        assertThat(TagFilter.parse("tag:env=prod").parameterName()).isEqualTo("tag:env");
    }

    @Test
    void emptyKeyIsRejected() {
        assertThatIllegalArgumentException().isThrownBy(() -> TagFilter.parse("=prod"));
    }
}
