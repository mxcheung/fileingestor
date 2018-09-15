package org.baeldung.persistence.repo;

import org.baeldung.persistence.model.FileContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileContentRepository extends JpaRepository<FileContentEntity, Long> {

}