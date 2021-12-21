package com.fanbakery.fancake.module.upload.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadModels {
    List<UploadModel> uploadfiles;

    private boolean result;
    private String msg;
}
