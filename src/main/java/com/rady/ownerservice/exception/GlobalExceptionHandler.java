package com.rady.ownerservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Mono<ProblemDetail>handleConflict(
            DataIntegrityViolationException ex, ServerWebExchange exchange) {

        log.warn("Conflict path={}",exchange.getRequest().getPath(), ex);

        String detail = ex.getMessage() !=null ? ex.getMessage() : ex.toString();

        return Mono.just(problem(exchange, HttpStatus.CONFLICT,
                "Conflict", detail, "/error/conflict"));
    }


    @ExceptionHandler(BadRequestException.class)
    public Mono<ProblemDetail>handleBadRequest(
            BadRequestException ex, ServerWebExchange exchange) {

        log.warn("Bad Request. path={}",exchange.getRequest().getPath(), ex);
        return Mono.just(problem(exchange, HttpStatus.BAD_REQUEST,
                "Bad Request", ex.getMessage(),"/error/bad-request"));
    }


    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ProblemDetail>handleValidation(WebExchangeBindException ex, ServerWebExchange exchange) {
        log.warn("WebClient response error. path={}",exchange.getRequest().getPath(), ex);

        String message = "Validation Failed.";
        if (!ex.getAllErrors().isEmpty() && ex.getAllErrors().getFirst().getDefaultMessage() != null) {
            message = ex.getAllErrors().getFirst().getDefaultMessage();
        }

        return Mono.just(problem(exchange, HttpStatus.BAD_REQUEST,
                "Validation Error",message, "/error/validation-error"));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ProblemDetail>handleGeneric(Exception ex, ServerWebExchange exchange) {
        log.error(ex.getMessage(),exchange.getRequest().getPath(), ex);

        return Mono.just(problem(exchange,HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error", "An unexpected error occurred.", "/error/internal-server-error"));
    }

    private ProblemDetail problem(ServerWebExchange exchange, HttpStatus status,
                                  String title, String detail, String typePath) {

        ProblemDetail pd=ProblemDetail.forStatusAndDetail(status, detail);
        pd.setTitle(title);
        pd.setType(URI.create(typePath));

        pd.setInstance(URI.create(exchange.getRequest().getPath().value()));

        pd.setProperty("timestamp: ", Instant.now().toString());
        pd.setProperty("trace Id: ", exchange.getRequest().getId());

        return pd;
    }
}
