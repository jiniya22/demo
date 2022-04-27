package me.jiniworld.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2638205698917010982L;

    public ResourceNotFoundException() {
        super("리소스가 없습니다.");
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }

}