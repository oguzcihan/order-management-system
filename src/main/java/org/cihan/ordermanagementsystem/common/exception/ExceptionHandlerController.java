package org.cihan.ordermanagementsystem.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorMessage> handleBusinessException(BusinessException ex, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ValidationException.class})
    public ResponseEntity<List<ErrorMessage>> handleValidationExceptions(Exception ex) {
        List<ErrorMessage> errors = new ArrayList<>();
        String message = "Validation Error";
        if (ex instanceof MethodArgumentNotValidException notValidException) {
            for (FieldError error : notValidException.getBindingResult().getFieldErrors()) {
                ErrorMessage errorMessage = new ErrorMessage(
                        new Date(),
                        message,
                        error.getDefaultMessage(),
                        error.getField()
                );
                errors.add(errorMessage);
            }
        } else if (ex instanceof ValidationException) {
            ErrorMessage errorMessage = new ErrorMessage(
                    new Date(),
                    message,
                    ex.getMessage(),
                    null
            );
            errors.add(errorMessage);
        }
        return ResponseEntity.badRequest().body(errors);
    }

}
