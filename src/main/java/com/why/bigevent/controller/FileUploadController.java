package com.why.bigevent.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.why.bigevent.pojo.Result;

@RestController
public class FileUploadController {

    @Autowired
    private COSClient cosClient;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));

        String key = "pic/" + fileName;
        String bucketName = "bigevent-1310127414";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file.getInputStream(), null);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

        return Result.success("https://bigevent-1310127414.cos.ap-beijing.myqcloud.com/pic/" + fileName);
    }
}
