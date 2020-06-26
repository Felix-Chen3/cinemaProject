/**
 * @author Felix
 * @describe
 * @date 2020/6/24 14:06
 */
package felix.biz.imp0;


import felix.biz.AdminBiz;
import felix.dao.Imp0.AdminDaoImp0;
import felix.entity.Admin;

public class AdminBizImp0 implements AdminBiz {
    private AdminDaoImp0 adi0= new AdminDaoImp0();

    @Override
    public boolean login(String account, String password) {
        Admin admin = new Admin(account, password);
        Admin rs = adi0.queryByAccount(admin);
        if (rs == null) {
            return false;
        } else {
            if (rs.getPassword().equals(password)) {
                return true;
            }
            else return false;
        }
    }
}
