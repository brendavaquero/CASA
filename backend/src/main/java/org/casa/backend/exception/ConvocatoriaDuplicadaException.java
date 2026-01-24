package org.casa.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConvocatoriaDuplicadaException  extends RuntimeException {
    public ConvocatoriaDuplicadaException(String mensaje) {
        super(mensaje);
    }
}
