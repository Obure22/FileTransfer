package Tpu_8K31.uploadingfiles.files;

import Tpu_8K31.uploadingfiles.storage.StorageService;
import Tpu_8K31.uploadingfiles.user.UserEntity;
import Tpu_8K31.uploadingfiles.user.UserRepository;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class FileUploadSystemService implements FileUploadService {

    private final UserRepository userRepository;
    private final StorageService storageService;
    private final FileRepository fileRepository;

    public FileUploadSystemService(UserRepository userRepository, StorageService storageService, FileRepository fileRepository) {
        this.userRepository = userRepository;
        this.storageService = storageService;
        this.fileRepository = fileRepository;
    }

    @Override
    public void fileUpload(MultipartFile file, String username, Long timeLength) {
        // Проверяем, есть ли пользователь
        UserEntity user = userRepository.findByUsername(username);

        String uniqueName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // Сохраняем файл на диск
        storageService.store(file, uniqueName);

        String url = "/api/files/" + uniqueName;

        // Сохраняем запись в базу
        FileEntity entity = new FileEntity(file.getOriginalFilename(),uniqueName, url, file.getSize(),timeLength);
        entity.setDeleteAt(entity.getUploadTime().plusMinutes(timeLength)); // в минутах
        entity.setOwner(user);
        try {
            fileRepository.save(entity);
        }
        catch(DataIntegrityViolationException e ){
            throw new FileUploadException("Файл не загружен");
        }

    }

    @Override
    public List<FileEntity> filesList(){
        return fileRepository.findAll();
    }

    @Override
    public List<FileEntity> getUserFiles(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            return List.of();
        }
        return user.getFiles();
    }

    @Override
    public ResponseEntity<Resource> fileGet(String uniqueFileName) {
        Resource file = storageService.loadAsResource(uniqueFileName);
        FileEntity fileEntity = fileRepository.findByUniqueFileName(uniqueFileName);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getUniqueFileName() + "\"")
                .body(file);
    }

    @Override
    public ResponseEntity<?> fileDelete(String uniqueFileName) {
        FileEntity file = fileRepository.findByUniqueFileName(uniqueFileName);
        if (file == null){
            return ResponseEntity.notFound().build();
        }
        fileRepository.delete(file);
        storageService.delete(uniqueFileName);
        return ResponseEntity.ok().build();
    }
}
