package me.jiniworld.demo.util;

public class InvalidInputException extends RuntimeException {

    private static final long serialVersionUID = -4708623386108060912L;

    public InvalidInputException() {
        super("필수 입력값을 잘못 입력하였습니다.");
    }

    public InvalidInputException(String msg) {
        super(msg);
    }

}