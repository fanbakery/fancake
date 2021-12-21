package com.fanbakery.fancake.module.alarm.controller;

import com.fanbakery.fancake.module.alarm.model.AlarmSettingInfo;
import com.fanbakery.fancake.module.alarm.service.AlarmService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alarm")
public class AlarmRestController {
    private final AlarmService alarmService;

    @PostMapping("/setting")
    public ResponseEntity<?> setting(@RequestParam(name="mbAlarmMessageYn") Integer mbAlarmMessageYn
                                     , @RequestParam(name="mbAlarmSoundYn") Integer mbAlarmSoundYn
                                     , @RequestParam(name="mbAlarmVibrationYn") Integer mbAlarmVibrationYn
                        , Model model, HttpSession session){
        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long myMbNo = user.getMbNo();

        AlarmSettingInfo settingInfo = AlarmSettingInfo.builder()
                .MbNo(myMbNo)
                .mbAlarmMessageYn(mbAlarmMessageYn)
                .mbAlarmSoundYn(mbAlarmSoundYn)
                .mbAlarmVibrationYn(mbAlarmVibrationYn)
                .build();

        alarmService.updateAlarmSettingInfo(settingInfo);
        
        return ResponseEntity.ok().build();
    }

}
