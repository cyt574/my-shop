package com.yichen.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UploadController {

    private final static String UPLOAD_PATH = "/static/upload/";

    @ResponseBody
    @PostMapping("/upload")
    public Map<String, Object> upload(MultipartFile dropzFile, MultipartFile editorFile, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        MultipartFile myFile = dropzFile == null ? editorFile:dropzFile;

        String fileName = myFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);

        File file = new File(filePath);
        if(!file.exists()) {
            file.mkdir();
        }

        file = new File(filePath, UUID.randomUUID() + fileSuffix);
        try {
            myFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (dropzFile != null) {
            map.put("fileName", UPLOAD_PATH + file.getName());
        } else {
            String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            map.put("errno", 0);
            map.put("data", new String[] {serverPath + UPLOAD_PATH + file.getName()});
        }

        return map;
    }
}
