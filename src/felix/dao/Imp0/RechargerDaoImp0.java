/**
 * @author Felix
 * @describe
 * @date 2020/6/30 13:23
 */
package felix.dao.Imp0;

import felix.dao.RechargerDao;
import felix.entity.Recharger;

public class RechargerDaoImp0 extends  BaseDao implements RechargerDao {
    @Override
    public boolean create(Recharger recharger) {
        int rs = update("insert into recharger (uid,amount,time_recharger)values(?,?,?)", recharger.getUid(), recharger.getAmount(), recharger.getTime_recharger());
        if (rs < 1) {
            return false;
        } else {
            return true;
        }
    }
}
