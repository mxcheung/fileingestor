package org.baeldung.service;

import java.util.List;
import java.util.Optional;

import org.baeldung.persistence.model.FileEntity;
import org.baeldung.persistence.repo.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    private final FileRepository fileRepository;

    @Autowired
    FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<FileEntity> findByBatchId(Long batchId) {
        LOGGER.info("Fetching files: {}", batchId);
        return fileRepository.findByBatchId(batchId);
    }

    
    public List<FileEntity> findByBatchIds(List<Long> batchIds) {
        LOGGER.info("Fetching files: {}", batchIds);
        return fileRepository.findByBatchIdIn(batchIds);
    }

    
    public FileEntity fetchFileEntity(Long fileId) {
        Optional<FileEntity> fileEntity = fileRepository.findById(fileId);
        return fileEntity.get();
    }

    public FileEntity save(FileEntity fileEntity) {
        return fileRepository.save(fileEntity);
    }

}