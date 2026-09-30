package io.ngine.cloudscale.client.model;

/**
 * Flavor of a load balancer, e.g. {@code lb-standard}.
 *
 * @param slug unique string identifying the flavor
 * @param name display name
 */
public record LoadBalancerFlavor(String slug, String name) {
}
