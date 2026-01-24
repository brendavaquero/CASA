package org.casa.backend.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConvocatoriaDuplicadaException.class)
    public ResponseEntity<Map<String, Object>> handleConvocatoriaDuplicada(
            ConvocatoriaDuplicadaException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "status", 409,
                        "error", "Convocatoria duplicada",
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }
    @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Map<String, Object>> handleResourceNotFound(
                ResourceNotFoundException ex
        ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "status", 404,
                        "error", "Recurso no encontrado",
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
        }
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<Map<String, Object>> handleIllegalArgument(
                IllegalArgumentException ex
        ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "status", 400,
                        "error", "Datos inválidos",
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
        }
        @ExceptionHandler(Exception.class)
        public ResponseEntity<Map<String, Object>> handleGeneralException(
                Exception ex
        ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "status", 500,
                        "error", "Error interno del servidor",
                        "message", "Ocurrió un error inesperado",
                        "timestamp", LocalDateTime.now()
                ));
        }
@ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(
        DataIntegrityViolationException ex) {

    String message = "Error de integridad de datos";

    if (ex.getRootCause() != null &&
        ex.getRootCause().getMessage().contains("usuario_correo_key")) {
        message = "El correo ya está registrado";
    }

    return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(Map.of(
                    "status", 409,
                    "error", "Conflicto de datos",
                    "message", message,
                    "timestamp", LocalDateTime.now()
            ));
}

    @ExceptionHandler(ResponseStatusException.class)
        public ResponseEntity<Map<String, Object>> handleResponseStatusException(
                ResponseStatusException ex
        ) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(Map.of(
                        "status", ex.getStatusCode().value(),
                        "error", ex.getReason(),
                        "message", ex.getReason(),
                        "timestamp", LocalDateTime.now()
                ));
        }
}
