package com.clothes.clothes.configs;

import java.security.SignatureException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.security.auth.login.AccountLockedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.clothes.clothes.exceptions.ConditionalException;
import com.clothes.clothes.vars.JsonResponses;
import com.clothes.clothes.vars.StringConsts;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    JsonResponses jsonResponses;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);

        return jsonResponses.ReturnErrorData(result, "Argumentos incorrectos");
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    protected ResponseEntity<?> notValid(HandlerMethodValidationException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);

        return jsonResponses.ReturnErrorData(result, "Argumentos incorrectos");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    protected ResponseEntity<?> notFound(NoResourceFoundException ex, HttpServletRequest request) {
        return jsonResponses.ReturnErrorMessage(
                "Recurso no encontrado",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    protected ResponseEntity<?> missingVariable(MissingPathVariableException ex, HttpServletRequest request) {
        return jsonResponses.ReturnErrorMessage(
                "Favor de ingresar los parametros necesarios",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<?> sqlException(DataIntegrityViolationException ex, HttpServletRequest request) {
        System.out.println("EXCEPCION SQL");
        System.out.println(ex.getMessage());

        return this.jsonResponses.ReturnErrorMessage(
                StringConsts.Expecion,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<?> jwtExpired(ExpiredJwtException ex, HttpServletRequest request) {
        return this.jsonResponses.ReturnErrorMessage(
                StringConsts.JwtExpired,
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ServletException.class)
    protected ResponseEntity<?> errorHandler(ServletException ex, HttpServletRequest request) {
        return this.jsonResponses.ReturnErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConditionalException.class)
    protected ResponseEntity<?> errorHandler(ConditionalException ex, HttpServletRequest request) {
        return this.jsonResponses.ReturnErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<?> badCredentials(BadCredentialsException ex, HttpServletRequest request) {
        return this.jsonResponses.ReturnErrorMessage(
                "Credenciales incorrectas",
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccountLockedException.class)
    protected ResponseEntity<?> accoutLocked(AccountLockedException ex, HttpServletRequest request) {
        return this.jsonResponses.ReturnErrorMessage(
                "Cuenta blockeada",
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(SignatureException.class)
    protected ResponseEntity<?> invalidJWT(SignatureException ex, HttpServletRequest request) {
        return this.jsonResponses.ReturnErrorMessage(
                "Token invalido",
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    protected ResponseEntity<?> malformedJwtException(MalformedJwtException ex, HttpServletRequest request) {
        return this.jsonResponses.ReturnErrorMessage(
                StringConsts.JwtNotValid,
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    protected ResponseEntity<?> authorizationDenied(AuthorizationDeniedException ex, HttpServletRequest request) {
        return this.jsonResponses.ReturnErrorMessage(
                "Permiso no valido",
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<?> noSuchElement(NoSuchElementException ex, HttpServletRequest request) {
        return this.jsonResponses.ReturnErrorMessage(
                ex.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<?> noSuchElement(IllegalArgumentException ex, HttpServletRequest request) {
        return this.jsonResponses.ReturnErrorMessage(
                ex.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<?> noSuchElement(HttpMessageNotReadableException ex, HttpServletRequest request) {
        return this.jsonResponses.ReturnErrorMessage(
                ex.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(io.jsonwebtoken.security.SignatureException.class)
    protected ResponseEntity<?> noSuchElement(io.jsonwebtoken.security.SignatureException ex,
            HttpServletRequest request) {
        return this.jsonResponses.ReturnErrorMessage(
                "JWT no valido",
                HttpStatus.NOT_FOUND);
    }

    // @ExceptionHandler(PrNotPending.class)
    // protected ResponseEntity<?> noSuchElement(PrNotPending ex, HttpServletRequest
    // request) {
    // return this.jsonResponses.ReturnErrorMessage(
    // ex.getMessage(),
    // HttpStatus.BAD_REQUEST
    // );
    // }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> generalException(Exception ex, HttpServletRequest request) {
        System.out.println("EXCEPCION");
        System.out.println(ex.getMessage());
        System.out.println(ex.getClass());
        // ex.printStackTrace();

        return this.jsonResponses.ReturnErrorMessage(
                StringConsts.Expecion,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}