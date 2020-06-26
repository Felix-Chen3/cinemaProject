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
        int rs = update(sql, movie.getName(), movie.getType(), movie.getDirector(), movie.getProtagonist(), movie.getDuration(), movie.getDetail(), movie.getScore(), movie.getLabels());
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
        String sql = "select id,name,type,director,protagonist,duration,detail,score,labels from movie where name like ? ";
        return queryAll(Movie.class, sql, "%" + movie.getName() + "%");
    }

    @Override
    public ArrayList<Movie> fuzzyQueryByTypeAll(Movie movie) {
        String sql = "select id,name,type,director,protagonist,duration,detail,score,labels from movie where name like ? ";
        return queryAll(Movie.class, sql, "%" + movie.getType() + "%");
    }

    @Override
    public ArrayList<Movie> fuzzyQueryByFieldAll(Movie movie, String field) {
        switch (field) {
            case "name":
                String sql0 = "select id,name,type,director,protagonist,duration,detail,score,labels from movie where name like ? ";
                return queryAll(Movie.class, sql0,  "%" + movie.getName() + "%");
            case "type":
                String sql1 = "select id,name,type,director,protagonist,duration,detail,score,labels from movie where type like ? ";
                return queryAll(Movie.class, sql1,  "%" + movie.getType() + "%");
            case "director":
                String sql2 = "select id,name,type,director,protagonist,duration,detail,score,labels from movie where director like ? ";
                return queryAll(Movie.class, sql2,  "%" + movie.getDirector() + "%");
            case "protagonist":
                String sql3 = "select id,name,type,director,protagonist,duration,detail,score,labels from movie where protagonist like ? ";
                return queryAll(Movie.class, sql3,  "%" + movie.getProtagonist() + "%");
            case "score":
                String sql4 = "select id,name,type,director,protagonist,duration,detail,score,labels from movie where score like ? ";
                return queryAll(Movie.class, sql4,  "%" + movie.getScore() + "%");
            case "labels":
                String sql5 = "select id,name,type,director,protagonist,duration,detail,score,labels from movie where labels like ? ";
                return queryAll(Movie.class, sql5,  "%" + movie.getLabels() + "%");
            default:
                return new ArrayList<>(0);
        }
    }
}
