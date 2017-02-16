package com.anders.upload.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentResponse {

    private String savePath;

    public DocumentResponse(String savePath) {
        this.savePath = savePath;
    }
}
