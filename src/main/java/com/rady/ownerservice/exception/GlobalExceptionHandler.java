package com.rady.ownerservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public Mono<ProblemDetail>handleBadRequest(
            BadRequestException ex, ServerWebExchange exchange) {

        log.warn("Bad Request. path={}",exchange.getRequest().getPath(), ex);
        return Mono.just(problem(exchange, HttpStatus.BAD_REQUEST,
                "Bad Request", ex.getMessage(),"/error/bad-request"));
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
