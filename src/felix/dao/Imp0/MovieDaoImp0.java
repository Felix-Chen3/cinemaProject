/**
 * @author Felix
 * @describe
 * @date 2020/6/26 15:04
 */
package felix.dao.Imp0;


import felix.dao.MovieDao;
import felix.entity.Movie;

import java.util.ArrayList;

public class MovieDaoImp0 extends BaseDao implements MovieDao {
    @Override
    public boolean createMovie(Movie movie) {
            String sql = "insert into movie(name,address)values(?,?)";
            int rs = update(sql, movie.getName(), movie.getDirector());
            if (rs == 0) {
                return false;
            } else {
                return true;
            }
    }

    @Override
    public Movie queryByName(Movie movie) {
        return null;
    }

    @Override
    public ArrayList<Movie> queryByNameAll(Movie movie) {
        return null;
    }

    @Override
    public ArrayList<Movie> fuzzyQueryByNameAll(Movie movie) {
        return null;
    }

    @Override
    public ArrayList<Movie> fuzzyQueryByAddressAll(Movie movie) {
        return null;
    }
}
