package com.apelisser.algaposts.post_service.domain.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class EntityNotFoundException extends DomainException {

    @Serial
    private static final long serialVersionUID = -8679016028629326769L;
    private static final String MESSAGE_TEMPLATE = "%s entity resource not found.";

    private final Class<?> entity;

    public EntityNotFoundException(Class<?> entity) {
        super(String.format(MESSAGE_TEMPLATE, entity.getSimpleName()));
        this.entity = entity;
    }

    public EntityNotFoundException(String message, Class<?> entity) {
        super(message);
        this.entity = entity;
    }

    public EntityNotFoundException(String message, Throwable cause, Class<?> entity) {
        super(message, cause);
        this.entity = entity;
    }

    public String getEntityName() {
        return entity.getSimpleName();
    }

}
