package org.baeldung.persistence.repo;

import java.util.List;

import org.baeldung.persistence.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

    List<FileEntity> findByBatchId(Long batchId);

    List<FileEntity> findByBatchIdIn(List<Long> batchIds);

}