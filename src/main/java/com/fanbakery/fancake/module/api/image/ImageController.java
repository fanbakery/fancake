package com.fanbakery.fancake.module.api.image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fanbakery.fancake.code.service.DirectoryCode;
import com.fanbakery.fancake.config.ApplicationConfig;
import com.fanbakery.fancake.module.upload.model.UploadModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class ImageController {
	 private final ApplicationConfig applicationConfig;
	    private static final String UPLOAD_TEMP_DIR = "temp/";

	    

	    @PostMapping("/uploadFile/image/temp")
	    @ResponseBody
	    public UploadModel uploadImageToTemp(@RequestParam("uploadFile") MultipartFile uploadfile)
	    {
	        if (uploadfile.isEmpty()) {
	            return UploadModel.builder()
	                    .result(false)
	                    .msg("업로드 파일이 비어 있습니다.")
	                    .build();
	        }

	        try {
	            String fileExt = FilenameUtils.getExtension(uploadfile.getOriginalFilename());
	            String orginalFileName = uploadfile.getOriginalFilename();
	            
	            File f = new File(applicationConfig.getUploadConfig().getPhysicalPath() + UPLOAD_TEMP_DIR);
	        	if(!f.exists()) f.mkdirs();
	        	
	        	
	            System.out.println("f : "+f.getCanonicalPath());
	            System.out.println("f : "+f.exists());
	            System.out.println("f : "+f.isDirectory());
	            if(!f.exists()) {
	            	System.out.println("파일생성 : "+f.mkdir());
	            }
	            File tempPath = File.createTempFile(DirectoryCode.TEMP_DIR.getCode(),
	                                        "." + fileExt,
	                                            new File(applicationConfig.getUploadConfig().getPhysicalPath() + UPLOAD_TEMP_DIR));


	            // 파일 저장.
	            uploadfile.transferTo(tempPath);

	            // 저장 객체 리턴.
	            return UploadModel.builder()
	                    .originalFileName(orginalFileName)
	                    .uploadFileUrl(applicationConfig.getUploadConfig().getUrlPath() + "/temp/" + tempPath.getName())
	                    .uploadFileName(tempPath.getName())
	                    .result(true)
	                    .msg("업로드가 완료되었습니다.")
	                    .build();
	        }
	        catch (IOException e) {
	            log.error(e.getMessage(), e);
	            return UploadModel.builder()
	                    .result(false)
	                    .msg("업로드 처리 중 오류가 발생하였습니다.")
	                    .build();
	        }
	        catch (Exception e) {
	            log.error(e.getMessage(), e);
	            return UploadModel.builder()
	                    .result(false)
	                    .msg("업로드 처리 중 오류가 발생하였습니다.")
	                    .build();
	        }
	    }


	    @GetMapping("/uploadFile/uploadForm")
	    public String uploadForm()
	    {
	        return "/test/uploadForm";
	    }
	    
}
