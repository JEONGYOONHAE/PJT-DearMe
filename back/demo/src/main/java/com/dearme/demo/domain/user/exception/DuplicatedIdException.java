package com.dearme.demo.domain.user.exception;

public class DuplicatedIdException extends RuntimeException{
    public DuplicatedIdException(String id) {
        super(id + "는 존재하는 id 입니다.");
    }
}
