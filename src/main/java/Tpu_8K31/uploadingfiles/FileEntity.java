package Tpu_8K31.uploadingfiles;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String uniqueFileName;
    private String url;
    private long size;
    private LocalDateTime uploadTime = LocalDateTime.now();
    private Long uploadTimeLength; // в минутах
    private LocalDateTime deleteAt;

    // Каждый файл имеет владельца
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id") // колонка в таблице files
    private UserEntity owner;

    public FileEntity() {}

    public FileEntity(String filename,String uniqueFileName, String url, long size, Long uploadTimeLength) {
        this.filename = filename;
        this.uniqueFileName = uniqueFileName;
        this.url = url;
        this.size = size;
        this.uploadTimeLength = uploadTimeLength;
    }
}
