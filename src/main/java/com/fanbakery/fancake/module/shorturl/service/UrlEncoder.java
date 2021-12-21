package com.fanbakery.fancake.module.shorturl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@Service
public class UrlEncoder {
    private final int BASE62 = 62;
    private final String BASE62_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private String encoding(long id) {
        StringBuffer sb = new StringBuffer();
        while(id > 0) {
            sb.append(BASE62_CHAR.charAt((int) (id % BASE62)));
            id /= BASE62;
        }
        return sb.toString();
    }

    private long decoding(String param) {
        long sum = 0;
        long power = 1;
        for (int i = 0; i < param.length(); i++) {
            sum += BASE62_CHAR.indexOf(param.charAt(i)) * power;
            power *= BASE62;
        }
        return sum;
    }

    //신퀀스를 인코딩
    public String urlEncoder(long seq) throws NoSuchAlgorithmException {
        String encodeStr = encoding(seq);
        return encodeStr;
    }

    //디코딩
    public long urlDecoder(String encodeStr) throws NoSuchAlgorithmException {
        long decodeVal = decoding(encodeStr);
        return decodeVal;
    }
}