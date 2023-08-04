package kr.texturized.muus.domain.exception;

import javax.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(Long target) {
        super(target + " is not found");
    }

}
