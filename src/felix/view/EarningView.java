package felix.view;

import felix.biz.imp0.*;
import felix.entity.Hall;
import felix.entity.MyHashMap;
import felix.entity.Session;
import felix.entity.Ticket;
import felix.util.MyUtil;
import org.junit.Test;
import java.text.DecimalFormat;
import java.util.*;

public class EarningView {
    private final Scanner scanner = new Scanner(System.in);
    private final CinemaBizImp0 cbi0 = new CinemaBizImp0();
    private final MovieBizImp0 mbi0 = new MovieBizImp0();
    private final HallBizImp0 hbi0 = new HallBizImp0();
    private final SessionBizImp0 sbi0 = new SessionBizImp0();
    private final TicketBizImp0 tbi0 = new TicketBizImp0();
    private final DecimalFormat df = new DecimalFormat("0.00");
    @Test
    public void earningView() {
        do {
            System.out.println("==========查看收益==========");
            System.out.println("1.根据电影票房查看");
            System.out.println("2.根据影院地址查看");
            System.out.println("0.返回上一级");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    earningViewByMovie();
                    break;
                case "2":
                    earningViewByCinema();
                    break;
                default:
                    break;
            }
        } while (MyUtil.isGoOn("是否返回上一级(y/n)", "y"));
    }

    /**
     * @return void
     * @author Felix
     * @date 2020/7/6 16:11
     * @describe 根据影院票房降序排列
     */
    private void earningViewByCinema() {
        ArrayList<Ticket> tickets = tbi0.queryTicketAll();
        MyHashMap map = new MyHashMap();
        for (Ticket t : tickets) {
            int sid = t.getSid();
            Session session = sbi0.querySessionById(sid);
            double price = session.getPrice();
            int hid = session.getHid();
            Hall hall = hbi0.queryHallById(hid);
            int cid = hall.getCid();
            map.put(cid, price);
        }
        List<Map.Entry<Integer, Double>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        System.out.println("==========影院盈利排行榜==========");
        for (Map.Entry<Integer, Double> e : list) {
            String cinemaName = cbi0.queryCinemaById(e.getKey()).getName();
            String cinemaAddress = cbi0.queryCinemaById(e.getKey()).getAddress();
            System.out.println("影院id:" + e.getKey() + "\t影院名称:" + cinemaName + "\t影院地址:" + cinemaAddress + "\t影院总票房" + df.format(e.getValue()));
        }
        System.out.println("=================================");

    }

    /**
     * @return void
     * @author Felix
     * @date 2020/7/5 0:25
     * @describe 根据电影票房降序排列
     */
    @Test
    public void earningViewByMovie() {
        ArrayList<Ticket> tickets = tbi0.queryTicketAll();
        MyHashMap map = new MyHashMap();
        for (Ticket t : tickets) {
            int sid = t.getSid();
            Session session = sbi0.querySessionById(sid);
            double price = session.getPrice();
            int mid = session.getMid();
            map.put(mid, price);
        }
        ArrayList<Map.Entry<Integer, Double>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        System.out.println("==========票房排行榜==========");
        for (Map.Entry<Integer, Double> e : list) {
            String movieName = mbi0.queryMovieById(e.getKey()).getName();
            System.out.println("电影id:" + e.getKey() + "\t电影名称:" + movieName + "\t电影票房" + df.format(e.getValue()));
        }
        System.out.println("==============================");
    }

}
