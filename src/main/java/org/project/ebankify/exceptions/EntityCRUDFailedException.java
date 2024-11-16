package org.project.ebankify.exceptions;

public class EntityCRUDFailedException extends RuntimeException {
    public EntityCRUDFailedException(String message) {
        super(message);
    }
}
