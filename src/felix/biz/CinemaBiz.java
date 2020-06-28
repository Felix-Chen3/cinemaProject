package felix.biz;



import felix.entity.Cinema;

import java.util.ArrayList;

public interface CinemaBiz {
    boolean create(Cinema cinema);

    boolean delete(Cinema cinema);

    Cinema queryCinemaById(int id);

    ArrayList<Cinema> queryCinemaByName(Cinema cinema);

    ArrayList<Cinema> queryCinemaByAddress(Cinema cinema);

    ArrayList<Cinema> queryCinemaAll();

    int updateName(int id,String changeString);

    int updateAddress(int id, String changeString);

    int deleteCinema(int id);
}
