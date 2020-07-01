/**
 * @author Felix
 * @describe
 * @date 2020/6/30 10:14
 */
package felix.dao.Imp0;

import felix.dao.QuerySessionCapacityDao;
import felix.entity.QuerySessionCapacity;

import java.util.ArrayList;

public class QuerySessionCapacityDaoImp0 extends BaseDao implements QuerySessionCapacityDao {
    @Override
    public QuerySessionCapacity querySessionByIdWithCapacity(int sid) {
        return queryOne(QuerySessionCapacity.class,"SELECT\n" +
                "\t`session`.id AS id, \n" +
                "\thall.capacity AS capacity\n" +
                "FROM\n" +
                "\t`session`\n" +
                "\tINNER JOIN\n" +
                "\thall\n" +
                "\tON \n" +
                "\t\t`session`.hid = hall.id\n" +
                "WHERE\n" +
                "\t`session`.id = ?",sid);
    }
}
