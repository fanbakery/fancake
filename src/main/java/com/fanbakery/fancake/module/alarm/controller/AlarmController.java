package com.fanbakery.fancake.module.alarm.controller;

import com.fanbakery.fancake.module.alarm.service.AlarmService;
import com.fanbakery.fancake.repository.model.ItMemberAlarmEntity;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping("/")
    public String index(Model model, HttpSession session){
        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long myMbNo = user.getMbNo();

        //1. get alam list
        List<ItMemberAlarmEntity> alarmList = alarmService.getAlarmList(myMbNo);

        model.addAttribute("alarmList", alarmList);
        return "alarm/alarm";
    }

    @GetMapping("/setting")
    public String setting(    Model model, HttpSession session){
        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long myMbNo = user.getMbNo();


        model.addAttribute("alarmSetting", alarmService.getAlarmSettingInfo(myMbNo));
        return "alarm/alarmSetting";
    }

}
