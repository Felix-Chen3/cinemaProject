package felix.dao;


import felix.entity.Movie;

import java.util.ArrayList;

public interface MovieDao {
    boolean createMovie(Movie movie);

    Movie queryById(int id);


    Movie queryByName(Movie movie);

    ArrayList<Movie> queryByNameAll(Movie movie);

    ArrayList<Movie> fuzzyQueryByNameAll(Movie movie);

    ArrayList<Movie> fuzzyQueryByTypeAll(Movie movie);

    ArrayList<Movie> fuzzyQueryByFieldAll(Movie movie, String field);

    ArrayList<Movie> queryMovieByScore(double min, double max);
}
