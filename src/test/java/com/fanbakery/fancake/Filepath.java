package com.fanbakery.fancake;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.Test;

//@SpringBootTest
class Filepath {

	
    @Test
    void contextLoads() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

    	
    	
//    	   String tem = applicationConfig.getUploadConfig().getPhysicalPath();
	        
    	
    	File f = new File("/home/fancake/upload/");	
    	
    	System.out.println(f.getAbsolutePath());
    	if(!f.exists()) f.mkdirs();
//    	Path directoryPath = Paths.get("/home/fancake/upload/");
//    	
//    	System.out.println(directoryPath.getRoot());
//    	System.out.println(directoryPath.getParent());
//    	System.out.println(directoryPath.getNameCount());
//    	System.out.println(directoryPath.getFileName());
////    	File f = directoryPath.
//    	try {
//    	// 디렉토리 생성
//    	Files.createDirectory(directoryPath);
//    	System.out.println(directoryPath + " 디렉토리가 생성되었습니다.");
//    	} catch (FileAlreadyExistsException e) {
//    		System.out.println(e);
//    	System.out.println("디렉토리가 이미 존재합니다");
//    	} catch (NoSuchFileException e) {
//    		e.printStackTrace();
//    	System.out.println("디렉토리 경로가 존재하지 않습니다");
//    	System.out.println(e);
//    	}catch (IOException e) {
//    	e.printStackTrace();
//    	}

//    	출처: https://hianna.tistory.com/594 [어제 오늘 내일]
    }	    
}
