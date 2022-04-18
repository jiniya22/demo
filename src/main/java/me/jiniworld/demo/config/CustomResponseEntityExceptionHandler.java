package me.jiniworld.demo.config;

import me.jiniworld.demo.domain.dto.response.BaseResponse;
import me.jiniworld.demo.util.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    protected ResponseEntity<BaseResponse> invalidInputException(InvalidInputException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(e.getMessage()));
    }

}
