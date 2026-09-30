package io.ngine.cloudscale.client.resource;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.ProjectLogPage;
import io.ngine.cloudscale.client.model.ProjectLogRecord;
import org.jspecify.annotations.Nullable;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Project audit logs, see <a href="https://www.cloudscale.ch/en/api/v1#project-logs">API docs</a>.
 * <p>
 * Results are paginated: follow {@link ProjectLogPage#next()} until it is {@code null} to
 * read all currently available entries, then later follow {@link ProjectLogPage#pollMore()}
 * to receive entries added in the meantime.
 */
public class ProjectLogResource {

    private static final String PATH = "/project-logs";

    private final ApiTransport transport;

    public ProjectLogResource(ApiTransport transport) {
        this.transport = transport;
    }

    /** Gets the first page of all audit log records. */
    public ProjectLogPage list() {
        return list(null, null);
    }

    /**
     * Gets the first page of audit log records within the given time range.
     * @param start only records with timestamp &gt;= start, or {@code null}
     * @param end only records with timestamp &lt; end, or {@code null}
     */
    public ProjectLogPage list(@Nullable OffsetDateTime start, @Nullable OffsetDateTime end) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (start != null) {
            params.add("start", start.toString());
        }
        if (end != null) {
            params.add("end", end.toString());
        }
        return transport.get(PATH, ProjectLogPage.class, params);
    }

    /** Follows a {@code next} or {@code poll_more} URL of a previous page. */
    public ProjectLogPage follow(String url) {
        return transport.get(URI.create(url), ProjectLogPage.class);
    }

    /**
     * Gets the next page, or {@code null} if all currently available entries have been read.
     */
    public @Nullable ProjectLogPage next(ProjectLogPage page) {
        return (page.next() != null) ? follow(page.next()) : null;
    }

    /**
     * Reads all currently available audit log records within the given time range,
     * following the pagination links.
     */
    public List<ProjectLogRecord> listAll(@Nullable OffsetDateTime start, @Nullable OffsetDateTime end) {
        List<ProjectLogRecord> records = new ArrayList<>();
        ProjectLogPage page = list(start, end);
        while (page != null) {
            records.addAll(page.results());
            page = next(page);
        }
        return records;
    }
}
