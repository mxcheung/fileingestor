package org.baeldung.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.baeldung.BatchDTO;
import org.baeldung.FileDTO;
import org.baeldung.FileStatusType;
import org.baeldung.persistence.model.BatchEntity;
import org.baeldung.persistence.model.FileContentEntity;
import org.baeldung.persistence.model.FileEntity;
import org.baeldung.persistence.repo.BatchRepository;
import org.baeldung.persistence.repo.FileContentRepository;
import org.baeldung.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileStoreAndFowardService {

    private static final String QUEUED = FileStatusType.QUEUED.name();
    private static final String STORED = FileStatusType.STORED.name();

    private static final Logger LOGGER = LoggerFactory.getLogger(FileStoreAndFowardService.class);

    private final FileService fileService;

    private final FileContentRepository fileContentRepository;

    private BatchRepository batchRepository;  
    

    @Autowired
    public FileStoreAndFowardService( BatchRepository batchRepository,  FileService fileService,
            FileContentRepository fileContentRepository) {
        this.batchRepository = batchRepository;
        this.fileService = fileService;
        this.fileContentRepository = fileContentRepository;
    }

    public FileEntity storeAndFwd(MultipartFile multipartFile, Long batchId, boolean storeAndFwd) throws IOException, InterruptedException {
        FileEntity fileEntity = storeFile(multipartFile, batchId, storeAndFwd);
        return fileEntity;
    }

    private FileEntity storeFile(MultipartFile multipartFile, Long batchId, boolean storeAndFwd) throws IOException {
        String status = getFileStatus(storeAndFwd);
        String originalFileName = multipartFile.getOriginalFilename();
        LOGGER.info("Storing file {}", originalFileName);
        byte[] byteArr = null;
        byteArr = multipartFile.getBytes();
        FileEntity fileEntity = new FileEntity();
        FileContentEntity fileContentEntity = new FileContentEntity();
        fileEntity.setFileContentEntity(fileContentEntity);
        fileEntity.setOriginalFileName(originalFileName);
        byte[] compressedData = FileUtil.gzipCompress(byteArr);
        fileContentEntity.setFileContent(compressedData);
        fileEntity.setBatchId(batchId);
        fileEntity.setStatus(status);
        fileContentRepository.save(fileContentEntity);
        fileService.save(fileEntity);
        return fileEntity;
    }

    
    public FileEntity forwardFile(MultipartFile multipartFile, String targetFolder, String targetFileName) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();
        LOGGER.info("Storing file {}", originalFileName);
        byte[] byteArr = null;
        byteArr = multipartFile.getBytes();
        FileEntity fileEntity = new FileEntity();
        fileEntity.setOriginalFileName(originalFileName);
        byte[] compressedData = FileUtil.gzipCompress(byteArr);
        String targetPath = targetFolder + "//" + originalFileName;
        File targetFile = new File(targetPath);
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(compressedData);
        outStream.close();
        return fileEntity;
    }


    public byte[] getFile(String targetFolder, String targetFileName) throws IOException {
        LOGGER.info("Getting file {}", targetFileName);
        String targetPath = targetFolder + "//" + targetFileName;
        File targetFile = new File(targetPath);
        InputStream is = new FileInputStream(targetFile);
        byte[] compressedData = IOUtils.toByteArray(is);
        byte[] uncompressedData = FileUtil.gzipUncompress(compressedData);
        return uncompressedData;
    }


    
    
    public String getFileStatus(boolean storeAndFwd) {
        String status = storeAndFwd ? QUEUED : STORED;
        return status;
    }

    public BatchDTO storeAndFwd(MultipartFile[] mpFiles, Long batchId, boolean storeAndFwd) throws IOException, InterruptedException {
        BatchEntity batchEntity = findOrCreate(batchId);
        Long newBatchId = batchEntity.getId();
        BatchDTO batchDTO = new BatchDTO();
        batchDTO.setId(newBatchId);
        List<FileEntity> fileEntitys = new ArrayList<FileEntity>();
        for (MultipartFile file : mpFiles) {
            FileEntity fileEntity = storeAndFwd(file, newBatchId, storeAndFwd);
            fileEntitys.add(fileEntity);
        }
        batchDTO.setFiles(convert(fileEntitys));
        return batchDTO;
    }

    public BatchEntity findOrCreate(Long batchId) {
        BatchEntity batchEntity = null;
        if (batchId != null) {
            batchEntity = batchRepository.findById(batchId).get();
        }
        if (batchEntity == null) {
            batchEntity = new BatchEntity();
            batchEntity = batchRepository.save(batchEntity);
        }
        return batchEntity;
    }

    public List<FileDTO> convert(List<FileEntity> fileEntitys) {
        List<FileDTO> fileDTOs = new ArrayList<FileDTO>();
        for (FileEntity fileEntity : fileEntitys) {
            FileDTO fileDTO = new FileDTO();
            fileDTOs.add(fileDTO);
        }
        return fileDTOs;
    }

}