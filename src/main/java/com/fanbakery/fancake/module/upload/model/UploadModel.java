package com.fanbakery.fancake.module.upload.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadModel {
    private String uploadFileName;
    private String uploadFileUrl;
    private String originalFileName;
    private boolean result;
    private String msg;
}
