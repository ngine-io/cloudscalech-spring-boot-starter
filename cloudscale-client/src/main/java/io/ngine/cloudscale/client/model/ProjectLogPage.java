package io.ngine.cloudscale.client.model;

import java.util.List;

import org.jspecify.annotations.Nullable;

/**
 * A page of project audit log records.
 *
 * @param next URL of the next page, {@code null} if there are no more results right now
 * @param pollMore URL to poll for entries added later
 * @param results the audit log records of this page
 */
public record ProjectLogPage(@Nullable String next, @Nullable String pollMore, List<ProjectLogRecord> results) {
}
