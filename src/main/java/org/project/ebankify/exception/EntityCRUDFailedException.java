package org.project.ebankify.exception;

public class EntityCRUDFailedException extends RuntimeException {
    public EntityCRUDFailedException(String message) {
        super(message);
    }
}
