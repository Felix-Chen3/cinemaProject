package felix.biz;


import felix.entity.Cinema;
import felix.entity.Movie;

import java.util.ArrayList;

public interface MovieBiz {
    boolean create(Movie movie);

    ArrayList<Movie> queryMovieByName(Movie movie);

    ArrayList<Movie> queryMovieByType(Movie movie);

    ArrayList<Movie> queryMovieByField(Movie movie,String field);

    ArrayList<Movie> queryMovieByScore(double min, double max);

    ArrayList<Movie> queryMovieAll();

    int updateName(int id, String changeString);

    int updateType(int id, String changeString);

    int updateDirector(int id, String changeString);

    int updateProtagonist(int id, String changeString);

    int updateDuration(int id, String changeString);

    int updateDetail(int id, String changeString);

    int updateScore(int id, Double positiveDouble);

    int updateLabels(int id, String changeString);

    int updateIsReleased(int id, boolean aBoolean);

    int deleteMovie(int id);
}
