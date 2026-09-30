package io.ngine.cloudscale.client.model;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Usage metrics of object storage buckets.
 *
 * @param start start of the metrics time range (UTC)
 * @param end end of the metrics time range (UTC)
 * @param data one entry per bucket matching the query
 */
public record BucketMetrics(OffsetDateTime start, OffsetDateTime end, List<Data> data) {

    /**
     * Metrics of a single bucket.
     *
     * @param subject the bucket
     * @param timeSeries the intervals of the time range, currently always a single one
     */
    public record Data(Subject subject, List<Interval> timeSeries) {
    }

    /**
     * Identifies a bucket.
     *
     * @param name bucket name
     * @param objectsUserId identifier of the objects user owning the bucket
     */
    public record Subject(String name, String objectsUserId) {
    }

    /**
     * Usage within an interval.
     *
     * @param start start of the interval
     * @param end end of the interval
     * @param usage the usage within the interval
     */
    public record Interval(OffsetDateTime start, OffsetDateTime end, Usage usage) {
    }

    /**
     * Bucket usage values.
     *
     * @param requests number of requests
     * @param objectCount average object count
     * @param storageBytes average amount of data stored
     * @param receivedBytes total amount of data received
     * @param sentBytes total amount of data sent
     */
    public record Usage(Long requests, Long objectCount, Long storageBytes, Long receivedBytes, Long sentBytes) {
    }
}
