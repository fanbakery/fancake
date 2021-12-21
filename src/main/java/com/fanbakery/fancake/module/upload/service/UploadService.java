package com.fanbakery.fancake.module.upload.service;

import com.fanbakery.fancake.code.service.DirectoryCode;
import com.fanbakery.fancake.config.ApplicationConfig;
import com.fanbakery.fancake.module.upload.model.UploadModel;
import com.fanbakery.fancake.module.upload.model.UploadModels;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadService {
    private final ApplicationConfig applicationConfig;
    private static final String UPLOAD_TEMP_DIR = "temp/";

    public UploadModel saveImageUploadFiles_ForTemp(MultipartFile uploadFile) {

        if (uploadFile.isEmpty()) {
            return UploadModel.builder()
                    .result(false)
                    .msg("업로드 파일이 비어 있습니다.")
                    .build();
        }

        try {
            String fileExt = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
            String orginalFileName = uploadFile.getOriginalFilename();
            File tempPath = File.createTempFile(DirectoryCode.TEMP_DIR.getCode(),
                                        "." + fileExt,
                                            new File(applicationConfig.getUploadConfig().getPhysicalPath() + UPLOAD_TEMP_DIR));


            // 파일 저장.
            uploadFile.transferTo(tempPath);

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

    public UploadModels saveImageUploadFiles_ForTemps(List<MultipartFile> uploadfiles) {
        UploadModels resPacket = new UploadModels();
        resPacket.setUploadfiles(new ArrayList<>());

        if (uploadfiles == null ) {
            return UploadModels.builder()
                    .result(false)
                    .msg("업로드 파일이 비어 있습니다.")
                    .build();
        }

        try {
            for(MultipartFile uploadfile : uploadfiles) {
                String fileExt = FilenameUtils.getExtension(uploadfile.getOriginalFilename());
                String orginalFileName = uploadfile.getOriginalFilename();
                File tempPath = File.createTempFile(DirectoryCode.TEMP_DIR.getCode(),
                        "." + fileExt,
                        new File(applicationConfig.getUploadConfig().getPhysicalPath() + UPLOAD_TEMP_DIR));


                // 파일 저장.
                uploadfile.transferTo(tempPath);

                // 저장 객체 리턴.
                UploadModel uploadModel = UploadModel.builder()
                        .originalFileName(orginalFileName)
                        .uploadFileUrl(applicationConfig.getUploadConfig().getUrlPath() + "/temp/" + tempPath.getName())
                        .uploadFileName(tempPath.getName())
                        .result(true)
                        .msg("업로드가 완료되었습니다.")
                        .build();

                resPacket.getUploadfiles().add(uploadModel);
            }

            resPacket.setResult(true);
            return resPacket;
        }
        catch (IOException e) {
            log.error(e.getMessage(), e);
            return UploadModels.builder()
                    .result(false)
                    .msg("업로드 처리 중 오류가 발생하였습니다.")
                    .build();
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            return UploadModels.builder()
                    .result(false)
                    .msg("업로드 처리 중 오류가 발생하였습니다.")
                    .build();
        }
    }
}
