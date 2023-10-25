package oit.is.z1992.kaizi.janken.service;

import oit.is.z1992.kaizi.janken.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.TimeUnit;

@Service
public class AsyncKekka {
    private boolean dbUpdated = false;

    @Autowired
    private MatchMapper matchMapper;
    @Autowired
    MatchInfoMapper matchInfoMapper;

    public void syncInsertMatchTable(Match match) {
        matchMapper.insertMatch(match);
        this.dbUpdated = true;
    }
    @Async
    public void kekka(SseEmitter emitter) {
        try {
            while (true) {
                if (!dbUpdated) {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    continue;
                }
                Match match = matchMapper.selectActiveMatch();
                JankenResult result = new JankenResult(match);
                emitter.send(result);

                MatchInfo activeMatch = matchInfoMapper.selectActiveMatchInfoByUser(match.getUser1());
                activeMatch.setActive(false);
                matchInfoMapper.updateMatchInfo(activeMatch);

                TimeUnit.MILLISECONDS.sleep(1000);
                this.dbUpdated = false;
            }
        }catch(Exception e){
            System.out.println("Exception:" + e.getClass().getName() + ":" + e.getMessage());
        } finally {
            emitter.complete();
        }
    }
}