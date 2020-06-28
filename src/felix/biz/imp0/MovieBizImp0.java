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
    public ArrayList<Movie> queryMovieByName(Movie movie) {
        return mdi0.fuzzyQueryByNameAll(movie);
    }

    @Override
    public ArrayList<Movie> queryMovieByType(Movie movie) {
        return mdi0.fuzzyQueryByTypeAll(movie);
    }

    @Override
    public ArrayList<Movie> queryMovieByField(Movie movie, String field) {
        return mdi0.fuzzyQueryByFieldAll(movie,field);
    }

    @Override
    public ArrayList<Movie> queryMovieByScore(double min, double max) {
        return mdi0.queryMovieByScore(min,max);
    }

    @Override
    public ArrayList<Movie> queryMovieAll() {
        return mdi0.queryAll(Movie.class,"select id,name,type,director,protagonist,duration,detail,score,labels,isReleased from movie");
    }

    @Override
    public int updateName(int id, String changeString) {
        return  mdi0.update("update movie set name = ? where id  = ?",changeString,id);
    }

    @Override
    public int updateType(int id, String changeString) {
        return  mdi0.update("update movie set type = ? where id  = ?",changeString,id);
    }

    @Override
    public int updateDirector(int id, String changeString) {
        return  mdi0.update("update movie set director = ? where id  = ?",changeString,id);
    }

    @Override
    public int updateProtagonist(int id, String changeString) {
        return  mdi0.update("update movie set protagonist = ? where id  = ?",changeString,id);
    }

    @Override
    public int updateDuration(int id, String changeString) {
        return  mdi0.update("update movie set duration = ? where id  = ?",changeString,id);
    }

    @Override
    public int updateDetail(int id, String changeString) {
        return  mdi0.update("update movie set detail = ? where id  = ?",changeString,id);
    }

    @Override
    public int updateScore(int id, Double positiveDouble) {
        return  mdi0.update("update movie set score = ? where id  = ?",positiveDouble,id);
    }

    @Override
    public int updateLabels(int id, String changeString) {
        return  mdi0.update("update movie set labels = ? where id  = ?",changeString,id);
    }

    @Override
    public int updateIsReleased(int id, boolean aBoolean) {
        return  mdi0.update("update movie set name = ? where id  = ?",aBoolean,id);
    }

    @Override
    public int deleteMovie(int id) {
        return  mdi0.update("delete from movie where id =?",id);
    }

}
