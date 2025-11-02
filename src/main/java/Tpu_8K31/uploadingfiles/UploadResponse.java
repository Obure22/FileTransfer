package Tpu_8K31.uploadingfiles;

public class UploadResponse {
    private String message;
    private String url;

    public UploadResponse(String message, String url) {
        this.message = message;
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }
}
