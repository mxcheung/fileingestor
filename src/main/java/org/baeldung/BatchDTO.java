package org.baeldung;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Batch Model representation for Batch entity.
 * 
 * @author MCheung
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "status", "batchFileId",  "batchFileCreated", "queueDepth", "lastFileId", "lastFileTimeStamp" })
public class BatchDTO {

    private Long id;

    private Long batchFileId;

    private Date batchFileCreated;
    
    private Long lastProcessedFileId;
    
    
    private Long queueDepth;
    
    private Date lastProcessedFileTimeStamp;

    private List<FileDTO> files;
    
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<FileDTO> files) {
        this.files = files;
    }

    public Long getBatchFileId() {
        return batchFileId;
    }

    public void setBatchFileId(Long batchFileId) {
        this.batchFileId = batchFileId;
    }

    public Date getBatchFileCreated() {
        return batchFileCreated;
    }

    public void setBatchFileCreated(Date batchFileCreated) {
        this.batchFileCreated = batchFileCreated;
    }

    public Long getLastProcessedFileId() {
        return lastProcessedFileId;
    }

    public void setLastProcessedFileId(Long lastProcessedFileId) {
        this.lastProcessedFileId = lastProcessedFileId;
    }

    public Date getLastProcessedFileTimeStamp() {
        return lastProcessedFileTimeStamp;
    }

    public void setLastProcessedFileTimeStamp(Date lastProcessedFileTimeStamp) {
        this.lastProcessedFileTimeStamp = lastProcessedFileTimeStamp;
    }

    public Long getQueueDepth() {
        return queueDepth;
    }

    public void setQueueDepth(Long queueDepth) {
        this.queueDepth = queueDepth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    
    

}
