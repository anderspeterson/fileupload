package com.anders.upload.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Document {

    private byte[] fileData;
    private String originalFileName;
    private Long ctrId;
    private DocumentResponse documentResponse;


    public Document(byte[] fileData, String originalFileName, Long ctrId) {
        this.fileData = fileData;
        this.originalFileName = originalFileName;
        this.ctrId = ctrId;
    }
}
