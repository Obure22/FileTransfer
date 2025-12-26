package Tpu_8K31.uploadingfiles.files;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    FileEntity findByFilename(String filename);
    FileEntity findByUniqueFileName(String uniqueFileName);
    List<FileEntity> findByDeleteAtBefore(LocalDateTime time);
}