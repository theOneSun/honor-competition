package com.sun.honor.business;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by sj on 2018/4/23.
 */
@Data
public class Match {
    private String id;
    private String name;
    private LocalDateTime startTime;//比赛开始时间
    private LocalDateTime applyBeginTime;//报名开始时间
    private LocalDateTime applyEndTime;//报名结束时间
    private Integer maxPlayer;//最大玩家数
    private boolean applyStatus;//是否可以报名
}
