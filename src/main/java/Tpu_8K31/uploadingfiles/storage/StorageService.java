package Tpu_8K31.uploadingfiles.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
	void init();
	void store(MultipartFile file, String uniqueName);
	Stream<Path> loadAll();
	Path load(String filename);
	Resource loadAsResource(String filename);
	void deleteAll();
	void delete(String uniqueFileName);
}