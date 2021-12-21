package com.fanbakery.fancake.module.upload.controller;

import com.fanbakery.fancake.module.upload.model.UploadModel;
import com.fanbakery.fancake.module.upload.model.UploadModels;
import com.fanbakery.fancake.module.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UploadController {
    private final UploadService uploadService;

    @PostMapping("/uploadFile/image/temp")
    @ResponseBody
    public UploadModel uploadImageToTemp(@RequestParam("uploadFile") MultipartFile uploadfile)
    {
        return uploadService.saveImageUploadFiles_ForTemp(uploadfile);
    }


    @PostMapping("/uploadFile/image/temps")
    @ResponseBody
    public UploadModels uploadImageToTemps(@RequestParam("uploadFile") List<MultipartFile> uploadfiles)
    {
        return uploadService.saveImageUploadFiles_ForTemps(uploadfiles);
    }

    @GetMapping("/uploadFile/uploadForm")
    public String uploadForm()
    {
        return "/uploadFile/uploadForm";
    }
}
