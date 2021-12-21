package com.fanbakery.fancake.module.upload.service;

import com.fanbakery.fancake.code.service.DirectoryCode;
import com.fanbakery.fancake.common.utils.DateUtil;
import com.fanbakery.fancake.config.ApplicationConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileHandlingService {
    private final ApplicationConfig applicationConfig;

    // 프로파일 이미지 --> 실 경로로 이동.
    // URL 리턴.
    public String moveProfileImage(String userProfTempFileName) throws IOException {
        // 디렉토리 경로 --> /full-path/profile/yyyy
        String fullPathDir = String.format("%s/%s/%s", applicationConfig.getUploadConfig().getPhysicalPath(),
                DirectoryCode.PROFILE_DIR.getCode(),
                DateUtil.getCurrentYear());

        String tempFileFullPath = String.format("%s%s/%s", applicationConfig.getUploadConfig().getPhysicalPath(),
                DirectoryCode.TEMP_DIR.getCode(),
                userProfTempFileName);
        String realFileFullPath = String.format("%s/%s", fullPathDir, userProfTempFileName.substring(4));

        File fileProfile = new File(fullPathDir);
        File tempFile = new File(tempFileFullPath);
        File realFile = new File(realFileFullPath);

        // 미 존재시 디렉토리 생성.
        if (!fileProfile.exists()) {
            FileUtils.forceMkdir(fileProfile);
        }

        // temp 파일 이동
        FileUtils.moveFile(tempFile, realFile);

        // 실제 URL 경로 저장.
        return String.format("%s/%s/%s/%s", applicationConfig.getUploadConfig().getUrlPath(),
                        DirectoryCode.PROFILE_DIR.getCode(),
                        DateUtil.getCurrentYear(), userProfTempFileName.substring(4));
    }


    public String moveCoverImage(String coverTempImageFile) throws IOException {
        // 디렉토리 경로 --> /full-path/profile/yyyy
        String fullPathDir = String.format("%s/%s/%s", applicationConfig.getUploadConfig().getPhysicalPath(),
                DirectoryCode.INFLUENCER_COVER_DIR.getCode(),
                DateUtil.getCurrentYear());

        String tempFileFullPath = String.format("%s%s/%s", applicationConfig.getUploadConfig().getPhysicalPath(),
                DirectoryCode.TEMP_DIR.getCode(),
                coverTempImageFile);
        String realFileFullPath = String.format("%s/%s", fullPathDir, coverTempImageFile.substring(4));

        File fileProfile = new File(fullPathDir);
        File tempFile = new File(tempFileFullPath);
        File realFile = new File(realFileFullPath);

        // 미 존재시 디렉토리 생성.
        if (!fileProfile.exists()) {
            FileUtils.forceMkdir(fileProfile);
        }

        // temp 파일 이동
        FileUtils.moveFile(tempFile, realFile);

        // 실제 URL 경로 저장.
        return String.format("%s/%s/%s/%s", applicationConfig.getUploadConfig().getUrlPath(),
                DirectoryCode.INFLUENCER_COVER_DIR.getCode(),
                DateUtil.getCurrentYear(), coverTempImageFile.substring(4));
    }
}
