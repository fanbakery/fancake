package com.fanbakery.fancake.module.alarm.service;

import com.fanbakery.fancake.common.utils.DateUtil;
import com.fanbakery.fancake.module.alarm.model.AlarmSettingInfo;
import com.fanbakery.fancake.repository.mapper.AlarmMapper;
import com.fanbakery.fancake.repository.model.ItMemberAlarmEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmMapper alarmMapper;

    /**
     * 알람 내역 조회
     * @param mbNo
     * @return
     */
    public List<ItMemberAlarmEntity>  getAlarmList(Long mbNo) {

        //1. 미확인 알람 및 확인한 최근 1주일 알람 내역 조회
        LocalDate startDate = LocalDate.now().minusDays(7);
        List<ItMemberAlarmEntity> alarmList = alarmMapper.selectAlarmListByMbno(mbNo, startDate);

        //2. 받은 알람 경과 시간 계산
        LocalDateTime todayTime = LocalDateTime.now();
        for(ItMemberAlarmEntity alarm : alarmList ) {
            alarm.setReceivedHour(DateUtil.getHourDurationBetween(alarm.getMbDatetime(), todayTime));
        }

        //3. update alarm access yes
        if( alarmList != null && !alarmList.isEmpty()) {
            updateAlarmOpenY(mbNo);
        }

        return alarmList;
    }


    /**
     * 알람 열람 Y로 업데이트
     * @param mbNo
     */
    @Transactional
    public void updateAlarmOpenY(Long mbNo) {
        alarmMapper.updateAlarmOpenYByMbNo(mbNo);
    }


    /**
     * 알람 설정 정보 조회
     * @param mbNo
     * @return
     */
    public AlarmSettingInfo getAlarmSettingInfo(Long mbNo) {
        return alarmMapper.selectAlarmSettingInfoByMbNo(mbNo);
    }


    /**
     * 알람 설정 정보 변경
     * @param settingInfo
     */
    public void updateAlarmSettingInfo(AlarmSettingInfo settingInfo)
    {
        alarmMapper.updateAlarmSettingByMbNo(settingInfo);
        return;
    }
}
