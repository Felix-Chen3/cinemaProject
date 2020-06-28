package felix.dao;



import felix.entity.Movie;

import java.util.ArrayList;

public interface MovieDao {
    public boolean createMovie(Movie movie);

    public Movie queryByName(Movie movie);

    public ArrayList<Movie> queryByNameAll(Movie movie);

    public ArrayList<Movie> fuzzyQueryByNameAll(Movie movie);

    ArrayList<Movie> fuzzyQueryByTypeAll(Movie movie);

    ArrayList<Movie> fuzzyQueryByFieldAll(Movie movie, String field);

    ArrayList<Movie> queryMovieByScore(double min, double max);
}
