/**
 * @author Felix
 * @describe
 * @date 2020/6/29 10:38
 */
package felix.dao.Imp0;

import felix.dao.UserDao;
import felix.entity.User;

public class UserDaoImp0 extends BaseDao implements UserDao {

    @Override
    public User queryByName(User user) {
        return queryOne(User.class, "select id,name,password,balance,totalAmount,level from user where name = ?", user.getName());
    }

    @Override
    public boolean createUser(User user) {
        int rs = update("insert into user (name,password,balance,totalAmount,level)values(?,?,?,?,?)",user.getName(),user.getPassword(),user.getBalance(),user.getTotalAmount(),user.getLevel());
        if (rs == 0) {
            return false;
        } else {
            return true;
        }
    }
}
