package com.sun.honor.service.impl;

import com.sun.honor.business.ApplyPlayer;
import com.sun.honor.dao.mapper.ApplyPlayerMapper;
import com.sun.honor.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * @author sunjian.
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private ApplyPlayerMapper applyPlayerMapper;
    @Value("${match.id}")
    private String matchId;
    @Value("${match.max-player}")
    private Integer maxPlayer;

    @Override
    public void groupPlayer() {

        /*
        1.查询报名选手
        2.进行分组
         */
        List<ApplyPlayer> playerList = applyPlayerMapper.listPlayerByMatchId(matchId, maxPlayer);
        Collections.shuffle(playerList);
        int groupCount = maxPlayer / 2;
        List<ApplyPlayer> aList = playerList.stream().limit(groupCount).collect(toList());
        List<ApplyPlayer> bList = playerList.stream().skip(groupCount).collect(toList());

        List<String> groupA = new ArrayList<>();
        List<String> groupB = new ArrayList<>();

        aList.forEach(applyPlayer -> groupA.add(applyPlayer.getName()));
        bList.forEach(applyPlayer -> groupB.add(applyPlayer.getName()));
        System.out.println("A组选手为:" + groupA);
        System.out.println("B组选手为:" + groupB);

    }
}
