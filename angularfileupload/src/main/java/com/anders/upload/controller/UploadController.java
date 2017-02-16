package com.anders.upload.controller;

import com.anders.upload.service.ArchiveService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Getter
@Setter
public class UploadController {

    private static final String template = "Hello, %s!";

    @Autowired
    private ArchiveService archiveService;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "World") String name) {
        return new Greeting(1L, String.format(template, name));
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody DocumentResponse upload(
            @RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "ctrId", required = true) Long ctrId
            ){
        Document document = null;
        try {
            document = new Document(file.getBytes(), file.getOriginalFilename(), ctrId);
            this.archiveService.save(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document.getDocumentResponse();
    }

}
