package Tpu_8K31.uploadingfiles.files;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {
    ResponseEntity<?>  fileUpload(MultipartFile file, String username, Long timeLength);
    List<FileEntity> filesList();
    List<FileEntity> getUserFiles(String username);
    ResponseEntity<Resource> fileGet(String uniqueFileName);
    ResponseEntity<?> fileDelete(String uniqueFileName);
}
