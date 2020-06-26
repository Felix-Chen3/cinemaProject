/**
 * @author Felix
 * @describe
 * @date 2020/6/24 14:26
 */
package felix.dao.Imp0;

import felix.dao.AdminDao;
import felix.entity.Admin;

public class AdminDaoImp0 extends BaseDao implements AdminDao {
    @Override
    public Admin queryByAccount(Admin admin) {
        String sql = "select password from admin where account =?";
        return queryOne(Admin.class,sql,admin.getAccount());
    }
}
