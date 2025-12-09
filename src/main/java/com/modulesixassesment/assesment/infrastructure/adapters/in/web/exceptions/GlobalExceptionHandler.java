package com.modulesixassesment.assesment.infrastructure.adapters.in.web.exceptions;

import com.modulesixassesment.assesment.infrastructure.adapters.in.web.exceptions.custom.BadRequestException;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.exceptions.custom.ConflictException;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.exceptions.custom.NoContentException;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.exceptions.custom.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>>  handleGenericException(Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("statusEvent", HttpStatus.valueOf(500));
        response.put("error", "Internal Server Error");
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(500));
    }

    // 204 - Not Content
    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Map<String, Object>> handleNotContentException(Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("statusEvent", HttpStatus.valueOf(204));
        response.put("error", "Not Content");
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(204));
    }

    // 400 - Bad Request
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequestException(Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("statusEvent", HttpStatus.valueOf(400));
        response.put("error", "Bad Request");
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(400));
    }

    // 404 - Not Found
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("statusEvent", HttpStatus.valueOf(404));
        response.put("error", "Not Found");
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(404));
    }

    // 409 - Conflict
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String, Object>> handlerConflictException(Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("statusEvent", HttpStatus.valueOf(409));
        response.put("error", "Conflict");
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(409));
    }

    private ProblemDetail build(HttpStatus status, String detail, String type) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setType(URI.create(type));
        problem.setTitle(status.getReasonPhrase());
        problem.setProperty("timestamp", LocalDateTime.now());
        problem.setProperty("traceId", UUID.randomUUID().toString());
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .reduce("", (a, b) -> a + "; " + b);

        return build(
                HttpStatus.BAD_REQUEST,
                message,
                "https://hu3.com/errors/validation"
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleNotFound(EntityNotFoundException ex) {
        return build(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                "https://hu3.com/errors/not-found"
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleIntegrity(DataIntegrityViolationException ex) {
        return build(
                HttpStatus.CONFLICT,
                "Error de integridad en la base de datos.",
                "https://hu3.com/errors/integrity"
        );
    }



}