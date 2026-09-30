package io.ngine.cloudscale.client;

import org.jspecify.annotations.Nullable;

/**
 * Raised when the cloudscale.ch API answers with an HTTP error status (4xx or 5xx).
 */
public class CloudscaleApiException extends RuntimeException {

    private final int statusCode;

    private final String responseBody;

    public CloudscaleApiException(int statusCode, @Nullable String detail, String responseBody) {
        super("API Response Error (" + statusCode + "): " + (detail != null ? detail : responseBody));
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    /** The HTTP status code returned by the API. */
    public int getStatusCode() {
        return statusCode;
    }

    /** The raw response body, usually a JSON document describing the problem. */
    public String getResponseBody() {
        return responseBody;
    }

    public boolean isNotFound() {
        return statusCode == 404;
    }
}
