package felix.view;

import felix.biz.imp0.*;
import felix.entity.Collection;
import felix.entity.Movie;
import felix.entity.Session;
import felix.entity.Ticket;
import felix.util.MyUtil;
import java.util.ArrayList;
import java.util.Random;


public class Recommend {

    private final Random random = new Random();
    private final MovieBizImp0 mbi0 = new MovieBizImp0();
    private final SessionBizImp0 sbi0 = new SessionBizImp0();
    private final TicketBizImp0 tbi0 = new TicketBizImp0();
    private final CollectionBizImp0 collectionBizImp0 = new CollectionBizImp0();


    /**
     * @return void
     * @author Felix
     * @date 2020/7/3 18:05
     * @describe 向用户推荐电影
     */

    void recommend(int uid) {
        do {
            ArrayList<Movie> recommend = new ArrayList<>();
            //若有收藏，优先推荐收藏电影
            ArrayList<Collection> collections = collectionBizImp0.queryCollectionByUid(uid);
            if (collections != null) {
                for (Collection c : collections) {
                    recommend.add(mbi0.queryMovieById(c.getMid()));
                }
            }
            ArrayList<Ticket> tickets = tbi0.queryTicketByUid(uid);
            //若购过票，根据所购票的type和labels来推荐电影
            if (tickets != null) {
                for (Ticket t : tickets) {
                    int sid = t.getSid();
                    Session session = sbi0.querySessionById(sid);
                    int mid = session.getMid();
                    Movie movie = mbi0.queryMovieById(mid);
                    String type = movie.getType();
                    String[] labels = movie.getLabels().split("\\|");
                    ArrayList<Movie> movieAll = mbi0.queryMovieAll();
                    for (Movie m : movieAll) {
                        String movieType = m.getType();
                        String movieLabels = m.getLabels();
                        if (!m.equals(movie) && !recommend.contains(m)) {
                            if (movieType.equals(type)) {
                                recommend.add(m);
                                continue;
                            }
                            for (String label : labels) {
                                if (movieLabels != null && movieLabels.contains(label)) {
                                    recommend.add(m);
                                }
                            }
                        }
                    }
                    if (recommend.size() > 5) break;
                }
                //不足5个随机填充至5个
                while (recommend.size() < 5) {
                    ArrayList<Movie> movieAll = mbi0.queryMovieAll();
                    Movie movie = movieAll.get(random.nextInt(movieAll.size()));
                    if (!recommend.contains(movie)) {
                        recommend.add(movie);
                    }
                }
            }
            //若都无记录，则随机推荐5个电影
            else {
                while (recommend.size() < 5) {
                    ArrayList<Movie> movieAll = mbi0.queryMovieAll();
                    Movie movie = movieAll.get(random.nextInt(movieAll.size()));
                    if (!recommend.contains(movie)) {
                        recommend.add(movie);
                    }
                }
            }
            MyUtil.showInfo(recommend, "热映推荐");
        } while (MyUtil.isGoOn("是否重新推荐(y/n)", "n"));
    }


}
