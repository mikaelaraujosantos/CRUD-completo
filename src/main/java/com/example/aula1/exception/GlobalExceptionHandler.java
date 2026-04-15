package com.example.aula1.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.aula1.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }




@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiResponse<Map<String, String>>>
        handleValidationErrors(
        MethodArgumentNotValidException ex){

    Map<String, String> erros =
            new HashMap<>();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(error -> {

            erros.put(
                error.getField(),
                error.getDefaultMessage()
            );

        });

    ApiResponse<Map<String, String>> response =
            new ApiResponse<>(
                    "error",
                    "Erro de validacao",
                    erros
            );

    return ResponseEntity
            .badRequest()
            .body(response);
}
    


}
