/**
 * @author Felix
 * @describe
 * @date 2020/6/26 14:58
 */
package felix.biz.imp0;


import felix.biz.MovieBiz;
import felix.dao.Imp0.MovieDaoImp0;
import felix.entity.Movie;

import java.util.ArrayList;

public class MovieBizImp0 implements MovieBiz {
    private MovieDaoImp0 mdi0 = new MovieDaoImp0();

    @Override
    public boolean create(Movie movie) {
        ArrayList<Movie> rs = mdi0.queryByNameAll(movie);
        if (rs == null) {
            return mdi0.createMovie(movie);
        }

        if (rs.contains(movie)) {
            return false;
        } else {
            return mdi0.createMovie(movie);
        }
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
