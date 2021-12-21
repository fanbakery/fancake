package com.fanbakery.fancake.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationConfig {

    @NotNull
    @Valid
    private UploadModule uploadConfig;

    @Data
    public static class UploadModule {
        @NotNull
        private String physicalPath;

        @NotNull
        private String urlPath;
    }

    @NotNull
    private String niceUrl;
    @NotNull
    private String niceAccessToken;
    @NotNull
    private String niceClientId;
    @NotNull
    private String niceProductId;
    @NotNull
    private String niceAccessTokenUrl;
    @NotNull
    private String niceAccessTokenUrlAfter;
    @NotNull
    private String niceRetrunUrl;

    @NotNull
    private String pgRefererDomain;
    
    
    @NotNull
    private String emailFrom;
    @NotNull
    private Integer emailPort;
    @NotNull
    private String emailUsername;
    @NotNull
    private String emailPassword;
    @NotNull
    private String emailHost;
    @NotNull
    private String image_tem_path;
}

