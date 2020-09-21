package com.kboxglobal.naydogdu.assignment.exception;

public class ViewNotFoundException extends RuntimeException {
    public ViewNotFoundException(Long id) {
        super("Could not find profile view " + id);
    }
}
