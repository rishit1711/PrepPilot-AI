package com.example.PrepPilot.AI.advices;

import com.example.PrepPilot.AI.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleException(DuplicateEmailException ex, HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Conflict")
                .path(request.getRequestURI())
                .timeStamp(LocalDateTime.now())
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);


    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException ex,HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder()
                        .error("Not Found")
                                .status(404)
                                        .path(request.getRequestURI())
                                                .message(ex.getMessage())
                                                        .timeStamp(LocalDateTime.now()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

    }

    @ExceptionHandler(InvalidResumeException.class)
    public ResponseEntity<ErrorResponse> handleException(InvalidResumeException ex,HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Invalid Resume")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleException(UnauthorizedException ex,HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Forbidden")
                .status(HttpStatus.FORBIDDEN.value())
                .path(request.getRequestURI())
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleException(AlreadyExistException ex,HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Conflict")
                .status(HttpStatus.CONFLICT.value())
                .path(request.getRequestURI())
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now()).build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServerErrorException.InternalServerError ex, HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Server Issue")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequestURI())
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now()).build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }




}
