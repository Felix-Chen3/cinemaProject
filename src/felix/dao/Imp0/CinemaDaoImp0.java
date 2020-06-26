/**
 * @author Felix
 * @describe
 * @date 2020/6/24 16:32
 */
package felix.dao.Imp0;



import felix.dao.CinemaDao;
import felix.entity.Cinema;

import java.util.ArrayList;

public class CinemaDaoImp0 extends BaseDao implements CinemaDao {
    /**
     * @author Felix
     * @date 2020-06-25 09:08
     * @describe 插入单条电影院数据
     * @return boolean
     * @throws
    */
    @Override
    public boolean createCinema(Cinema cinema) {
        String sql = "insert into cinema(name,address)values(?,?)";
        int rs = update(sql, cinema.getName(), cinema.getAddress());
        if (rs == 0) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * @author Felix
     * @date 2020-06-25 09:08
     * @describe 用单条cinema对象检索数据库对应数据
     * @return Cinema
     * @throws
    */
    @Override
    public Cinema queryByName(Cinema cinema) {
        String sql = "select address from cinema where name =?";
        return queryOne(Cinema.class, sql, cinema.getName());
    }
    /**
     * @author Felix
     * @date 2020-06-25 10:11
     * @describe 通过name得到所有影城结果
     * @return ArrayList<Cinema>
     * @throws
    */
    public ArrayList<Cinema> queryByNameAll(Cinema cinema) {
        String sql = "select name,address from cinema where name =?";
        return queryAll(Cinema.class, sql, cinema.getName());
    }
    /**
     * @author Felix
     * @date 2020-06-25 11:13
     * @describe 通过给定name模糊查询指定影院
     * @return ArrayList<Cinema>
     * @throws
    */
    @Override
    public ArrayList<Cinema> fuzzyQueryByNameAll(Cinema cinema) {
        String sql = "select id,name,address from cinema where name like ? ";
        return queryAll(Cinema.class,sql,"%"+cinema.getName()+"%");
    }
    /**
     * @author Felix
     * @date 2020-06-25 11:50
     * @describe 通过给定address模糊查询指定影院
     * @return
     * @throws
    */
    @Override
    public ArrayList<Cinema> fuzzyQueryByAddressAll(Cinema cinema) {
        String sql = "select id,name,address from cinema where address  like ? ";
        return queryAll(Cinema.class,sql,"%"+cinema.getAddress()+"%");
    }
}
