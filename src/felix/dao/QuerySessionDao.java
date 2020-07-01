package felix.dao;

import felix.entity.QuerySession;

import java.util.ArrayList;

public interface QuerySessionDao {
    ArrayList<QuerySession> show(int mid);
}
