/**
 * @author Felix
 * @describe
 * @date 2020/6/26 14:58
 */
package felix.biz.imp0;


import felix.biz.MovieBiz;
import felix.entity.Movie;

import java.util.ArrayList;

public class MovieBizImp0 implements MovieBiz {
    @Override
    public boolean create(Movie movie) {
//        {
//            ArrayList<Cinema> rs = cdi0.queryByNameAll(cinema);
//            if (rs == null) {
//                return cdi0.createCinema(cinema);
//            }
//
//            if (rs.contains(cinema)) {
//                return false;
//            } else {
//                return cdi0.createCinema(cinema);
//            }
//        }
        return false;
    }

    @Override
    public boolean delete(Movie movie) {
        return false;
    }

    @Override
    public ArrayList<Movie> queryMovieByName(Movie movie) {
        return null;
    }
}
