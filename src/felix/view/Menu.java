package felix.view;

import felix.biz.imp0.*;
import felix.dao.Imp0.*;
import felix.entity.*;
import felix.entity.Collection;
import felix.util.MyUtil;
import felix.util.Print;
import org.junit.Test;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private String choice;
    private final Random random = new Random();
    private final UserDaoImp0 udi0 = new UserDaoImp0();
    private final MovieDaoImp0 mdi0 = new MovieDaoImp0();
    private final AdminBizImp0 abi0 = new AdminBizImp0();
    private final CinemaBizImp0 cbi0 = new CinemaBizImp0();
    private final MovieBizImp0 mbi0 = new MovieBizImp0();
    private final HallBizImp0 hbi0 = new HallBizImp0();
    private final SessionBizImp0 sbi0 = new SessionBizImp0();
    private final UserBizImp0 ubi0 = new UserBizImp0();
    private final TicketBizImp0 tbi0 = new TicketBizImp0();
    private final TicketDaoImp0 tdi0 = new TicketDaoImp0();
    private final QuerySessionDaoImp0 dsdi0 = new QuerySessionDaoImp0();
    private final QuerySessionCapacityDaoImp0 qscdi0 = new QuerySessionCapacityDaoImp0();
    private final RechargerBizImp0 rbi0 = new RechargerBizImp0();
    private final CollectionBizImp0 collectionBizImp0 = new CollectionBizImp0();
    private final DecimalFormat df = new DecimalFormat("0.00");

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
            System.out.println("5.查看收益");
            System.out.println("0.退出登录");
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
                case "5":
                    earningView();
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
                    if (SessionCanNotDelete(sr.getId())) {
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

    @Test
    public void movieManage() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========影片管理==========");
            System.out.println("1.添加影片");
            System.out.println("2.查询影片");
            System.out.println("3.更改影片信息");
            System.out.println("4.下架影片");
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
        do {
            System.out.println("影片名称:");
            String name = scanner.next();
            String type = MyUtil.scanInfo("类型");
            System.out.println("导演:");
            String director = scanner.next();
            String protagonists = MyUtil.scanInfo("主演");
            System.out.println("影片时长:");
            String duration = scanner.next();
            System.out.println("影片详情:");
            String detail = scanner.next();
            System.out.println("影片评分:");
            double score = Print.getPositiveDouble();
            Movie movie;
            if (!MyUtil.isGoOn("是否额外添加标签(y/n)", "y")) {
                String labels = MyUtil.scanInfo("标签");
                movie = new Movie(name, type, director, protagonists, duration, detail, score, labels);
            } else {
                movie = new Movie(name, type, director, protagonists, duration, detail, score);
            }
            MyUtil.showIsOk(mbi0.create(movie), "添加成功", "添加失败");
        } while (MyUtil.isGoOn("是否继续添加影片(y/n)", "n"));
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
        do {
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
        } while (MyUtil.isGoOn("是否继续查询(y/n)", "n"));
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
        do {
            System.out.println("请输入分数下限");
            double min = Print.getPositiveDouble();
            System.out.println("请输入分数上限");
            double max = Print.getPositiveDouble();
            ArrayList<Movie> rs = mbi0.queryMovieByScore(min, max);
            MyUtil.showInfo(rs, "影片结果");
        } while (MyUtil.isGoOn("是否继续查询(y/n)", "n"));
    }

    private void queryMovieByLabels() {
        queryMovieByFieldM("labels");
    }

    @Test
    public void updateMovie() {
        do {
            System.out.println("==========影片信息更改==========");
            ArrayList<Movie> rs = mbi0.queryMovieAll();
            MyUtil.showInfo(rs, "影片列表");
            System.out.println("请输入更改影片id");
            int id = Print.getPositiveInt();
            do {
                System.out.println("请输入要更改的字段");
                String field = scanner.next();
                System.out.println("请输入更改后的信息");
                String changeString;
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
            } while (MyUtil.isGoOn("是否继续修改本条数据(y/n)", "n"));
        } while (MyUtil.isGoOn("是否退出更改界面(y/n)", "y"));
    }

    @Test
    public void deleteMovie() {
        do {
            System.out.println("==========下架影片==========");
            int id = isMovieIdExistAndGet();
            int tmp = mbi0.deleteMovie(id);
            boolean updateResult = false;
            if (tmp > 0) {
                updateResult = true;
            }
            MyUtil.showIsOk(updateResult, "下架成功", "下架失败");
        } while (MyUtil.isGoOn("是否返回上层界面(y/n)", "y"));
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
        do {
            System.out.println("放映厅名称:");
            String name = scanner.next();
            MyUtil.showInfo(cbi0.queryCinemaAll(), "影院列表");
            int cid = isCinemaIdExistAndGet();
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
                if (SessionCanNotDelete(hr.getId())) {
                    System.out.println("删除放映厅存在场次有票已被售出且未放映，该放映厅不能删除");
                    return;
                }
            }
            hbi0.deleteHall(hid);
            System.out.println("删除成功");
        } while (MyUtil.isGoOn("是否返回上层界面(y/n)", "y"));
    }

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
            int hid = isHallIdExistAndGet();
            int mid = isMovieIdExistAndGet();
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
    private boolean SessionCanNotDelete(int sid) {
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

    @Test
    public void earningView() {
        do {
            System.out.println("==========查看收益==========");
            System.out.println("1.根据电影票房查看");
            System.out.println("2.根据影院地址查看");
            System.out.println("0.返回上一级");
            choice = scanner.next();
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
        List<Map.Entry<Integer, Double>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        System.out.println("==========票房排行榜==========");
        for (Map.Entry<Integer, Double> e : list) {
            String movieName = mbi0.queryMovieById(e.getKey()).getName();
            System.out.println("电影id:" + e.getKey() + "\t电影名称:" + movieName + "\t电影票房" + df.format(e.getValue()));
        }
        System.out.println("==============================");
    }

    public void userCheck() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========用户账号==========");
            System.out.println("1.注册");
            System.out.println("2.登录");
            System.out.println("0.返回上一级");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    createUser();
                    break;
                case "2":
                    userLogin();
                    break;
                default: {
                    flag = 100;
                    break;
                }
            }
        }
    }

    @Test
    public void createUser() {
        do {
            System.out.println("用户名:");
            String name = scanner.next();
            System.out.println("密码");
            String password = scanner.next();
            User user = new User(name, password, 0, 0, null);
            MyUtil.showIsOk(ubi0.register(user), "添加成功", "添加失败,该用户名已存在");
        } while (MyUtil.isGoOn("是否继续注册(y/n)", "n"));

    }

    @Test
    public void userLogin() {
        while (true) {
            System.out.println("==========身份校验==========");
            System.out.println("输入账号:");
            String name = scanner.next();
            System.out.println("输入密码:");
            String password = scanner.next();
            User user = new User(name, password, 0, 0, null);
            if (ubi0.login(user)) {
                System.out.println("登录成功");
                int uid = udi0.queryByName(user).getId();
                UserMenu(uid);
                break;
            } else {
                System.out.println("登录失败，请确认账号密码是否正确");
            }
            if (!MyUtil.isGoOn("是否继续登录(y/n)", "n")) {
                break;
            }
        }
    }

    private void UserMenu(int uid) {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========欢迎进入用户界面==========");
            System.out.println("1.浏览电影");
            System.out.println("2.我的收藏");
            System.out.println("3.购票");
            System.out.println("4.我的影票");
            System.out.println("5.我的账号");
            System.out.println("6.热映推荐");
            System.out.println("0.退出登录");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    queryMovie();
                    break;
                case "2":
                    myCollection(uid);
                    break;
                case "3":
                    ticketPurchaseByMovie(uid);
                    break;
                case "4":
                    checkTicket(uid);
                    break;
                case "5":
                    userAccount(uid);
                    break;
                case "6":
                    recommend(uid);
                    break;
                default:
                    if (!MyUtil.isGoOn("是否退出登录(y/n)", "y")) {
                        flag = 100;
                        break;
                    }
            }
        }
    }

    private void myCollection(int uid) {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========欢迎进入收藏界面==========");
            System.out.println("1.浏览收藏");
            System.out.println("2.添加收藏");
            System.out.println("0.返回上一级");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    queryCollection(uid);
                    break;
                case "2":
                    createCollection(uid);
                    break;
                default:
                    if (!MyUtil.isGoOn("是否返回上一级(y/n)", "y")) {
                        flag = 100;
                        break;
                    }
            }
        }
    }

    private void queryCollection(int uid) {
        do {
            ArrayList<Collection> list = collectionBizImp0.queryCollectionByUid(uid);
            ArrayList<Movie> show = new ArrayList<>();
            for (Collection collection : list) {
                int mid = collection.getMid();
                show.add(queryMovieById(mid));
            }
            MyUtil.showInfo(show, "我的收藏");
        } while (MyUtil.isGoOn("是否返回上一级(y/n)", "y"));
    }

    private Movie queryMovieById(int mid) {
        return mbi0.queryMovieById(mid);
    }

    private void createCollection(int uid) {
        do {
            System.out.println("想要收藏的电影id");
            int mid = isMovieIdExistAndGet();
            Collection collection = new Collection(uid, mid);
            MyUtil.showIsOk(collectionBizImp0.create(collection), "添加成功", "添加失败,该片已被收藏");
        } while (MyUtil.isGoOn("是否继续添加收藏(y/n)", "n"));
    }


    private void userAccount(int uid) {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========我的账号==========");
            System.out.println("1.查看余额");
            System.out.println("2.查看vip等级");
            System.out.println("3.充值");
            System.out.println("4.修改密码");
            System.out.println("0.返回上一级");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    queryBalance(uid);
                    break;
                case "2":
                    queryVip(uid);
                    break;
                case "3":
                    recharge(uid);
                    break;
                case "4":
                    changePassword(uid);
                    break;
                default:
                    if (!MyUtil.isGoOn("是否返回上一级(y/n)", "y")) {
                        flag = 100;
                        break;
                    }
            }
        }
    }

    private void queryBalance(int uid) {
        User user = ubi0.queryUserById(uid);
        user.showBalance();
    }

    private void queryVip(int uid) {
        User user = ubi0.queryUserById(uid);
        String discount = "";
        if (user.getLevel() != null) {
            switch (user.getLevel()) {
                case "Vip1":
                    discount = "9折";
                    break;
                case "Vip2":
                    discount = "8折";
                    break;
                case "Vip3":
                    discount = "7折";
                    break;
                case "sVip":
                    discount = "5折";
                    break;
                default:
                    break;
            }
            System.out.println("您的Vip等级为" + user.getLevel() + "\t享受的折扣优惠为" + discount);
        } else {
            System.out.println("您暂时无Vip等级,请充值后再试");
        }
    }

    /**
     * @return void
     * @author Felix
     * @date 2020/7/3 17:49
     * @describe 修改密码：修改后密码相同不能修改
     */
    private void changePassword(int uid) {
        System.out.println("请输入当前密码:");
        String thisPassword = scanner.next();
        User user = ubi0.queryUserById(uid);
        String password = user.getPassword();
        if (!password.equals(thisPassword)) {
            System.out.println("输入错误");
            return;
        }
        String newPassword;
        while (true) {
            System.out.println("请输入您的新密码:");
            newPassword = scanner.next();
            if (thisPassword.equals(newPassword)) {
                System.out.println("新旧密码不能相同");
            } else {
                break;
            }
        }
        while (true) {
            System.out.println("再次确认您的新密码:");
            String doubleNewPassword = scanner.next();
            if (!newPassword.equals(doubleNewPassword)) {
                System.out.println("输入错误，请重新输入");
            } else {
                if (MyUtil.getCaptcha()) {
                    ubi0.updatePassword(uid, doubleNewPassword);
                    System.out.println("修改成功！");
                    return;
                }
            }
        }
    }


    public void ticketPurchaseByMovie(int uid) {
        do {

            int mid = isMovieIdExistAndGet();
            int sid = isSessionIdExistAndGet(mid);
            if (sid == 0) {
                continue;
            }
            System.out.println("输入想要购买的数量");
            int num = Print.getPositiveInt();
            while (!ubi0.checkEnoughBalance(uid, sid, num)) {
                System.out.println("余额不足,请充值后再试");
                recharge(uid);
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
     * @return int
     * @author Felix
     * @date 2020/7/3 17:55
     * @describe 获取一个存在的放映厅id
     */
    private int isHallIdExistAndGet() {
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
    private int isSessionIdExistAndGet(int mid) {
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

    /**
     * @return int
     * @author Felix
     * @date 2020/7/3 17:57
     * @describe 获取一个存在的电影id
     */
    private int isMovieIdExistAndGet() {
        int mid;
        while (true) {
            ArrayList<Movie> movieList = queryMovieByUp();
            System.out.println("选择电影id");
            mid = Print.getPositiveInt();
            ArrayList<Integer> midList = new ArrayList<>();
            for (Movie movie : movieList) {
                midList.add(movie.getId());
            }
            if (!midList.contains(mid)) {
                System.out.println("无此电影id，请重新输入");
            } else {
                break;
            }
        }
        return mid;
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


    public ArrayList<Movie> queryMovieByUp() {
        Movie m = new Movie(null, null, null, null, null, null, 0, null, true);
        ArrayList<Movie> rs = mbi0.queryMovieByField(m, "isReleased");
        MyUtil.showInfo(rs, "时下热映");
        return rs;
    }

    /**
     * @return void
     * @author Felix
     * @date 2020/7/3 18:04
     * @describe 充值流程：需要输入密码、验证码
     */
    private void recharge(int uid) {
        do {
            System.out.println("输入您要充值的金额");
            double amount = Print.getPositiveInt();
            System.out.println("请输入密码");
            String thisPassword = scanner.next();
            String password = ubi0.queryUserById(uid).getPassword();
            if (!password.equals(thisPassword)) {
                System.out.println("密码错误");
                return;
            }
            int flag = 0;
            while (flag < 100) {
                System.out.println("请输入验证码");
                StringBuilder test = new StringBuilder();
                //生成26个字母的字符串组
                String[] strings = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
                //随机抽取4个
                for (int i = 0; i < 4; i++) {
                    test.append(strings[random.nextInt(26)]);
                }
                System.out.println(test);
                String userText = scanner.next();
                if (!userText.equals(test.toString())) {
                    System.out.println("验证码有误");
                } else {
                    flag = 100;
                }
            }
            Recharger recharger = new Recharger(uid, amount, LocalDateTime.now());
            MyUtil.showIsOk(rbi0.create(recharger), "充值成功", "充值失败");
            ubi0.recharger(uid, amount);
        } while (MyUtil.isGoOn("是否继续充值(y/n)", "n"));
    }

    /**
     * @return void
     * @author Felix
     * @date 2020/7/3 18:05
     * @describe 向用户推荐电影
     */
    private void recommend(int uid) {
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