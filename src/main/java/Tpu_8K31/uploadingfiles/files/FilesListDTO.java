package Tpu_8K31.uploadingfiles.files;

import java.time.LocalDateTime;

public record FilesListDTO(Long id, String filename, long size, LocalDateTime deleteAt) { }
