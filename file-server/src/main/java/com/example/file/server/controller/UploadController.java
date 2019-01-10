package com.example.file.server.controller;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @ ClassName  ：UploadController
 * @ Description：TODO
 * @ Author     ：yuqin
 * @ createTime : 2018-12-27
 */
@RestController
public class UploadController {

    // 上传图片
    @RequestMapping("/uploadImg")
    public int uploadImg(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || !request.getContentType().contains("multipart/form-data")){
            System.out.println("File cannot be empty or Incorrect ContentTye!");
            return 0;
        }
        boolean isImg = this.isImage(file);
        if (!isImg) {
            System.out.println("不是图片");
            return 0;
        }

        // 原始文件名
        String originName = file.getOriginalFilename();
        // 文件后缀(扩展名)
        String extName = FilenameUtils.getExtension(originName);

        return 0;
    }

    private boolean isImage(MultipartFile mFile) {
        CommonsMultipartFile cmFile = (CommonsMultipartFile) mFile;
        DiskFileItem diskFileItem = (DiskFileItem) cmFile.getFileItem();
        File file = diskFileItem.getStoreLocation();
        try {
            BufferedImage read = ImageIO.read(file);
            return read != null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
