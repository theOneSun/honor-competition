package com.sun.honor.entity.vo;

import com.sun.honor.business.ApplyPlayer;
import com.sun.honor.business.Match;
import lombok.Data;

import java.util.List;

/**
 * @author sunjian.
 */
@Data
public class MatchVO {
    private Match match;
    List<ApplyPlayer> playerList;
}