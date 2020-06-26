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
            String sql = "insert into movie(name,type,director,protagonist,duration,detail,score,labels)values(?,?,?,?,?,?,?,?)";
            int rs = update(sql, movie.getName(),movie.getType(), movie.getDirector(),movie.getProtagonist(),movie.getDuration(),movie.getDetail(),movie.getScore(),movie.getLabels());
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
        String sql = "select name,type,director,protagonist,duration,detail,score,labels from movie where name =?";
        return queryAll(Movie.class, sql, movie.getName());
    }

    @Override
    public ArrayList<Movie> fuzzyQueryByNameAll(Movie movie) {
        String sql = "id,select name,type,director,protagonist,duration,detail,score,labels from movie where name like ? ";
        return queryAll(Movie.class,sql,"%"+movie.getName()+"%");
    }

    @Override
    public ArrayList<Movie> fuzzyQueryByAddressAll(Movie movie) {
        return null;
    }
}
