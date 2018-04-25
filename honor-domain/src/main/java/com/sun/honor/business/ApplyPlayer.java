package com.sun.honor.business;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by sj on 2018/4/23.
 */
@Data
public class ApplyPlayer {
    private String id;
    private String name;
    private LocalDateTime applyTime;//报名时间
    private long applyTimeMilli;//报名时间毫秒值
    private String matchId;//报名比赛的id
}
