package Tpu_8K31.uploadingfiles;

import Tpu_8K31.uploadingfiles.storage.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // тут
public class FileUploadController {

	private final StorageService storageService;
	private final FileRepository fileRepository;
	private final UserRepository userRepository;

	public FileUploadController(StorageService storageService, FileRepository fileRepository, UserRepository userRepository) {
		this.storageService = storageService;
		this.fileRepository = fileRepository;
		this.userRepository = userRepository;
	}

	// Загрузка файла с указанием владельца
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("username") String username) {
		// Проверяем, есть ли пользователь
		UserEntity user = userRepository.findByUsername(username);
		if (user == null) {
			return ResponseEntity.badRequest().body("Пользователь не найден: " + username);
		}

		String uniqueName = UUID.randomUUID() + "_" + file.getOriginalFilename();

		// Сохраняем файл на диск
		storageService.store(file, uniqueName);

		String url = "/api/files/" + uniqueName;

		// Сохраняем запись в базу
		FileEntity entity = new FileEntity(file.getOriginalFilename(),uniqueName, url, file.getSize());
		entity.setOwner(user);
		fileRepository.save(entity);

		return ResponseEntity.ok(entity);
	}

	// Получить все файлы
	@GetMapping("/files")
	public List<FileEntity> listFiles() {
		return fileRepository.findAll();
	}

	// Получить файлы конкретного пользователя
	@GetMapping("/files/user/{username}")
	public List<FileEntity> getFilesByUser(@PathVariable String username) {
		UserEntity user = userRepository.findByUsername(username);
		if (user == null) {
			return List.of();
		}
		return user.getFiles();
	}

	// Скачать файл
	@GetMapping("/files/{uniqueFileName:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String uniqueFileName) {
		Resource file = storageService.loadAsResource(uniqueFileName);
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}