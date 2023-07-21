package com.texturized.muus.domain.user.exception;

import javax.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(Long target) {
        super(target + " is not found");
    }

}
