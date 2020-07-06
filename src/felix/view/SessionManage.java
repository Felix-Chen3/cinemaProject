package felix.view;

import felix.biz.imp0.*;
import felix.dao.Imp0.*;
import felix.entity.QuerySession;
import felix.entity.Session;
import felix.util.MyUtil;
import felix.util.Print;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class SessionManage {
    private final Scanner scanner = new Scanner(System.in);
    private String choice;
    private final MovieDaoImp0 mdi0 = new MovieDaoImp0();
    private final HallBizImp0 hbi0 = new HallBizImp0();
    private final SessionBizImp0 sbi0 = new SessionBizImp0();
    private final TicketDaoImp0 tdi0 = new TicketDaoImp0();
    private final QuerySessionDaoImp0 dsdi0 = new QuerySessionDaoImp0();
    private final MovieManage mm = new MovieManage();
    private final static HallManage hm = new HallManage();



    @Test
    public void sessionManage() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========场次管理==========");
            System.out.println("1.新增场次");
            System.out.println("2.查询场次");
            System.out.println("3.更改场次信息");
            System.out.println("4.删除场次");
            System.out.println("0.返回上级界面");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    createSession();
                    break;
                case "2":
                    querySession();
                    break;
                case "3":
                    updateSession();
                    break;
                case "4":
                    deleteSession();
                    break;
                default:
                    if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "y")) {
                        flag = 100;
                        break;
                    }
            }
        }

    }

    @Test
    public void createSession() {
        do {
            MyUtil.showInfo(hbi0.queryHallAll(), "放映厅列表");
            int hid = hm.isHallIdExistAndGet();
            int mid = mm.isMovieIdExistAndGet();
            System.out.println("放映时间:");
            LocalDateTime time = Print.getLocalDateTime();
            System.out.println("价格");
            int price = Print.getPositiveInt();
            Session session = new Session(hid, mid, time, price);
            MyUtil.showIsOk(sbi0.create(session), "添加成功", "添加失败,请检查时间是否冲突");
        } while (MyUtil.isGoOn("是否继续添加场次(y/n)", "n"));
    }

    private void querySession() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========场次查询==========");
            System.out.println("1.按放映厅id查询");
            System.out.println("2.按电影id查询");
            System.out.println("3.按播放时间区间查询");
            System.out.println("4.按价格区间查询");
            System.out.println("5.查询所有");
            System.out.println("0.返回上级界面");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    querySessionByHid();
                    break;
                case "2":
                    querySessionByMid();
                    break;
                case "3":
                    querySessionByTime();
                    break;
                case "4":
                    querySessionByPrice();
                    break;
                case "5":
                    querySessionAll();
                    break;
                default:
                    if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "y")) {
                        flag = 100;
                        break;
                    }
            }
        }
    }

    private void querySessionAll() {
        do {
            MyUtil.showInfo(sbi0.querySessionAll(), "场次列表");
        } while (MyUtil.isGoOn("是否再次查看(y/n)", "n"));
    }

    private void querySessionByHid() {
        do {
            System.out.println("输入要查询的放映厅id:");
            int hid = Print.getPositiveInt();
            Session session = new Session(hid, 0, null, 0);
            ArrayList<Session> rs = sbi0.querySessionByHid(session);
            MyUtil.showInfo(rs, "场次结果");
        } while (MyUtil.isGoOn("是否继续查询(y/n)", "n"));
    }

    private void querySessionByMid() {
        do {
            System.out.println("输入要查询的电影id:");
            int mid = Print.getPositiveInt();
            Session session = new Session(0, mid, null, 0);
            ArrayList<Session> rs = sbi0.querySessionByMid(session);
            MyUtil.showInfo(rs, "场次结果");
        } while (MyUtil.isGoOn("是否继续查询(y/n)", "n"));
    }

    @Test
    public void querySessionByTime() {
        do {
            System.out.println("输入要查询的起始时间:");
            LocalDateTime min = Print.getLocalDateTime();
            System.out.println("输入要查询的截至时间:");
            LocalDateTime max = Print.getLocalDateTime();
            ArrayList<Session> rs = sbi0.querySessionByTime(min, max);
            MyUtil.showInfo(rs, "场次结果");
        } while (MyUtil.isGoOn("是否继续查询(y/n)", "n"));
    }

    @Test

    public void querySessionByPrice() {
        do {
            System.out.println("输入要查询的价格下限:");
            Double min = Print.getPositiveDouble();
            System.out.println("输入要查询的价格上限:");
            Double max = Print.getPositiveDouble();
            ArrayList<Session> rs = sbi0.querySessionByPrice(min, max);
            MyUtil.showInfo(rs, "场次结果");
        } while (MyUtil.isGoOn("是否继续查询(y/n)", "n"));
    }

    @Test
    public void updateSession() {
        do {
            System.out.println("==========场次信息更改==========");
            ArrayList<Session> rs = sbi0.querySessionAll();
            MyUtil.showInfo(rs, "场次列表");
            System.out.println("请输入要更改场次id");
            int id = Print.getPositiveInt();
            do {
                System.out.println("请输入要更改的字段");
                String field = scanner.next();
                int tmp = -1;
                System.out.println("请输入更改后的信息");
                switch (field) {
                    case "hid":
                        int hid = Print.getPositiveInt();
                        tmp = sbi0.updateHid(id, hid);
                        break;
                    case "mid":
                        int mid = Print.getPositiveInt();
                        tmp = sbi0.updateMid(id, mid);
                        break;
                    case "time":
                        LocalDateTime time = Print.getLocalDateTime();
                        tmp = sbi0.updateTime(id, time);
                        break;
                    case "price":
                        double price = Print.getPositiveDouble();
                        tmp = sbi0.updatePrice(id, price);
                        break;
                    default:
                        if (!MyUtil.isGoOn("字段名称有误，是否重新输入(y/n)", "n")) {
                            break;
                        }
                }
                boolean updateResult = false;
                if (tmp > 0) {
                    updateResult = true;
                }
                MyUtil.showIsOk(updateResult, "修改成功", "修改失败");
            } while (MyUtil.isGoOn("是否继续修改本条数据(y/n)", "n"));

        } while (MyUtil.isGoOn("是否返回上层界面(y/n)", "y"));
    }

    @Test
    public void deleteSession() {
        while (true) {
            System.out.println("==========删除场次==========");
            ArrayList<Session> rs = sbi0.querySessionAll();
            MyUtil.showInfo(rs, "场次列表");
            int sid = isSessionIdExistAndGet();
            if (SessionCanNotDelete(sid)) {
                System.out.println("删除的场次有票已被售出且未放映，该场次不能删除");
                if (!MyUtil.isGoOn("是否返回上层界面(y/n)", "y")) {
                    return;
                } else {
                    continue;
                }
            }
            int tmp = sbi0.deleteSession(sid);
            boolean updateResult = false;
            if (tmp > 0) {
                updateResult = true;
            }
            MyUtil.showIsOk(updateResult, "删除成功", "删除失败");
            if (!MyUtil.isGoOn("是否返回上层界面(y/n)", "y")) break;
        }
    }

    /**
     * @return boolean
     * @author Felix
     * @date 2020/7/3 17:43
     * @describe 确认该场次是否能被删除：通过取出放映时间与电影时常，若该场次已经买票且未放映，则该场次不能能被删除，
     */
    public boolean SessionCanNotDelete(int sid) {
        boolean isSold = (tdi0.queryTicketAllBySid(sid).size() != 0);
        if (isSold) {
            Session session = sbi0.querySessionById(sid);
            LocalDateTime start = session.getTime();
            int length = Integer.parseInt(mdi0.queryById(session.getMid()).getDuration());
            LocalDateTime end = start.plusMinutes(length);
            LocalDateTime now = LocalDateTime.now();
            return now.compareTo(end) <= 0;
        } else return false;
    }

    /**
     * @return int
     * @author Felix
     * @date 2020/7/3 17:56
     * @describe 获取一个存在的场次id
     */
    private int isSessionIdExistAndGet() {
        int sid;
        while (true) {
            ArrayList<Session> sessionList = sbi0.querySessionAll();
            System.out.println("选择场次id");
            sid = Print.getPositiveInt();
            ArrayList<Integer> sidList = new ArrayList<>();
            for (Session session : sessionList) {
                sidList.add(session.getId());
            }
            if (!sidList.contains(sid)) {
                System.out.println("无此场次id，请重新输入");
            } else {
                break;
            }
        }
        return sid;
    }

    /**
     * @return int
     * @author Felix
     * @date 2020/7/3 17:56
     * @describe 通过电影id，获取一个对应存在的场次id
     */
    int isSessionIdExistAndGet(int mid) {
        int sid;
        while (true) {
            ArrayList<QuerySession> sessionList = dsdi0.show(mid);
            if (sessionList.size() == 0) {
                System.out.println("该电影暂无排场，请选择其他电影观看");
                return 0;
            }
            MyUtil.showInfo(sessionList, "场次列表");
            System.out.println("输入想要购买的场次id");
            sid = Print.getPositiveInt();
            ArrayList<Integer> sidList = new ArrayList<>();
            for (QuerySession querySession : sessionList) {
                sidList.add(querySession.getId());
            }
            if (!sidList.contains(sid)) {
                System.out.println("无此场次id，请重新输入");
            } else {
                break;
            }
        }
        return sid;
    }

}
