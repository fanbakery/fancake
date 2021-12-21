package com.fanbakery.fancake.module.alarm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmSettingInfo {
    private Long MbNo;

    /*  메세지 알람 설정 (0/1) */
    private Integer mbAlarmMessageYn;

    /*  소리 설정 (0/1) */
    private Integer mbAlarmSoundYn;

    /*  진동 설정 (0/1) */
    private Integer mbAlarmVibrationYn;
}
