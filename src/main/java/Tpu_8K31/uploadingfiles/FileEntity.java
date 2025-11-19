package Tpu_8K31.uploadingfiles;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
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
    private LocalDateTime uploadTimeLength;

    // Каждый файл имеет владельца
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id") // колонка в таблице files
    private UserEntity owner;

    public FileEntity() {}

    public FileEntity(String filename,String uniqueFileName, String url, long size, LocalDateTime uploadTimeLength) {
        this.filename = filename;
        this.uniqueFileName = uniqueFileName;
        this.url = url;
        this.size = size;
        this.uploadTimeLength = uploadTimeLength;
    }

    public Long getId() { return id; }
    public String getFilename() { return filename; }
    public String getUrl() { return url; }
    public long getSize() { return size; }
    public LocalDateTime getUploadTime() { return uploadTime; }
    public UserEntity getOwner() { return owner; }
    public String getUniqueFilename(){return uniqueFileName;}
    public LocalDateTime getUploadTimeLength(){return uploadTimeLength;}

    public void setId(Long id) { this.id = id; }
    public void setFilename(String filename) { this.filename = filename; }
    public void setUrl(String url) { this.url = url; }
    public void setSize(long size) { this.size = size; }
    public void setUploadTime(LocalDateTime uploadTime) { this.uploadTime = uploadTime; }
    public void setOwner(UserEntity owner) { this.owner = owner; }
    public void setUniqueFileName(String uniqueFileName) { this.uniqueFileName = uniqueFileName; }
    public void setUploadTimeLength(LocalDateTime uploadTimeLength){this.uploadTimeLength = uploadTimeLength;}
}
