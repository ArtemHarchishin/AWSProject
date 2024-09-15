package com.akharchyshyn.aws.controller;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.akharchyshyn.aws.dto.ErrorDTO;
import com.akharchyshyn.aws.exception.ImageNotFoundException;
import com.akharchyshyn.aws.exception.InvalidImageDateException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class AWSErrorController {

    private final ErrorAttributes errorAttributes;

    @ExceptionHandler(InvalidImageDateException.class)
    public ResponseEntity<ErrorDTO> handleInvalidImageDateException(HttpServletRequest request) {
        return ResponseEntity.status(BAD_REQUEST)
            .body(buildErrorDto(request));
    }


    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleImageNotFoundException(HttpServletRequest request) {
        return ResponseEntity.status(BAD_REQUEST)
            .body(buildErrorDto(request));
    }

    private ErrorDTO buildErrorDto(HttpServletRequest request) {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        Throwable error = errorAttributes.getError(servletWebRequest);
        String message = getErrorMessage(error);
        return ErrorDTO.builder()
            .message(message)
            .build();
    }

    private String getErrorMessage(Throwable error) {
        String message = ofNullable(error)
            .map(Throwable::getMessage)
            .filter(StringUtils::hasLength)
            .orElse(null);

        if (nonNull(message)) {
            return nonNull(error.getCause()) && !StringUtils.hasLength(error.getCause().getMessage())
                ? error.getCause().getMessage()
                : message;
        }

        return null;

    }

}

