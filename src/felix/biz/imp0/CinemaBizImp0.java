/**
 * @author Felix
 * @describe
 * @date 2020/6/24 16:30
 */
package felix.biz.imp0;


import felix.biz.CinemaBiz;
import felix.dao.Imp0.CinemaDaoImp0;
import felix.entity.Cinema;

import java.util.ArrayList;
import java.util.Random;

public class CinemaBizImp0 implements CinemaBiz {
    CinemaDaoImp0 cdi0 = new CinemaDaoImp0();
    Random random = new Random();

    @Override

    public boolean create(Cinema cinema) {
        ArrayList<Cinema> rs = cdi0.queryByNameAll(cinema);
        if (rs == null) {
            return cdi0.createCinema(cinema);
        }

        if (rs.contains(cinema)) {
            return false;
        } else {
            return cdi0.createCinema(cinema);
        }
    }

    @Override
    public boolean delete(Cinema cinema) {
        return false;
    }

    @Override
    public ArrayList<Cinema> queryCinemaByName(Cinema cinema) {
        return cdi0.fuzzyQueryByNameAll(cinema);
    }

    @Override
    public ArrayList<Cinema> queryCinemaByAddress(Cinema cinema) {
        return cdi0.fuzzyQueryByAddressAll(cinema);
    }

    @Override
    public ArrayList<Cinema> queryCinemaAll() {
        return cdi0.queryAll(Cinema.class,"select id,name,address from cinema");
    }

    @Override
    public Cinema queryCinemaById(int id) {

        return null;
    }

    @Override
    public int updateName(int id, String changeString) {
        return  cdi0.update("update cinema set name = ? where id  = ?",changeString,id);
    }

    @Override
    public int updateAddress(int id, String changeString) {
        return  cdi0.update("update cinema set address = ? where id  = ?",changeString,id);
    }

    @Override
    public int deleteCinema(int id) {
        int i = -(random.nextInt(99999));
        return  cdi0.update("update cinema set id = ? where id  =?",i,id);
    }
}
