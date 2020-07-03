/**
 * @author Felix
 * @describe
 * @date 2020/7/2 10:34
 */
package felix.biz.imp0;

import felix.biz.CollectionBiz;
import felix.dao.Imp0.CollectionDaoImp0;
import felix.entity.Collection;

import java.util.ArrayList;

public class CollectionBizImp0 implements CollectionBiz {
    private CollectionDaoImp0 collectionDaoImp0 = new CollectionDaoImp0();

    @Override
    public boolean create(Collection collection) {
        return collectionDaoImp0.create(collection);
    }

    @Override
    public ArrayList<Collection> queryCollectionAll(Collection collection) {
        return collectionDaoImp0.queryAll(Collection.class,"select id,uid,mid from collection");
    }

    @Override
    public ArrayList<Collection> queryCollectionByUid(int uid) {
        return collectionDaoImp0.queryAll(Collection.class, "select id,uid,mid from collection where uid = ?",uid);
    }
}
