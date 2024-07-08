package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOssUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@Tag(name = "文件上传相关接口")
@Slf4j
public class UploadFileController {

    @PutMapping
    @Operation(summary = "文件上传")
    public Result uploadFile(MultipartFile file) throws IOException {

        log.info("文件上传中...");
        //获取上传文件的原始文件名。
        String originalFilename = file.getOriginalFilename();
        //生成一个唯一的文件名，通常使用 UUID 避免文件名冲突，并保留文件原始的扩展名
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String url = AliOssUtil.uploadFile(fileName, file.getInputStream());
        return Result.success(url);
    }
}
