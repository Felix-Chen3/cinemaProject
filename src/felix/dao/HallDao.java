package felix.dao;

import felix.entity.Hall;

import java.util.ArrayList;

public interface HallDao {
    ArrayList<Hall> fuzzyQueryByNameAll(Hall hall);

    ArrayList<Hall> fuzzyQueryByCidAll(Hall hall);

    ArrayList<Hall> fuzzyQueryByCapacityAll(Hall hall);

    ArrayList<Hall> queryByNameAll(Hall hall);

    boolean createHall(Hall hall);
}
