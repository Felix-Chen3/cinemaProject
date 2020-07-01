/**
 * @author Felix
 * @describe
 * @date 2020/6/30 13:30
 */
package felix.biz.imp0;

import felix.dao.Imp0.RechargerDaoImp0;
import felix.entity.Recharger;

public class RechargerBizImp0 {
    private RechargerDaoImp0 rdi0 = new RechargerDaoImp0();

    public boolean create(Recharger recharger) {
        return rdi0.create(recharger);
    }
}
