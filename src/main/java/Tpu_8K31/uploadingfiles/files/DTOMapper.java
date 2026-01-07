package Tpu_8K31.uploadingfiles.files;

import org.springframework.stereotype.Component;

@Component
public class DTOMapper {
    public FilesListDTO createDTO(FileEntity fileEntity){
        return new FilesListDTO(fileEntity.getId(),fileEntity.getFilename(),fileEntity.getSize(),fileEntity.getDeleteAt());
    }
}
