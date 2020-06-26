package felix.dao;


import felix.entity.Cinema;

import java.util.ArrayList;

public interface CinemaDao {
    public boolean createCinema(Cinema cinema);

    public Cinema queryByName(Cinema cinema);

    public ArrayList<Cinema> queryByNameAll(Cinema cinema);

    public ArrayList<Cinema> fuzzyQueryByNameAll(Cinema cinema);

    public ArrayList<Cinema> fuzzyQueryByAddressAll(Cinema cinema);
}
