package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.module.alarm.model.AlarmSettingInfo;
import com.fanbakery.fancake.repository.model.ItMemberAlarmEntity;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface AlarmMapper {

    /**  ----------------
     * it_member_alarm table
     ** ---------------------- */

    //select
    public List<ItMemberAlarmEntity> selectAlarmListByMbno(Long mbNo, LocalDate startDate);

    public AlarmSettingInfo selectAlarmSettingInfoByMbNo(Long mbNo);

    //update
    public void updateAlarmOpenYByMbNo(Long mbNo);
    public void updateAlarmSettingByMbNo(AlarmSettingInfo settingInfo);

}
