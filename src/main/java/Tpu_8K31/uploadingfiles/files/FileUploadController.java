package Tpu_8K31.uploadingfiles.files;

import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("username") String username, @RequestParam("timeLength")Long timeLength) {

		try{
			fileUploadService.fileUpload(file,username,timeLength);
			return ResponseEntity.ok().body("Файл загружен");
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body("Файл не загружен");
		}

	}

	// Получить все файлы
	@GetMapping("/files")
	public List<FileEntity> listFiles() {
		return fileUploadService.filesList();
	}

	// Получить файлы конкретного пользователя
	@GetMapping("/files/user/{username}")
	public List<FileEntity> getFilesByUser(@PathVariable String username) {
		return fileUploadService.getUserFiles(username);
	}

	// Скачать файл
	@GetMapping("/files/{uniqueFileName:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String uniqueFileName) {
		return fileUploadService.fileGet(uniqueFileName);
	}

	// Удалить файл
	@DeleteMapping("/files/{uniqueFileName:.+}")
	public ResponseEntity<?> deleteFile(@PathVariable String uniqueFileName){
		return fileUploadService.fileDelete(uniqueFileName);
    }
}