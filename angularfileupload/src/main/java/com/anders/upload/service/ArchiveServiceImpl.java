package com.anders.upload.service;

import com.anders.upload.controller.Document;
import com.anders.upload.controller.DocumentResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service("archiveService")
public class ArchiveServiceImpl implements ArchiveService {

    private final String DIRECTORY = "C:\\Users\\AndersP";

    @Override
    public Document save(Document document) {
        try {
            createDirectory(document);
            saveFileData(document);
            return document;
        }catch(IOException ioe) {
            String message = "Error while saving document";
            System.out.println(message);
            ioe.printStackTrace();
            throw new RuntimeException(message, ioe);
        }
    }

    private void saveFileData(Document document) throws IOException {
        String path = getDirectoryPath(document);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(new File(path), document.getOriginalFileName())));
        stream.write(document.getFileData());
        stream.close();
        document.setDocumentResponse(new DocumentResponse(path));
    }

    private String createDirectory(Document document) {
        String path = getDirectoryPath(document);
        createDirectory(path);
        return path;
    }

    private String getDirectoryPath(Document document) {
        return getDirectoryPath(document.getCtrId());
    }

    private String getDirectoryPath(Long ctrId) {
        StringBuilder sb = new StringBuilder();
        sb.append(DIRECTORY).append(File.separator).append(ctrId);
        String path = sb.toString();
        return path;
    }

    private void createDirectory(String path) {
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }
    }

}
