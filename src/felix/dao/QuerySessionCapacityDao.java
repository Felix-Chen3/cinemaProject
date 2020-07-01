package felix.dao;

import felix.entity.QuerySessionCapacity;
import felix.entity.Session;

import java.util.ArrayList;

public interface QuerySessionCapacityDao {
    QuerySessionCapacity querySessionByIdWithCapacity(int sid);
}
