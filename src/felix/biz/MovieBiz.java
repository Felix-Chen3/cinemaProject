package felix.biz;


import felix.entity.Movie;

import java.util.ArrayList;

public interface MovieBiz {
    public boolean create(Movie movie);

    public boolean delete(Movie movie);

    public ArrayList<Movie> queryMovieByName(Movie movie);
}
