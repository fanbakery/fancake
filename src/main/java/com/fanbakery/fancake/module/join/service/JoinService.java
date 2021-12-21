package com.fanbakery.fancake.module.join.service;

import com.fanbakery.fancake.code.service.DirectoryCode;
import com.fanbakery.fancake.code.service.member.MbRouteCd;
import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.common.utils.AuthUtil;
import com.fanbakery.fancake.common.utils.DateUtil;
import com.fanbakery.fancake.config.ApplicationConfig;
import com.fanbakery.fancake.module.join.model.JoinInfoReq;
import com.fanbakery.fancake.repository.mapper.JoinMapper;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class JoinService {
    private final JoinMapper joinMapper;
    private final ApplicationConfig applicationConfig;

    /**
     * 핸드폰 인증 번소 발송
     * @param phone
     * @return
     */
    public String createPhoneCertNm(String phone) {

        int authNo = (int)(Math.random() * (999999 - 100000 + 1)) + 100000;

        //1. 핸드폰 인증번호 발송
        //@@@ TODO:: 핸드폰으로 인증발송 작업 필요

        //2. 발송 인증 번호 DB insert
        joinMapper.insertPhoneCertNum(phone, Integer.toString(authNo));
        return Integer.toString(authNo);
    }


    /**
     * 이메일 중복 체크
     * @param email
     * @return
     */
    public boolean checkDupEmail(String email ) {
        Long mbNo = joinMapper.selectMbNoByMbEmail(email);
        if(mbNo != null && mbNo > 0) {
            return true;
        }
        return false;
    }


    /**
     * 닉네임 사용 불가 리스트 확인
     * @param inputNick
     * @return
     */
    public boolean checkBlockNick(String inputNick) {
        String dbNick = joinMapper.selectBlockNick(inputNick);
        if(dbNick != null && !dbNick.isEmpty() ) {
            log.error("[BLOCK_NICK] :: 사용할수 없는 닉네임, NICK_" + inputNick);
            return true;
        }

        return false;
    }


    /**
     * 닉네임 중복 확인
     * @param inputNick
     * @return
     */
    public boolean checkDupNick(String inputNick) {
        Long mbNo = joinMapper.selectMbNoByMbNick(inputNick);
        if(mbNo != null && mbNo > 0) {
            log.error("[DUPLICATION_NICK] :: 사용할수 없는 닉네임, NICK_" + inputNick);
            return true;
        }


        return  false;
    }


    /**
     * 닉네임 사용 가능 여부 확인
     * @param inputNick
     * @return
     */
    public boolean checkNick(String inputNick) {
        if( !checkBlockNick(inputNick) ) {
            return checkDupNick(inputNick);
        }
        return  true;
    }


    /**
     * 핸드폰 중복 체크
     * @param inputPhone
     * @return
     */
    public boolean checkDupHp(String inputPhone) {
        ItMemberEntity user = joinMapper.selectMbInfombyMbHp(inputPhone);
        if(user != null ) {
            return true;
        }

        return  false;
    }



    public String createRandomNick(String inputNick) {

        String nick = inputNick;

        //랜덤 4자리표함해서 생성
        int randomNo = (int)(Math.random() * (9999 - 1000 + 1)) + 1000;

        if(nick.length() > 6)  {
            nick = nick.substring(0,6);
        }

        nick = nick + Integer.toString(randomNo);
        return nick;
    }

    /**
     * 이메일 가입처리
     * @param emailJoinInfo
     * @return
     * @throws Exception
     */
    public Long joinEmail(JoinInfoReq emailJoinInfo ) throws Exception{

        ItMemberEntity joinReqData = ItMemberEntity.builder()
                .mbRoute(MbRouteCd.APP)
                .mbEmail(emailJoinInfo.getUserMail())
                .mbPassword(AuthUtil.encrypt_SHA256(emailJoinInfo.getUserPw()))
                .mbName(emailJoinInfo.getUserName())
                .mbNick(!emailJoinInfo.getUserNick().isEmpty() ?
                        emailJoinInfo.getUserNick() : emailJoinInfo.getUserName())
                .mbHp(emailJoinInfo.getUserPhone())
                .mbCertify(emailJoinInfo.getUserCert())
                .mbProfile(emailJoinInfo.getUserProfRealFileUrl()) //url로 변경 필요.
                .mbSignature(MbSignatureCd.FAN)
                .build();

        Long cnt = joinMapper.insertJoinEmail(joinReqData);
        return  (cnt != null && cnt > 0) ? joinReqData.getMbNo() : -1;
    }



    public Long joinSns(JoinInfoReq snsJoinInfo) throws Exception{

            ItMemberEntity joinReqData = ItMemberEntity.builder()
                    .mbRoute(snsJoinInfo.getMbRoute())
                    .mbEmail(snsJoinInfo.getUserMail())
                    .mbNick(snsJoinInfo.getUserNick())
                    .mbProfile(snsJoinInfo.getUserProfRealFileUrl())
                    .mbSignature(MbSignatureCd.FAN)
                    .build();

            Long cnt = joinMapper.insertJoinSns(joinReqData);
            return  (cnt != null && cnt > 0) ? joinReqData.getMbNo() : -1;
    }




    /**
     * 프로파일 이미지 --> 실 경로로 이동.
     * @param joinInfoReq
     * @param userProfTempFileName
     * @throws IOException
     */
    // 프로파일 이미지 --> 실 경로로 이동.
    public void moveProfileImage(JoinInfoReq joinInfoReq, String userProfTempFileName) throws IOException {
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
        joinInfoReq.setUserProfRealFileUrl(
                        String.format("%s/%s/%s/%s", applicationConfig.getUploadConfig().getUrlPath(),
                        DirectoryCode.PROFILE_DIR.getCode(),
                        DateUtil.getCurrentYear(), userProfTempFileName.substring(4))
        );
    }





}
