package org.baeldung.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * File Model representation for File entity.
 * 
 * @author MCheung
 */

@Entity
@Table(name = "fileitem")
public class FileEntity  {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "batch_id")
    private Long batchId;

    @Column(name = "status")
    private String status;

    @Column(name = "fail_reason")
    private String failReason;

    @Column(name = "total_datasets")
    private int totalDatasets;

    @Column(name = "error_datasets")
    private int errorDatasets;

    @CreatedDate
    @Column(name = "insert_date")
    private Date insertDate;

    @LastModifiedDate
    @Column(name = "last_modified")
    private Date lastModified;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_content_id")
    private FileContentEntity fileContentEntity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public int getTotalDatasets() {
        return totalDatasets;
    }

    public void setTotalDatasets(int totalDatasets) {
        this.totalDatasets = totalDatasets;
    }

    public int getErrorDatasets() {
        return errorDatasets;
    }

    public void setErrorDatasets(int errorDatasets) {
        this.errorDatasets = errorDatasets;
    }

    public FileContentEntity getFileContentEntity() {
        return fileContentEntity;
    }

    public void setFileContentEntity(FileContentEntity fileContentEntity) {
        this.fileContentEntity = fileContentEntity;
    }
    
    

}
