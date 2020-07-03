/**
 * @author Felix
 * @describe
 * @date 2020/7/2 10:34
 */
package felix.dao.Imp0;

import felix.dao.CollectionDao;
import felix.entity.Collection;

public class CollectionDaoImp0 extends BaseDao implements CollectionDao {
    @Override
    public boolean create(Collection collection) {
        int rs = update("insert into collection (uid,mid)values(?,?)", collection.getUid(), collection.getMid());
        return (rs>0)?true:false;
    }
}
