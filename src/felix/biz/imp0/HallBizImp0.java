/**
 * @author Felix
 * @describe
 * @date 2020/6/28 10:42
 */
package felix.biz.imp0;

import felix.biz.HallBiz;
import felix.dao.Imp0.HallDapImp0;
import felix.entity.Hall;
import felix.entity.Movie;

import java.util.ArrayList;

public class HallBizImp0 implements HallBiz {
    private HallDapImp0 hdi0 = new HallDapImp0();
    @Override
    public boolean create(Hall hall) {
        ArrayList<Hall> rs = hdi0.queryByNameAll(hall);
        if (rs == null) {
            return hdi0.createHall(hall);
        }

        if (rs.contains(hall)) {
            return false;
        } else {
            return hdi0.createHall(hall);
        }
    }

    @Override
    public Hall queryHallById(int id) {
        return hdi0.queryOne(Hall.class,"select id,name,cid,capacity from hall where id =?",id);
    }

    @Override
    public ArrayList<Hall> queryHallByName(Hall hall) {
        return hdi0.fuzzyQueryByNameAll(hall);
    }

    @Override
    public ArrayList<Hall> queryHallByCid(Hall hall) {
        return hdi0.fuzzyQueryByCidAll(hall);
    }

    @Override
    public ArrayList<Hall> queryHallByCapacity(Hall hall) {
        return hdi0.fuzzyQueryByCapacityAll(hall);
    }

    @Override
    public ArrayList<Hall> queryHallAll() {
       return hdi0.queryAll(Hall.class,"select id,name,cid,capacity from hall");
    }

    @Override
    public int updateName(int id, String changeString) {
        return hdi0.update("update hall set name = ? where id = ?",changeString,id);
    }

    @Override
    public int updateCid(int id, int cid) {
        return hdi0.update("update hall set cid = ? where id = ?",cid,id);
    }

    @Override
    public int updateCapacity(int id, String changeString) {
        return hdi0.update("update hall set capacity = ? where id = ?",changeString,id);
    }

    @Override
    public int deleteHall(int id) {
        return  hdi0.update("delete from hall where id =?",id);
    }
}
