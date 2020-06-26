package felix.dao;



import felix.entity.Movie;

import java.util.ArrayList;

public interface MovieDao {
    public boolean createMovie(Movie movie);

    public Movie queryByName(Movie movie);

    public ArrayList<Movie> queryByNameAll(Movie movie);

    public ArrayList<Movie> fuzzyQueryByNameAll(Movie movie);

    public ArrayList<Movie> fuzzyQueryByAddressAll(Movie movie);
}
