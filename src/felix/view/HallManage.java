package felix.view;

import felix.biz.imp0.*;
import felix.entity.Hall;
import felix.entity.Session;
import felix.util.MyUtil;
import felix.util.Print;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Scanner;

public class HallManage {
    private final Scanner scanner = new Scanner(System.in);
    private String choice;
    private final CinemaBizImp0 cbi0 = new CinemaBizImp0();
    private final HallBizImp0 hbi0 = new HallBizImp0();
    private final SessionBizImp0 sbi0 = new SessionBizImp0();
    private final static CinemaManage cm = new CinemaManage();
    private final SessionManage sm = new SessionManage();


    public void hallManage() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========放映厅管理==========");
            System.out.println("1.新增放映厅");
            System.out.println("2.查询放映厅");
            System.out.println("3.更改放映厅信息");
            System.out.println("4.删除放映厅");
            System.out.println("0.返回上级界面");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    createHall();
                    break;
                case "2":
                    queryHall();
                    break;
                case "3":
                    updateHall();
                    break;
                case "4":
                    deleteHall();
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
    public void createHall() {
        do {
            System.out.println("放映厅名称:");
            String name = scanner.next();
            MyUtil.showInfo(cbi0.queryCinemaAll(), "影院列表");
            int cid = cm.isCinemaIdExistAndGet();
            String capacity;
            // 放映厅大小只能在给定的3个选项中选
            while (true) {
                System.out.println("放映厅大小(S/M/L)");
                choice = scanner.next();
                if (choice.equals("S") || choice.equals("M") || choice.equals("L")) {
                    capacity = choice;
                    break;
                }
            }
            Hall hall = new Hall(name, cid, capacity);
            MyUtil.showIsOk(hbi0.create(hall), "添加成功", "添加失败");
        } while (MyUtil.isGoOn("是否继续添加放映厅(y/n)", "n"));
    }

    private void queryHall() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========放映厅查询==========");
            System.out.println("1.按影厅名查询");
            System.out.println("2.按影院id查询");
            System.out.println("3.按容量查询");
            System.out.println("4.查看所有");
            System.out.println("0.返回上级界面");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    queryHallByName();
                    break;
                case "2":
                    queryHallByCid();
                    break;
                case "3":
                    queryHallByCapacity();
                    break;
                case "4":
                    queryHallAll();
                    break;
                default:
                    if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "y")) {
                        flag = 100;
                        break;
                    }
            }
        }
    }

    private void queryHallAll() {
        do {
            MyUtil.showInfo(hbi0.queryHallAll(), "放映厅列表");
        } while (MyUtil.isGoOn("是否继再次查看(y/n)", "n"));
    }

    @Test
    public void queryHallByName() {
        do {
            System.out.println("输入要查询的放映厅名称:");
            String name = scanner.next();
            Hall hall = new Hall(name, 0, null);
            ArrayList<Hall> rs = hbi0.queryHallByName(hall);
            MyUtil.showInfo(rs, "放映厅结果");
        } while (MyUtil.isGoOn("是否继续查询(y/n)", "n"));
    }

    @Test
    public void queryHallByCid() {
        do {
            System.out.println("输入要查询对应影院id:");
            int id = Print.getPositiveInt();
            Hall hall = new Hall(null, id, null);
            ArrayList<Hall> rs = hbi0.queryHallByCid(hall);
            MyUtil.showInfo(rs, "放映厅结果");
        } while (MyUtil.isGoOn("是否继续查询(y/n)", "n"));
    }

    @Test
    public void queryHallByCapacity() {
        do {
            System.out.println("输入要查询的容量大小(S/M/L):");
            String capacity = scanner.next();
            Hall hall = new Hall(null, 0, capacity);
            ArrayList<Hall> rs = hbi0.queryHallByCapacity(hall);
            MyUtil.showInfo(rs, "放映厅结果");
        } while (MyUtil.isGoOn("是否继续查询(y/n)", "n"));
    }

    @Test
    public void updateHall() {
        do {
            System.out.println("==========放映厅信息更改==========");
            ArrayList<Hall> rs = hbi0.queryHallAll();
            MyUtil.showInfo(rs, "放映厅列表");
            int id = isHallIdExistAndGet();
            do {
                System.out.println("请输入要更改的字段");
                String field = scanner.next();
                String changeString;
                int tmp = -1;
                switch (field) {
                    case "name":
                        System.out.println("请输入更改后的信息");
                        changeString = scanner.next();
                        tmp = hbi0.updateName(id, changeString);
                        break;
                    case "cid":
                        System.out.println("请输入更改后的信息");
                        int cid = Print.getPositiveInt();
                        tmp = hbi0.updateCid(id, cid);
                        break;
                    case "capacity":
                        System.out.println("请输入更改后的信息");
                        changeString = scanner.next();
                        tmp = hbi0.updateCapacity(id, changeString);
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
    public void deleteHall() {
        do {
            System.out.println("==========删除放映厅==========");
            ArrayList<Hall> rs = hbi0.queryHallAll();
            MyUtil.showInfo(rs, "放映厅列表");
            int hid = isHallIdExistAndGet();
            ArrayList<Session> hrs = sbi0.querySessionByHid(new Session(hid, 0, null, 0));
            for (Session hr : hrs) {
                if (sm.SessionCanNotDelete(hr.getId())) {
                    System.out.println("删除放映厅存在场次有票已被售出且未放映，该放映厅不能删除");
                    return;
                }
            }
            hbi0.deleteHall(hid);
            System.out.println("删除成功");
        } while (MyUtil.isGoOn("是否返回上层界面(y/n)", "y"));
    }

    /**
     * @return int
     * @author Felix
     * @date 2020/7/3 17:55
     * @describe 获取一个存在的放映厅id
     */
    public int isHallIdExistAndGet() {
        int hid;
        while (true) {
            ArrayList<Hall> hallList = hbi0.queryHallAll();
            System.out.println("选择放映厅id");
            hid = Print.getPositiveInt();
            ArrayList<Integer> hidList = new ArrayList<>();
            for (Hall hall : hallList) {
                hidList.add(hall.getId());
            }
            if (!hidList.contains(hid)) {
                System.out.println("无此放映厅id，请重新输入");
            } else {
                break;
            }
        }
        return hid;
    }

}
