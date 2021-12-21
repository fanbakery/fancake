package com.fanbakery.fancake.module.shorturl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShortUrlService {
    private final UrlEncoder urlEncoder;

    public String encodingUrl(long seq) throws Exception{
        return urlEncoder.urlEncoder(seq);
    }
}