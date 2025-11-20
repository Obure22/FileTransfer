package Tpu_8K31.uploadingfiles.filescleanup;

import Tpu_8K31.uploadingfiles.FileEntity;
import Tpu_8K31.uploadingfiles.FileRepository;
import Tpu_8K31.uploadingfiles.storage.StorageService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileCleanupService {
    private final FileRepository fileRepository;
    private final StorageService storageService;

    public FileCleanupService(FileRepository fileRepository, StorageService storageService) {
        this.fileRepository = fileRepository;
        this.storageService = storageService;
    }

    @Scheduled(fixedRate = 60000)
    public void deleteExpiredFiles(){
        List<FileEntity> expiredFiles = fileRepository.findByDeleteAtBefore(LocalDateTime.now());

        for (FileEntity file : expiredFiles){
            storageService.delete(file.getUniqueFilename());
            fileRepository.delete(file);
        }
    }
}
