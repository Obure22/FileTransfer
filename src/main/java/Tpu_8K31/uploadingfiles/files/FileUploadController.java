package Tpu_8K31.uploadingfiles.files;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000") // тут
public class FileUploadController {

	private final FileUploadService fileUploadService;

	public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

	// Загрузка файла с указанием владельца. С фронта подаётся кол-во времени до исчезновения файла в минутах.
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("timeLength")Long timeLength,
											 Principal principal) {
		String username = principal.getName();
		fileUploadService.fileUpload(file,username,timeLength);
		return ResponseEntity.ok().body("Файл загружен");
	}

	// Получить все файлы
	@GetMapping("/files")
	public List<FileEntity> listFiles() {
		return fileUploadService.filesList();
	}

	// Получить файлы конкретного пользователя
	@GetMapping("/files/user")
	public List<FilesListDTO> getFilesByUser(Principal principal) {
		String username = principal.getName();
		return fileUploadService.getUserFiles(username);
	}

	// Скачать файл
	@GetMapping("/files/{fileId}")
	public ResponseEntity<Resource> getFile(@PathVariable Long fileId) {
		return fileUploadService.fileGet(fileId);
	}

	// Удалить файл
	@DeleteMapping("/files/{fileId}")
	public ResponseEntity<?> deleteFile(@PathVariable Long fileId){
		return fileUploadService.fileDelete(fileId);
    }
}