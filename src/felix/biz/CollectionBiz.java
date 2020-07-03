package felix.biz;

import felix.dao.Imp0.CollectionDaoImp0;
import felix.entity.Collection;

import java.util.ArrayList;

public interface CollectionBiz {
    boolean create(Collection collection);

    ArrayList<Collection> queryCollectionAll(Collection collection);

    ArrayList<Collection> queryCollectionByUid(int uid);
}
