package com.fanbakery.fancake.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImgUrlInfo {
    //-- 임시 파일명 경로
    private String imgTempUrlPath;
    private String imgTempFileName;
    // 실제 프로파일 URL 경로
    private String imgRealFileUrl;
}
