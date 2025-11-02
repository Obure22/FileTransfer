package Tpu_8K31.uploadingfiles;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String url;
    private long size;
    private LocalDateTime uploadTime = LocalDateTime.now();

    // Каждый файл имеет владельца
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id") // колонка в таблице files
    private UserEntity owner;

    public FileEntity() {}

    public FileEntity(String filename, String url, long size) {
        this.filename = filename;
        this.url = url;
        this.size = size;
    }

    public Long getId() { return id; }
    public String getFilename() { return filename; }
    public String getUrl() { return url; }
    public long getSize() { return size; }
    public LocalDateTime getUploadTime() { return uploadTime; }
    public UserEntity getOwner() { return owner; }

    public void setId(Long id) { this.id = id; }
    public void setFilename(String filename) { this.filename = filename; }
    public void setUrl(String url) { this.url = url; }
    public void setSize(long size) { this.size = size; }
    public void setUploadTime(LocalDateTime uploadTime) { this.uploadTime = uploadTime; }
    public void setOwner(UserEntity owner) { this.owner = owner; }
}
