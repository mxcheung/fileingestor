package org.baeldung.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Batch Model representation for Batch entity.
 * 
 * @author MCheung
 */

@Entity
@Table(name = "batchitem")
public class BatchEntity  {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @CreatedDate
    @Column(name = "insert_date")
    private Date insertDate;

    @LastModifiedDate
    @Column(name = "last_modified")
    private Date lastModified;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
