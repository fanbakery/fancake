package com.fanbakery.fancake.config.mvc;

import com.fanbakery.fancake.config.ApplicationConfig;
import com.fanbakery.fancake.config.intercept.LoginIntercepter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final ApplicationConfig applicationConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginIntercepter loginIntercepter = new LoginIntercepter();
        registry.addInterceptor(loginIntercepter)
                .addPathPatterns(loginIntercepter.loginIncludeUri)
                .excludePathPatterns(loginIntercepter.loginExcludeUri);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            registry.addResourceHandler(applicationConfig.getUploadConfig().getUrlPath() + "/**")
                    .addResourceLocations("file:///" + applicationConfig.getUploadConfig().getPhysicalPath())
                    .setCachePeriod(3600)
                    .resourceChain(true)
                    .addResolver(new PathResourceResolver());
        }
        else {
            registry.addResourceHandler(applicationConfig.getUploadConfig().getUrlPath() + "/**")
                    .addResourceLocations("file:" + applicationConfig.getUploadConfig().getPhysicalPath())
                    .setCachePeriod(3600)
                    .resourceChain(true)
                    .addResolver(new PathResourceResolver());
        }

    }
}
