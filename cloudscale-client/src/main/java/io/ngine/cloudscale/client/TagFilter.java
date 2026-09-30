package io.ngine.cloudscale.client;

import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * A tag filter clause used when listing taggable resources.
 * <p>
 * The API currently supports at most one clause per request, either matching resources
 * that have a tag with a given key ({@code tag:key}) or with a given key and value
 * ({@code tag:key=value}).
 *
 * @param key the tag key, without the {@code tag:} prefix
 * @param value the tag value, or {@code null} to match any value
 */
public record TagFilter(String key, @Nullable String value) {

    public TagFilter {
        Objects.requireNonNull(key, "key must not be null");
        if (key.startsWith("tag:")) {
            key = key.substring(4);
        }
        if (key.isEmpty()) {
            throw new IllegalArgumentException("key must not be empty");
        }
    }

    /** Matches resources having a tag with the given key, regardless of its value. */
    public static TagFilter hasKey(String key) {
        return new TagFilter(key, null);
    }

    /** Matches resources having a tag with the given key and value. */
    public static TagFilter of(String key, String value) {
        return new TagFilter(key, Objects.requireNonNull(value, "value must not be null"));
    }

    /** Parses a filter in the format {@code <key>=<value>} or {@code <key>}. */
    public static TagFilter parse(String filter) {
        int idx = filter.indexOf('=');
        return (idx < 0) ? hasKey(filter) : of(filter.substring(0, idx), filter.substring(idx + 1));
    }

    /** The query parameter name, e.g. {@code tag:project}. */
    public String parameterName() {
        return "tag:" + key;
    }
}
