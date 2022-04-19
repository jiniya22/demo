package me.jiniworld.demo.config;

import me.jiniworld.demo.domain.dto.response.BaseResponse;
import me.jiniworld.demo.util.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidInputException.class, ValidationException.class})
    protected ResponseEntity<BaseResponse> handleException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseResponse> handleException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream()
                .findFirst().map(ObjectError::getDefaultMessage).orElse("");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(message));
    }

}
