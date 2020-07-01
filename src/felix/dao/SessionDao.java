package felix.dao;

import felix.entity.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface SessionDao {
    ArrayList<Session> fuzzyQueryByHidAll(Session session);

    ArrayList<Session> fuzzyQueryByMidAll(Session session);

    ArrayList<Session> fuzzyQueryByTimeAll(LocalDateTime min, LocalDateTime max);

    ArrayList<Session> fuzzyQueryByPriceAll(Double min,Double max);

    ArrayList<Session> queryByNameAll(Session session);

    boolean createSession(Session session);


}
