package felix.view;

import felix.biz.imp0.*;
import felix.dao.Imp0.*;
import felix.entity.Hall;
import felix.entity.Session;
import felix.entity.Ticket;
import felix.util.MyUtil;
import felix.util.Print;

import java.util.ArrayList;

public class TicketPurchase {
    private final HallBizImp0 hbi0 = new HallBizImp0();
    private final SessionBizImp0 sbi0 = new SessionBizImp0();
    private final UserBizImp0 ubi0 = new UserBizImp0();
    private final TicketBizImp0 tbi0 = new TicketBizImp0();
    private final TicketDaoImp0 tdi0 = new TicketDaoImp0();
    private final QuerySessionCapacityDaoImp0 qscdi0 = new QuerySessionCapacityDaoImp0();
    private final MovieManage mm = new MovieManage();
    private final SessionManage sessionManage = new SessionManage();
    private final UserAccount ua = new UserAccount();


    public void ticketPurchaseByMovie(int uid) {
        do {

            int mid = mm.isMovieIdExistAndGet();
            int sid = sessionManage.isSessionIdExistAndGet(mid);
            if (sid == 0) {
                continue;
            }
            System.out.println("输入想要购买的数量");
            int num = Print.getPositiveInt();
            while (!ubi0.checkEnoughBalance(uid, sid, num)) {
                System.out.println("余额不足,请充值后再试");
                if (!MyUtil.isGoOn("是否前往充值(y/n)", "y")) {
                    ua.recharge(uid);
                } else return;
            }
            for (int i = 0; i < num; ) {
                sessionShow(sid);
                int row = getLegalRow(sid);
                int column = getLegalColumn(sid);
                Ticket ticket = new Ticket(uid, sid, row, column);
                if (tbi0.create(ticket)) {
                    System.out.println("购买成功");
                    ubi0.deductBalance(uid, sid);
                    i++;
                } else {
                    System.out.println("购买失败，该座次已被购买");
                }
            }
        } while (MyUtil.isGoOn("是否继续购买其他场次电影票(y/n)", "n"));
    }

    /**
     * @return int
     * @author Felix
     * @date 2020/7/3 17:54
     * @describe 获取对应场次的合法座次
     */
    private int getLegalColumn(int sid) {
        Session session = sbi0.querySessionById(sid);
        Hall hall = hbi0.queryHallById(session.getHid());
        String size = hall.getCapacity();
        int columnMax = 0;
        int column;
        switch (size) {
            case "S":
                columnMax = 6;
                break;
            case "M":
                columnMax = 8;
                break;
            case "L":
                columnMax = 10;
                break;
        }
        while (true) {
            System.out.println("输入要购买的座次");
            column = Print.getPositiveInt();
            if (column > columnMax) {
                System.out.println("请检查输入座次是否存在");
            } else {
                break;
            }
        }
        return column;
    }

    /**
     * @return int
     * @author Felix
     * @date 2020/7/3 17:54
     * @describe 获取对应场次的合法排数
     */
    private int getLegalRow(int sid) {
        Session session = sbi0.querySessionById(sid);
        Hall hall = hbi0.queryHallById(session.getHid());
        String size = hall.getCapacity();
        int rowMax = 0;
        int row;
        switch (size) {
            case "S":
                rowMax = 5;
                break;
            case "M":
                rowMax = 7;
                break;
            case "L":
                rowMax = 9;
                break;
        }
        while (true) {
            System.out.println("输入要购买的排数");
            row = Print.getPositiveInt();
            if (row > rowMax) {
                System.out.println("请检查输入排数是否存在");
            } else {
                break;
            }
        }
        return row;
    }


    /**
     * @return void
     * @author Felix
     * @date 2020/7/3 17:57
     * @describe 将对应场次的剩余空座以一个二维平面图的输出结果展示
     */
    private void sessionShow(int sid) {
        int row = 0, column = 0;
        //确定对应场次的容量大小
        String capacity = qscdi0.querySessionByIdWithCapacity(sid).getCapacity();
        switch (capacity) {
            case "S":
                row = 5;
                column = 6;
                break;
            case "M":
                row = 7;
                column = 8;
                break;
            case "L":
                row = 9;
                column = 10;
                break;
            default:
                break;
        }
        //生成对应容量大小的二位数组
        boolean[][] seats = new boolean[row][column];
        ArrayList<Ticket> rs = tdi0.queryTicketAllBySid(sid);
        //将已被订票的座位进行填充
        if (rs != null) {
            for (Ticket r : rs) {
                int rowed = r.getRow();
                int columned = r.getColumn();
                seats[rowed - 1][columned - 1] = true;
            }
        }
        //输出所有座位状态
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (seats[i][j]) {
                    System.out.print("booked\t");
                } else {
                    System.out.print((i + 1) + "排" + (j + 1) + "座\t");
                }
            }
            System.out.println();
        }
    }
}
