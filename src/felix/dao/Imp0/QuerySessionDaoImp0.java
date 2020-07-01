/**
 * @author Felix
 * @describe
 * @date 2020/6/29 18:21
 */
package felix.dao.Imp0;

import felix.dao.QuerySessionDao;
import felix.entity.QuerySession;

import java.util.ArrayList;

public class QuerySessionDaoImp0 extends BaseDao implements QuerySessionDao {
    @Override
    public ArrayList<QuerySession> show(int mid) {
        return queryAll(QuerySession.class, "SELECT\n" +
                "\t`session`.id, \n" +
                "\tmovie.`name` AS movie_name, \n" +
                "\tcinema.`name` AS cinema_name, \n" +
                "\tcinema.address AS cinema_address, \n" +
                "\thall.`name` AS hall_name, \n" +
                "\t`session`.time AS session_time, \n" +
                "\t`session`.price AS session_price\n" +
                "FROM\n" +
                "\tmovie\n" +
                "\tINNER JOIN\n" +
                "\t`session`\n" +
                "\tON \n" +
                "\t\tmovie.id = `session`.mid\n" +
                "\tINNER JOIN\n" +
                "\tcinema\n" +
                "\tINNER JOIN\n" +
                "\thall\n" +
                "\tON \n" +
                "\t\tcinema.id = hall.cid AND\n" +
                "\t\t`session`.hid = hall.id\n" +
                "WHERE\n" +
                "\tmovie.id = ?", mid);
    }
}
