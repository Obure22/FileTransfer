package Tpu_8K31.uploadingfiles;

import Tpu_8K31.uploadingfiles.storage.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api") // все маршруты будут начинаться с /api
@CrossOrigin(origins = "*") // чтобы можно было обращаться с фронтенда
public class FileUploadController {

	private final StorageService storageService;

	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	// 📤 Загрузить файл
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		storageService.store(file);
		String downloadUrl = "/api/files/" + file.getOriginalFilename();
		return ResponseEntity.ok().body(
				new UploadResponse("Файл успешно загружен", downloadUrl)
		);
	}

	// 📋 Получить список всех файлов
	@GetMapping("/files")
	public List<String> listFiles() throws IOException {
		return storageService.loadAll()
				.map(path -> "/api/files/" + path.getFileName().toString())
				.collect(Collectors.toList());
	}

	// 📥 Скачать файл
	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
