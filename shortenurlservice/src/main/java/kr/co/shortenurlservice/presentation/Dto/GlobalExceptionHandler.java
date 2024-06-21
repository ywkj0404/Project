package kr.co.shortenurlservice.presentation.Dto;

import kr.co.shortenurlservice.domain.KeyNotFoundException;
import kr.co.shortenurlservice.domain.LackOfShortenUrlKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LackOfShortenUrlKeyException.class)
    public ResponseEntity<String> handleLackOfShortenUrlKeyException() {
        return new ResponseEntity<>("단축 URL 자원이 부족합니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(KeyNotFoundException.class)
    public ResponseEntity<String> handleKeyNotFoundException() {
        return new ResponseEntity<>("단축 URL을 찾지 못했습니다.", HttpStatus.BAD_REQUEST);
    }

}
