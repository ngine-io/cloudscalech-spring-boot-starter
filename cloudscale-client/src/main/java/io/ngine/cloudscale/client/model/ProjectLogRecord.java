package io.ngine.cloudscale.client.model;

import java.time.OffsetDateTime;
import java.util.Map;

import org.jspecify.annotations.Nullable;

/**
 * An audit log record. The contents of {@code action} and {@code message} are not a
 * stable part of the API.
 *
 * @param ipAddress source address of the request, {@code null} for staff or automated tasks
 * @param action internal name of the operation
 * @param message human-readable description
 * @param timestamp date and time of the operation
 * @param actor who performed the operation
 * @param subject what was affected by the operation
 */
public record ProjectLogRecord(@Nullable String ipAddress, String action, String message, OffsetDateTime timestamp,
        Actor actor, Subject subject) {

    /**
     * The entity that performed an operation. Exactly one attribute is set.
     *
     * @param user a user acting via the control panel
     * @param apiToken an API token
     * @param system set (to an empty object) if an automated task performed the operation
     * @param admin set (to an empty object) if cloudscale.ch staff performed the operation
     */
    public record Actor(@Nullable UserStub user, @Nullable ApiTokenStub apiToken,
            @Nullable Map<String, Object> system, @Nullable Map<String, Object> admin) {

        public boolean isSystem() {
            return system != null;
        }

        public boolean isAdmin() {
            return admin != null;
        }
    }

    /**
     * The entity affected by an operation or containing the affected object. Exactly one
     * attribute is set.
     *
     * @param organization the organization
     * @param user the user
     * @param project the project
     */
    public record Subject(@Nullable OrganizationStub organization, @Nullable UserStub user,
            @Nullable ProjectStub project) {
    }

    /** @param email email address of the user account */
    public record UserStub(String email) {
    }

    /**
     * @param uuid unique identifier of the API token
     * @param description label of the API token
     */
    public record ApiTokenStub(String uuid, @Nullable String description) {
    }

    /**
     * @param href URL of the organization
     * @param uuid unique identifier of the organization
     * @param name name of the organization
     */
    public record OrganizationStub(@Nullable String href, String uuid, String name) {
    }

    /**
     * @param uuid unique identifier of the project
     * @param name name of the project
     */
    public record ProjectStub(String uuid, String name) {
    }
}
