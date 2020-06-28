package felix.biz;

import felix.entity.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface SessionBiz {
    boolean create(Session session);

    Session querySessionById(int id);

    ArrayList<Session> querySessionByName(Session session);

    ArrayList<Session> querySessionByHid(Session session);

    ArrayList<Session> querySessionByMid(Session session);

    ArrayList<Session> querySessionByTime(LocalDateTime min, LocalDateTime max);

    ArrayList<Session> querySessionByPrice(Double min ,Double max);

    ArrayList<Session> querySessionAll();


    int updateHid(int id, int hid);

    int updateMid(int id, int mid);

    int updateTime(int id, LocalDateTime time);

    int updatePrice(int id, double price);

    int deleteSession(int id);
}
