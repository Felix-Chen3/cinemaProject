package felix.view;

import felix.biz.imp0.*;
import felix.entity.Session;
import felix.entity.Ticket;
import felix.util.MyUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CheckTicket {
    private final MovieBizImp0 mbi0 = new MovieBizImp0();
    private final HallBizImp0 hbi0 = new HallBizImp0();
    private final SessionBizImp0 sbi0 = new SessionBizImp0();
    private final TicketBizImp0 tbi0 = new TicketBizImp0();


    public void checkTicket(int uid) {
        do {
            ArrayList<Ticket> tickets = tbi0.queryTicketByUid(uid);
            if (tickets.size() == 0) {
                System.out.println("无购票记录");
                return;
            }
            boolean isEmpty1 = false;
            boolean isEmpty2 = false;
            System.out.println("===============未使用影票===============");
            for (Ticket t : tickets) {
                int sid = t.getSid();
                Session session = sbi0.querySessionById(sid);
                String movieName = mbi0.queryMovieById(session.getMid()).getName();
                LocalDateTime time = session.getTime();
                String hallName = hbi0.queryHallById(session.getHid()).getName();
                int row = t.getRow();
                int column = t.getColumn();
                if (time.compareTo(LocalDateTime.now()) >= 0) {
                    System.out.println("电影:" + movieName + "\t\t放映时间:" + time + "\t\t放映厅名称:" + hallName + "\t\t" + row + "排" + column + "座");
                    isEmpty1 = true;
                }
            }
            if (!isEmpty1) {
                System.out.println("无相关数据");
            }
            System.out.println("----------------------------------------");
            System.out.println("===============已过期影票================");
            for (Ticket t : tickets) {
                int sid = t.getSid();
                Session session = sbi0.querySessionById(sid);
                String movieName = mbi0.queryMovieById(session.getMid()).getName();
                LocalDateTime time = session.getTime();
                String hallName = hbi0.queryHallById(session.getHid()).getName();
                int row = t.getRow();
                int column = t.getColumn();
                if (time.compareTo(LocalDateTime.now()) < 0) {
                    System.out.println("电影:" + movieName + "\t\t放映时间:" + time + "\t\t放映厅名称:" + hallName + "\t\t" + row + "排" + column + "座");
                    isEmpty2 = true;
                }
            }
            if (!isEmpty2) {
                System.out.println("无相关数据");
            }
            System.out.println("----------------------------------------");
        } while (MyUtil.isGoOn("是否返回上级界面(y/n)", "y"));
    }
}
