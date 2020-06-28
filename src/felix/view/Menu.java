package felix.view;

import felix.biz.imp0.*;
import felix.entity.Cinema;
import felix.entity.Hall;
import felix.entity.Movie;
import felix.entity.Session;
import felix.util.MyUtil;
import felix.util.Print;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private String choice;
    private final AdminBizImp0 abi0 = new AdminBizImp0();
    private final CinemaBizImp0 cbi0 = new CinemaBizImp0();
    private final MovieBizImp0 mbi0 = new MovieBizImp0();
    private final HallBizImp0 hbi0 = new HallBizImp0();
    private final SessionBizImp0 sbi0 = new SessionBizImp0();

    @Test
    public void mainMenu() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========欢迎来到影片综合购票管理系统==========");
            System.out.println("请选择您的身份");
            System.out.println("1.管理员");
            System.out.println("2.个人用户");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    adminCheck();
                    break;
                case "2":
                    userCheck();
                    break;
                default:
                    if (!MyUtil.isGoOn("是否退出登录(y/n)", "y")) {
                        flag = 100;
                        break;
                    }
            }
        }
    }

    @Test
    public void adminCheck() {
        while (true) {
            System.out.println("==========身份校验==========");
            System.out.println("输入账号:");
            String account = scanner.next();
            System.out.println("输入密码:");
            String password = scanner.next();
            if (abi0.login(account, password)) {
                System.out.println("登录成功");
                adminMenu();
                break;
            } else {
                System.out.println("登录失败，请确认账号密码是否正确");
            }
            if (!MyUtil.isGoOn("是否继续登录(y/n)", "n")) {
                break;
            }
        }
    }

    @Test
    public void adminMenu() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========管理员权限==========");
            System.out.println("1.影院管理");
            System.out.println("2.放映厅管理");
            System.out.println("3.影片管理");
            System.out.println("4.场次管理");
            System.out.println("5.退出登录");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    cinemaManege();
                    break;
                case "2":
                    hallManage();
                    break;
                case "3":
                    movieManage();
                    break;
                case "4":
                    sessionManage();
                    break;
                default:
                    if (!MyUtil.isGoOn("是否退出登录(y/n)", "y")) {
                        flag = 100;
                        break;
                    }
            }
        }
    }

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
        while (true) {
            System.out.println("影院名称:");
            String name = scanner.next();
            System.out.println("影院地址:");
            String address = scanner.next();
            Cinema cinema = new Cinema(name, address);
            MyUtil.showIsOk(cbi0.create(cinema), "添加成功", "添加失败");
            if (!MyUtil.isGoOn("是否继续添加(y/n)", "n")) {
                break;
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
        while (true) {
            System.out.println("输入要查询的影院名称:");
            String name = scanner.next();
            Cinema cinema = new Cinema(name, null);
            ArrayList<Cinema> rs = cbi0.queryCinemaByName(cinema);
            MyUtil.showInfo(rs, "影院结果");
            if (!MyUtil.isGoOn("是否继续查询(y/n)", "")) {
                break;
            }
        }
    }

    @Test
    public void queryCinemaByAddress() {
        while (true) {
            System.out.println("输入要查询的地址:");
            String address = scanner.next();
            Cinema cinema = new Cinema(null, address);
            ArrayList<Cinema> rs = cbi0.queryCinemaByAddress(cinema);
            MyUtil.showInfo(rs, "影院结果");
            if (!MyUtil.isGoOn("是否继续查询(y/n)", "n")) {
                break;
            }
        }
    }

    @Test
    public void updateCinema() {
        while (true) {
            System.out.println("==========影院信息更改==========");
            ArrayList<Cinema> rs = cbi0.queryCinemaAll();
            MyUtil.showInfo(rs, "影院列表");
            System.out.println("请输入更改影院id");
            int id = Print.getPositiveInt();
            while (true) {
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
                if (!MyUtil.isGoOn("是否继续修改本条数据(y/n)", "n")) {
                    break;
                }
            }
            if (!MyUtil.isGoOn("是否返回上层界面(y/n)", "y")) break;

        }

    }

    @Test
    public void deleteCinema() {
        while (true) {
            System.out.println("==========删除影院==========");
            ArrayList<Cinema> rs = cbi0.queryCinemaAll();
            MyUtil.showInfo(rs, "影院列表");
            System.out.println("请输入删除影院id");
            int id = Print.getPositiveInt();
            int tmp = cbi0.deleteCinema(id);
            boolean updateResult = false;
            if (tmp > 0) {
                updateResult = true;
            }
            MyUtil.showIsOk(updateResult, "删除成功", "删除失败");
            if (!MyUtil.isGoOn("是否返回上层界面(y/n)", "y")) break;
        }
    }

    @Test
    public void movieManage() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========影片管理==========");
            System.out.println("1.上架影片");
            System.out.println("2.查询影片");
            System.out.println("3.更改影片信息");
            System.out.println("4.删除影片");
            System.out.println("0.返回上级界面");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    createMovie();
                    break;
                case "2":
                    queryMovie();
                    break;
                case "3":
                    updateMovie();
                    break;
                case "4":
                    deleteMovie();
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
    public void createMovie() {
        while (true) {
            String name = null;
            String type = null;
            String director = null;
            String protagonists = null;
            String duration = null;
            String detail = null;
            double score = 0;
            System.out.println("影片名称:");
            name = scanner.next();
            System.out.println("影片类型:");
            type = MyUtil.scanInfo("类型");
            System.out.println("导演:");
            director = scanner.next();
            protagonists = MyUtil.scanInfo("主演");
            System.out.println("影片时长:");
            duration = scanner.next();
            System.out.println("影片详情:");
            detail = scanner.next();
            System.out.println("影片评分:");
            score = Print.getPositiveDouble();
            Movie movie = null;
            if (!MyUtil.isGoOn("是否额外添加标签(y/n)", "y")) {
                String labels = MyUtil.scanInfo("标签");
                movie = new Movie(name, type, director, protagonists, duration, detail, score, labels);
            } else {
                movie = new Movie(name, type, director, protagonists, duration, detail, score);
            }
            MyUtil.showIsOk(mbi0.create(movie), "添加成功", "添加失败");
            if (!MyUtil.isGoOn("是否继续添加影片(y/n)", "n")) {
                break;
            }
        }
    }

    @Test
    public void queryMovie() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========影片查询==========");
            System.out.println("1.按片名查询");
            System.out.println("2.按类型查询");
            System.out.println("3.按导演查询");
            System.out.println("4.按主演查询");
            System.out.println("5.按评分查询");
            System.out.println("6.按标签查询");
            System.out.println("7.按上下架状态查询");
            System.out.println("0.返回上级界面");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    queryMovieByName();
                    break;
                case "2":
                    queryMovieByType();
                    break;
                case "3":
                    queryMovieByDirector();
                    break;
                case "4":
                    queryMovieByProtagonist();
                    break;
                case "5":
                    queryMovieByScore();
                    break;
                case "6":
                    queryMovieByLabels();
                    break;
                case "7":
                    queryMovieReleased();
                    break;
                default:
                    if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "y")) {
                        flag = 100;
                        break;
                    }
            }
        }
    }

    private void queryMovieReleased() {
        System.out.println("查阅上架电影请输入1，已下架电影请输入2");
        queryMovieByFieldM("isReleased");
    }


    public void queryMovieByFieldM(String field) {
        while (true) {
            System.out.println("输入关键字:");
            String tmp = scanner.next();
            Movie movie = null;
            switch (field) {
                case "name":
                    movie = new Movie(tmp, null, null, null, null, null, 0, null);
                    break;
                case "type":
                    movie = new Movie(null, tmp, null, null, null, null, 0, null);
                    break;
                case "director":
                    movie = new Movie(null, null, tmp, null, null, null, 0, null);
                    break;
                case "protagonist":
                    movie = new Movie(null, null, null, tmp, null, null, 0, null);
                    break;
                case "labels":
                    movie = new Movie(null, null, null, null, null, null, 0, tmp);
                    break;
                case "isReleased":
                    if (tmp.equals("1")) {
                        movie = new Movie(null, null, null, null, null, null, 0, null, true);
                    } else {
                        movie = new Movie(null, null, null, null, null, null, 0, null, false);
                    }
                    break;
                default:
                    break;
            }
            ArrayList<Movie> rs = mbi0.queryMovieByField(movie, field);
            MyUtil.showInfo(rs, "影片结果");
            if (!MyUtil.isGoOn("是否继续查询(y/n)", "n")) {
                break;
            }
        }
    }

    public void queryMovieByName() {
        queryMovieByFieldM("name");
    }

    @Test
    public void queryMovieByType() {
        queryMovieByFieldM("type");
    }

    private void queryMovieByDirector() {
        queryMovieByFieldM("director");
    }

    private void queryMovieByProtagonist() {
        queryMovieByFieldM("protagonist");
    }

    @Test
    public void queryMovieByScore() {
        while (true) {
            System.out.println("请输入分数下限");
            double min = Print.getPositiveDouble();
            System.out.println("请输入分数上限");
            double max = Print.getPositiveDouble();
            ArrayList<Movie> rs = mbi0.queryMovieByScore(min, max);
            MyUtil.showInfo(rs, "影片结果");
            if (!MyUtil.isGoOn("是否继续查询(y/n)", "n")) {
                break;
            }
        }
    }

    private void queryMovieByLabels() {
        queryMovieByFieldM("labels");
    }

    @Test
    public void updateMovie() {
        while (true) {
            System.out.println("==========影片信息更改==========");
            ArrayList<Movie> rs = mbi0.queryMovieAll();
            MyUtil.showInfo(rs, "影片列表");
            System.out.println("请输入更改影片id");
            int id = Print.getPositiveInt();
            while (true) {
                System.out.println("请输入要更改的字段");
                String field = scanner.next();
                System.out.println("请输入更改后的信息");
                String changeString = null;
                int tmp = -1;
                switch (field) {
                    case "name":
                        changeString = scanner.next();
                        tmp = mbi0.updateName(id, changeString);
                        break;
                    case "type":
                        changeString = scanner.next();
                        tmp = mbi0.updateType(id, changeString);
                        break;
                    case "director":
                        changeString = scanner.next();
                        tmp = mbi0.updateDirector(id, changeString);
                        break;
                    case "protagonist":
                        changeString = scanner.next();
                        tmp = mbi0.updateProtagonist(id, changeString);
                        break;
                    case "duration":
                        changeString = scanner.next();
                        tmp = mbi0.updateDuration(id, changeString);
                        break;
                    case "detail":
                        changeString = scanner.next();
                        tmp = mbi0.updateDetail(id, changeString);
                        break;
                    case "score":
                        tmp = mbi0.updateScore(id, Print.getPositiveDouble());
                        break;
                    case "labels":
                        changeString = scanner.next();
                        tmp = mbi0.updateLabels(id, changeString);
                        break;
                    case "isReleased":
                        System.out.println("true(上架)/false(下架)");
                        tmp = mbi0.updateIsReleased(id, Print.getBoolean());
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
                if (!MyUtil.isGoOn("是否继续修改本条数据(y/n)", "n")) {
                    break;
                }
            }
            if (!MyUtil.isGoOn("是否退出更改界面(y/n)", "y")) break;
        }
    }

    @Test
    public void deleteMovie() {
        while (true) {
            System.out.println("==========删除影片==========");
            ArrayList<Movie> rs = mbi0.queryMovieAll();
            MyUtil.showInfo(rs, "影片列表");
            System.out.println("请输入删除影片id");
            int id = Print.getPositiveInt();
            int tmp = mbi0.deleteMovie(id);
            boolean updateResult = false;
            if (tmp > 0) {
                updateResult = true;
            }
            MyUtil.showIsOk(updateResult, "删除成功", "删除失败");
            if (!MyUtil.isGoOn("是否返回上层界面(y/n)", "y")) break;
        }
    }


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
        while (true) {
            System.out.println("放映厅名称:");
            String name = scanner.next();
            System.out.println("所属影院id");
            int cid = Print.getPositiveInt();
            String capacity = null;
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
            if (!MyUtil.isGoOn("是否继续添加放映厅(y/n)", "n")) {
                break;
            }
        }
    }

    private void queryHall() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========影片查询==========");
            System.out.println("1.按影厅名查询");
            System.out.println("2.按影院id查询");
            System.out.println("3.按容量查询");
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
                default:
                    if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "y")) {
                        flag = 100;
                        break;
                    }
            }
        }
    }

    @Test
    public void queryHallByName() {
        while (true) {
            System.out.println("输入要查询的放映厅名称:");
            String name = scanner.next();
            Hall hall = new Hall(name, 0, null);
            ArrayList<Hall> rs = hbi0.queryHallByName(hall);
            MyUtil.showInfo(rs, "放映厅结果");
            if (!MyUtil.isGoOn("是否继续查询(y/n)", "n")) {
                break;
            }
        }
    }

    @Test
    public void queryHallByCid() {
        while (true) {
            System.out.println("输入要查询对应影院id:");
            int id = Print.getPositiveInt();
            Hall hall = new Hall(null, id, null);
            ArrayList<Hall> rs = hbi0.queryHallByCid(hall);
            MyUtil.showInfo(rs, "放映厅结果");
            if (!MyUtil.isGoOn("是否继续查询(y/n)", "n")) {
                break;
            }
        }
    }

    @Test
    public void queryHallByCapacity() {
        {
            while (true) {
                System.out.println("输入要查询的容量大小(S/M/L):");
                String capacity = scanner.next();
                Hall hall = new Hall(null, 0, capacity);
                ArrayList<Hall> rs = hbi0.queryHallByCapacity(hall);
                MyUtil.showInfo(rs, "放映厅结果");
                if (!MyUtil.isGoOn("是否继续查询(y/n)", "n")) {
                    break;
                }
            }
        }
    }

    @Test
    public void updateHall() {
        while (true) {
            System.out.println("==========放映厅信息更改==========");
            ArrayList<Hall> rs = hbi0.queryHallAll();
            MyUtil.showInfo(rs, "放映厅列表");
            System.out.println("请输入要更改放映厅id");
            int id = Print.getPositiveInt();
            while (true) {
                System.out.println("请输入要更改的字段");
                String field = scanner.next();
                String changeString = null;
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
                if (!MyUtil.isGoOn("是否继续修改本条数据(y/n)", "n")) {
                    break;
                }
            }
            if (!MyUtil.isGoOn("是否返回上层界面(y/n)", "y")) break;

        }
    }

    @Test
    public void deleteHall() {
        while (true) {
            System.out.println("==========删除放映厅==========");
            ArrayList<Hall> rs = hbi0.queryHallAll();
            MyUtil.showInfo(rs, "放映厅列表");
            System.out.println("请输入删除放映厅id");
            int id = Print.getPositiveInt();
            int tmp = hbi0.deleteHall(id);
            boolean updateResult = false;
            if (tmp > 0) {
                updateResult = true;
            }
            MyUtil.showIsOk(updateResult, "删除成功", "删除失败");
            if (!MyUtil.isGoOn("是否返回上层界面(y/n)", "y")) break;
        }
    }

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
        while (true) {
            System.out.println("放映厅id:");
            int hid = Print.getPositiveInt();
            System.out.println("所属电影id:");
            int mid = Print.getPositiveInt();
            System.out.println("放映时间:");
            LocalDateTime time = Print.getLocalDateTime();
            System.out.println("价格");
            int price = Print.getPositiveInt();
            Session session = new Session(hid, mid, time, price);
            MyUtil.showIsOk(sbi0.create(session), "添加成功", "添加失败");
            if (!MyUtil.isGoOn("是否继续添加放映厅(y/n)", "n")) {
                break;
            }
        }
    }

    private void querySession() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========影片查询==========");
            System.out.println("1.按放映厅id查询");
            System.out.println("2.按电影id查询");
            System.out.println("3.按播放时间区间查询");
            System.out.println("4.按价格区间查询");
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
                default:
                    if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "y")) {
                        flag = 100;
                        break;
                    }
            }
        }
    }

    private void querySessionByHid() {
        while (true) {
            System.out.println("输入要查询的放映厅id:");
            int hid = Print.getPositiveInt();
            Session session = new Session(hid, 0, null, 0);
            ArrayList<Session> rs = sbi0.querySessionByHid(session);
            MyUtil.showInfo(rs, "场次结果");
            if (!MyUtil.isGoOn("是否继续查询(y/n)", "n")) {
                break;
            }
        }
    }

    private void querySessionByMid() {
        while (true) {
            System.out.println("输入要查询的电影id:");
            int mid = Print.getPositiveInt();
            Session session = new Session(0, mid, null, 0);
            ArrayList<Session> rs = sbi0.querySessionByHid(session);
            MyUtil.showInfo(rs, "场次结果");
            if (!MyUtil.isGoOn("是否继续查询(y/n)", "n")) {
                break;
            }
        }
    }

    @Test
    public void querySessionByTime() {
        while (true) {
            System.out.println("输入要查询的起始时间:");
            LocalDateTime min = Print.getLocalDateTime();
            System.out.println("输入要查询的截至时间:");
            LocalDateTime max = Print.getLocalDateTime();
            ArrayList<Session> rs = sbi0.querySessionByTime(min, max);
            MyUtil.showInfo(rs, "场次结果");
            if (!MyUtil.isGoOn("是否继续查询(y/n)", "n")) {
                break;
            }
        }
    }

    @Test

    public void querySessionByPrice() {
        while (true) {
            System.out.println("输入要查询的价格下限:");
            Double min = Print.getPositiveDouble();
            System.out.println("输入要查询的价格上限:");
            Double max = Print.getPositiveDouble();
            ArrayList<Session> rs = sbi0.querySessionByPrice(min, max);
            MyUtil.showInfo(rs, "场次结果");
            if (!MyUtil.isGoOn("是否继续查询(y/n)", "n")) {
                break;
            }
        }
    }

    @Test
    public void updateSession() {
        while (true) {
            System.out.println("==========场次信息更改==========");
            ArrayList<Session> rs = sbi0.querySessionAll();
            MyUtil.showInfo(rs, "场次列表");
            System.out.println("请输入要更改场次id");
            int id = Print.getPositiveInt();
            while (true) {
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
                if (!MyUtil.isGoOn("是否继续修改本条数据(y/n)", "n")) {
                    break;
                }
            }
            if (!MyUtil.isGoOn("是否返回上层界面(y/n)", "y")) break;

        }
    }
    
    private void deleteSession() {
        while (true) {
            System.out.println("==========删除场次==========");
            ArrayList<Session> rs = sbi0.querySessionAll();
            MyUtil.showInfo(rs, "场次列表");
            System.out.println("请输入删除场次id");
            int id = Print.getPositiveInt();
            int tmp = sbi0.deleteSession(id);
            boolean updateResult = false;
            if (tmp > 0) {
                updateResult = true;
            }
            MyUtil.showIsOk(updateResult, "删除成功", "删除失败");
            if (!MyUtil.isGoOn("是否返回上层界面(y/n)", "y")) break;
        }
    }
    

    public void userCheck() {

    }

}