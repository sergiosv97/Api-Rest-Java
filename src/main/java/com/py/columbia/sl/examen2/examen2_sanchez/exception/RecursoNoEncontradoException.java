
package com.py.columbia.sl.examen2.examen2_sanchez.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoNoEncontradoException extends RuntimeException{
    public RecursoNoEncontradoException(String message) {
        super (message);
    }
    public RecursoNoEncontradoException(String message,Throwable cause){
        super (message,cause);
    }
}
