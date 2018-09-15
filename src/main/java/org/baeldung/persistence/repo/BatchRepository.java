package org.baeldung.persistence.repo;

import org.baeldung.persistence.model.BatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<BatchEntity, Long> {

}