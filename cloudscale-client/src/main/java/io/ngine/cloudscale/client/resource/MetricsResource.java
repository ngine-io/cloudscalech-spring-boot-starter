package io.ngine.cloudscale.client.resource;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import io.ngine.cloudscale.client.internal.ApiTransport;
import io.ngine.cloudscale.client.model.BucketMetrics;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/** Usage metrics, see <a href="https://www.cloudscale.ch/en/api/v1#metrics">API docs</a>. */
public class MetricsResource {

    private final ApiTransport transport;

    public MetricsResource(ApiTransport transport) {
        this.transport = transport;
    }

    /**
     * Gets the metrics of all buckets.
     * @param start first day of the time range (inclusive, Europe/Zurich)
     * @param end last day of the time range (inclusive, Europe/Zurich)
     */
    public BucketMetrics buckets(LocalDate start, LocalDate end) {
        return buckets(start, end, List.of(), List.of());
    }

    /**
     * Gets the metrics of buckets, filtered by bucket name and/or owner. Filters of the same
     * kind are combined with OR, different kinds with AND.
     * @param start first day of the time range (inclusive, Europe/Zurich)
     * @param end last day of the time range (inclusive, Europe/Zurich)
     * @param bucketNames bucket names to include, empty for all
     * @param objectsUserIds bucket owners to include, empty for all
     */
    public BucketMetrics buckets(LocalDate start, LocalDate end, Collection<String> bucketNames,
            Collection<String> objectsUserIds) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("start", start.toString());
        params.add("end", end.toString());
        bucketNames.forEach((name) -> params.add("bucket_name", name));
        objectsUserIds.forEach((id) -> params.add("objects_user_id", id));
        return transport.get("/metrics/buckets", BucketMetrics.class, params);
    }
}
