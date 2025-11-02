package Tpu_8K31.uploadingfiles;

import Tpu_8K31.uploadingfiles.storage.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FileUploadController {

	private final StorageService storageService;
	private final FileRepository fileRepository;

	public FileUploadController(StorageService storageService, FileRepository fileRepository) {
		this.storageService = storageService;
		this.fileRepository = fileRepository;
	}

	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		storageService.store(file);

		String url = "/api/files/" + file.getOriginalFilename();
		FileEntity saved = fileRepository.save(
				new FileEntity(file.getOriginalFilename(), url, file.getSize())
		);

		return ResponseEntity.ok().body(saved);
	}

	@GetMapping("/files")
	public List<FileEntity> listFiles() {
		return fileRepository.findAll();
	}

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