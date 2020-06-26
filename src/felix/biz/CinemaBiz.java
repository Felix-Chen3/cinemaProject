package felix.biz;



import felix.entity.Cinema;

import java.util.ArrayList;

public interface CinemaBiz {
    public boolean create(Cinema cinema);

    public boolean delete(Cinema cinema);

    public ArrayList<Cinema> queryCinemaByName(Cinema cinema);

    public ArrayList<Cinema> queryCinemaByAddress(Cinema cinema);
}
