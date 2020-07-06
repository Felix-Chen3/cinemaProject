package felix.view;

import felix.biz.imp0.*;
import felix.entity.Cinema;
import felix.entity.Hall;
import felix.entity.Session;
import felix.util.MyUtil;
import felix.util.Print;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Scanner;

public class CinemaManage {
    private final Scanner scanner = new Scanner(System.in);
    private String choice;
    private final CinemaBizImp0 cbi0 = new CinemaBizImp0();
    private final HallBizImp0 hbi0 = new HallBizImp0();
    private final SessionBizImp0 sbi0 = new SessionBizImp0();
    private final static SessionManage sm = new SessionManage();


    public void cinemaManege() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========影院管理==========");
            System.out.println("1.添加影院");
            System.out.println("2.查询影院");
            System.out.println("3.更改影院信息");
            System.out.println("4.删除影院");
            System.out.println("0.返回上级界面");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    createCinema();
                    break;
                case "2":
                    queryCinema();
                    break;
                case "3":
                    updateCinema();
                    break;
                case "4":
                    deleteCinema();
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
    public void createCinema() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("影院名称:");
            String name = scanner.next();
            System.out.println("影院地址:");
            String address = scanner.next();
            Cinema cinema = new Cinema(name, address);
            MyUtil.showIsOk(cbi0.create(cinema), "添加成功", "添加失败");
            if (!MyUtil.isGoOn("是否继续添加(y/n)", "n")) {
                flag = 100;
            }
        }
    }

    @Test
    public void queryCinema() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========影院查询==========");
            System.out.println("1.按影院名称查询");
            System.out.println("2.按地址位置查询");
            System.out.println("3.查看所有");
            System.out.println("0.返回上级界面");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    queryCinemaByName();
                    break;
                case "2":
                    queryCinemaByAddress();
                    break;
                case "3":
                    queryCinemaAll();
                    break;
                default:
                    if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "y")) {
                        flag = 100;
                        break;
                    }
            }
        }
    }

    private void queryCinemaAll() {
        ArrayList<Cinema> rs = cbi0.queryCinemaAll();
        MyUtil.showInfo(rs, "影院列表");
    }

    @Test
    public void queryCinemaByName() {
        do {
            System.out.println("输入要查询的影院名称:");
            String name = scanner.next();
            Cinema cinema = new Cinema(name, null);
            ArrayList<Cinema> rs = cbi0.queryCinemaByName(cinema);
            MyUtil.showInfo(rs, "影院结果");
        } while (MyUtil.isGoOn("是否继续查询(y/n)", "n"));
    }

    @Test
    public void queryCinemaByAddress() {
        do {
            System.out.println("输入要查询的地址:");
            String address = scanner.next();
            Cinema cinema = new Cinema(null, address);
            ArrayList<Cinema> rs = cbi0.queryCinemaByAddress(cinema);
            MyUtil.showInfo(rs, "影院结果");
        } while (MyUtil.isGoOn("是否继续查询(y/n)", "n"));
    }

    @Test
    public void updateCinema() {
        do {
            System.out.println("==========影院信息更改==========");
            ArrayList<Cinema> rs = cbi0.queryCinemaAll();
            MyUtil.showInfo(rs, "影院列表");
            System.out.println("请输入更改影院id");
            int id = Print.getPositiveInt();
            do {
                System.out.println("请输入要更改的字段");
                String field = scanner.next();
                System.out.println("请输入更改后的信息");
                String changeString = scanner.next();
                int tmp = -1;
                switch (field) {
                    case "name":
                        tmp = cbi0.updateName(id, changeString);
                        break;
                    case "address":
                        tmp = cbi0.updateAddress(id, changeString);
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
    public void deleteCinema() {
        do {
            System.out.println("==========删除影院==========");
            ArrayList<Cinema> rs = cbi0.queryCinemaAll();
            MyUtil.showInfo(rs, "影院列表");
            int cid = isCinemaIdExistAndGet();
            ArrayList<Hall> hrs = hbi0.queryHallByCid(new Hall(null, cid, null));
            for (Hall hr : hrs) {
                ArrayList<Session> srs = sbi0.querySessionByHid(new Session(hr.getId(), 0, null, 0));
                for (Session sr : srs) {
                    if (sm.SessionCanNotDelete(sr.getId())) {
                        System.out.println("删除影院存在场次有票已被售出且未放映，该影院不能删除");
                        return;
                    }
                }
            }
            cbi0.deleteCinema(cid);
            System.out.println("删除成功");
        } while (MyUtil.isGoOn("是否返回上层界面(y/n)", "y"));
    }

    /**
     * @return int
     * @author Felix
     * @date 2020/7/3 17:41
     * @describe 获取一个存在的影院id
     */
    public int isCinemaIdExistAndGet() {
        int cid;
        while (true) {
            ArrayList<Cinema> cinemaList = cbi0.queryCinemaAll();
            System.out.println("选择影院id");
            cid = Print.getPositiveInt();
            ArrayList<Integer> cidList = new ArrayList<>();
            for (Cinema cinema : cinemaList) {
                cidList.add(cinema.getId());
            }
            if (!cidList.contains(cid)) {
                System.out.println("无此影院id，请重新输入");
            } else {
                break;
            }
        }
        return cid;
    }



}
