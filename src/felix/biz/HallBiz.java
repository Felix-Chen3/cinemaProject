package felix.biz;

import felix.entity.Cinema;
import felix.entity.Hall;

import java.util.ArrayList;

public interface HallBiz {
    boolean create(Hall hall);

    Hall queryHallById(int id);

    ArrayList<Hall> queryHallByName(Hall hall);

    ArrayList<Hall> queryHallByCid(Hall hall);

    ArrayList<Hall> queryHallByCapacity(Hall hall);

    ArrayList<Hall> queryHallAll();

    int updateName(int id, String changeString);

    int updateCid(int id,int cid);

    int updateCapacity(int id, String changeString);


    int deleteHall(int id);
}
