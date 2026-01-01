package Tpu_8K31.uploadingfiles.exception;

import Tpu_8K31.uploadingfiles.files.FileUploadException;
import Tpu_8K31.uploadingfiles.security.LoginException;
import Tpu_8K31.uploadingfiles.user.UserCreationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<String> handleUserCreationException(UserCreationException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> handleLoginException(LoginException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<String> handleFileUploadException(FileUploadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
