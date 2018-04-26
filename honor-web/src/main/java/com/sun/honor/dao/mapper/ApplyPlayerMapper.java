package com.sun.honor.dao.mapper;

import com.sun.honor.business.ApplyPlayer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by sj on 2018/4/24.
 */
@Mapper
public interface ApplyPlayerMapper {

    void save(ApplyPlayer applyPlayer);
    //根据matchId和玩家总数查询
    List<ApplyPlayer> listPlayerByMatchId(@Param("matchId") String matchId,@Param("maxPlayer") Integer maxPlayer);
}
