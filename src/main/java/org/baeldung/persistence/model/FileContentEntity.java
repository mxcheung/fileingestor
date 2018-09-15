package org.baeldung.persistence.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * File Content representation for File entity.
 * 
 * @author MCheung
 */

@Entity
@Table(name = "filecontent")
public class FileContentEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;


    @JsonIgnore
    @Lob
    @Column(name = "file_content")
    private byte[] fileContent;

    @JsonIgnore
    @Lob
    @Column(name = "result_content")
    private byte[] resultContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public byte[] getResultContent() {
        return resultContent;
    }

    public void setResultContent(byte[] resultContent) {
        this.resultContent = resultContent;
    }
    
    
    

}
