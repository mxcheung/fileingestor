package org.baeldung;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "originalFileName", "status", "failReason", "totalDatasets", "errorDatasets", "resultDTO" })
public class FileDTO {

    private Long id;
    private String status;
    private String failReason;
    private int totalDatasets;
    private int errorDatasets;
    private String originalFileName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

}
