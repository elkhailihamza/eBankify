package org.project.ebankify.exceptions;

public class EntityDataConflictException extends RuntimeException {
    public EntityDataConflictException(String message) {
        super(message);
    }
}
