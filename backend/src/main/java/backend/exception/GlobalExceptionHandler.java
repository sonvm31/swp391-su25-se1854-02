package backend.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Thông báo nếu email đã tồn tại
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        if (ex.getMessage().toLowerCase().contains("email")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        
        if (ex.getMessage().toLowerCase().contains("username")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        if (ex.getMessage().toLowerCase().contains("phone")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation");
    }

    

}
