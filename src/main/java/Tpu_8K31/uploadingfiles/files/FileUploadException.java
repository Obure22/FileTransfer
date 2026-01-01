package Tpu_8K31.uploadingfiles.files;

public class FileUploadException extends RuntimeException {
    public FileUploadException(String message) {
        super(message);
    }
    public FileUploadException(String message, Throwable cause){
        super(message, cause);
    }
}
