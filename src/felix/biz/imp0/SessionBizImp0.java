/**
 * @author Felix
 * @describe
 * @date 2020/6/28 15:38
 */
package felix.biz.imp0;

import felix.biz.SessionBiz;
import felix.dao.Imp0.MovieDaoImp0;
import felix.dao.Imp0.SessionDaoImp0;
import felix.entity.Movie;
import felix.entity.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SessionBizImp0 implements SessionBiz {
    private SessionDaoImp0 sdi0 = new SessionDaoImp0();
    private MovieDaoImp0 mdi0 = new MovieDaoImp0();
    /**
     * @author Felix
     * @date 2020-06-29 17:15
     * @describe 包含时间冲突判定的场次创建
     * @return
     * @throws
    */
    @Override
    public boolean create(Session session) {
        ArrayList<Session> rs = querySessionByHid(session);
        if (rs.size() == 0) {
            return sdi0.createSession(session);
        }
        for (Session r : rs) {
            LocalDateTime start = r.getTime();
            int length = Integer.parseInt(mdi0.queryById(r.getMid()).getDuration());
            LocalDateTime end = start.plusMinutes(length);
            LocalDateTime thisStart = session.getTime();
            int thisLength = Integer.parseInt(mdi0.queryById(session.getMid()).getDuration());
            LocalDateTime thisEnd = thisStart.plusMinutes(thisLength);
            if (((thisEnd.compareTo(end)>0) && (end.compareTo(thisStart)>0)) || (thisEnd.compareTo(start)>0 && start.compareTo(thisStart)>0)){
                return false;
            }
        }
        return sdi0.createSession(session);
    }

    private ArrayList<Session> querySessionByHidAndMid(Session session) {
        return sdi0.queryAll(Session.class, "select id,hid,mid,time,price from session where hid = ? and mid = ?", session.getHid(), session.getMid());
    }

    @Override
    public Session querySessionById(int id) {
        return sdi0.queryOne(Session.class,"select id,hid,mid,time,price from session where id = ?",id);
    }

    @Override
    public ArrayList<Session> querySessionByName(Session session) {
        return null;
    }

    @Override
    public ArrayList<Session> querySessionByHid(Session session) {
        return sdi0.fuzzyQueryByHidAll(session);
    }

    @Override
    public ArrayList<Session> querySessionByMid(Session session) {
        return sdi0.fuzzyQueryByMidAll(session);
    }

    @Override
    public ArrayList<Session> querySessionByTime(LocalDateTime min, LocalDateTime max) {
        return sdi0.fuzzyQueryByTimeAll(min, max);
    }

    @Override
    public ArrayList<Session> querySessionByPrice(Double min ,Double max) {
        return sdi0.fuzzyQueryByPriceAll(min,max);
    }


    @Override
    public ArrayList<Session> querySessionAll() {
        return sdi0.queryAll(Session.class,"select id,hid,mid,time,price from session");
    }

    @Override
    public int updateHid(int id, int hid) {
        return sdi0.update("update session set hid = ? where id  = ?",hid,id);
    }

    @Override
    public int updateMid(int id, int mid) {
        return sdi0.update("update session set mid = ? where id  = ?",mid,id);
    }

    @Override
    public int updateTime(int id, LocalDateTime time) {
        return sdi0.update("update session set time = ? where id  = ?",time,id);
    }

    @Override
    public int updatePrice(int id, double price) {
        return sdi0.update("update session set price = ? where id  = ?",price,id);
    }

    @Override
    public int deleteSession(int id) {
        return sdi0.update("delete from session where id= ?",id);
    }

}
