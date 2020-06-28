/**
 * @author Felix
 * @describe
 * @date 2020/6/28 10:41
 */
package felix.dao.Imp0;

import felix.dao.HallDao;
import felix.entity.Hall;
import felix.entity.Movie;

import java.util.ArrayList;

public class HallDapImp0 extends BaseDao implements HallDao {
    @Override
    public ArrayList<Hall> fuzzyQueryByNameAll(Hall hall) {
        String sql = "select id,name,cid,capacity from hall where name =?";
        return queryAll(Hall.class, sql, "%" + hall.getName() + "%");
    }

    @Override
    public ArrayList<Hall> fuzzyQueryByCidAll(Hall hall) {
        String sql = "select id,name,cid,capacity from hall where cid like ?";
        return queryAll(Hall.class, sql, "%" + hall.getCid() + "%");
    }

    @Override
    public ArrayList<Hall> fuzzyQueryByCapacityAll(Hall hall) {
        String sql = "select id,name,cid,capacity from hall where capacity like ?";
        return queryAll(Hall.class, sql, "%" + hall.getCapacity() + "%");
    }

    @Override
    public ArrayList<Hall> queryByNameAll(Hall hall) {
        String sql = "select id,name,cid,capacity from hall where name like ?";
        return queryAll(Hall.class, sql, "%" + hall.getName() + "%");
    }

    @Override
    public boolean createHall(Hall hall) {
        String sql = "insert into hall(name,cid,capacity)values(?,?,?)";
        int rs = update(sql, hall.getName(), hall.getCid(), hall.getCapacity());
        if (rs == 0) {
            return false;
        } else {
            return true;
        }
    }
}
