package com.sun.honor.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by sj on 2018/4/23.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyPlayer {
    private String id;
    private String name;
    private String openId;
    private LocalDateTime applyTime;//报名时间
    private long applyTimeMilli;//报名时间毫秒值
    private String matchId;//报名比赛的id
}
