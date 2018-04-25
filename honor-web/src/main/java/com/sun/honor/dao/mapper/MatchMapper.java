package com.sun.honor.dao.mapper;

import com.sun.honor.business.Match;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by sj on 2018/4/24.
 */
@Mapper
public interface MatchMapper {
    //查询最新有效的竞赛
    Match getLatestMatchCanEnter();
}
