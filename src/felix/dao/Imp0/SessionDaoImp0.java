/**
 * @author Felix
 * @describe
 * @date 2020/6/28 15:37
 */
package felix.dao.Imp0;

import felix.dao.SessionDao;
import felix.entity.Movie;
import felix.entity.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SessionDaoImp0 extends BaseDao implements SessionDao {

    @Override
    public ArrayList<Session> fuzzyQueryByHidAll(Session session) {
        String sql = "select id,hid,mid,time,price from session where hid like ?";
        return queryAll(Session.class, sql, "%" + session.getHid() + "%");
    }

    @Override
    public ArrayList<Session> fuzzyQueryByMidAll(Session session) {
        String sql = "select id,hid,mid,time,price from session where mid like ?";
        return queryAll(Session.class, sql, "%" + session.getMid() + "%");
    }

    @Override
    public ArrayList<Session> fuzzyQueryByTimeAll(LocalDateTime min, LocalDateTime max) {
        String sql = "select id,hid,mid,time,price from session where time between ? and ?";
        return queryAll(Session.class, sql, min, max);
    }

    @Override
    public ArrayList<Session> fuzzyQueryByPriceAll(Double min, Double max) {
        String sql = "select id,hid,mid,time,price from session where price between ? and ?";
        return queryAll(Session.class, sql, min, max);
    }

    @Override
    public ArrayList<Session> queryByNameAll(Session session) {
        return null;
    }

    @Override
    public boolean createSession(Session session) {
        String sql = "insert into session(hid,mid,time,price)values(?,?,?,?)";
        int rs = update(sql, session.getHid(), session.getMid(), session.getTime(), session.getPrice());
        if (rs == 0) {
            return false;
        } else {
            return true;
        }
    }
}
