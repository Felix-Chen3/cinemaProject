/**
 * @author Felix
 * @describe 主菜单页面
 * @date 2020/6/24 13:32
 */
package felix.view;

import com.google.gson.Gson;
import felix.biz.imp0.AdminBizImp0;
import felix.biz.imp0.CinemaBizImp0;
import felix.biz.imp0.MovieBizImp0;
import felix.entity.Cinema;
import felix.entity.Movie;
import felix.util.MyUtil;
import felix.util.Print;
import org.junit.Test;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Gson gson = new Gson();
    private String choice;
    private AdminBizImp0 abi0 = new AdminBizImp0();
    private CinemaBizImp0 cbi0 = new CinemaBizImp0();
    private MovieBizImp0 mbi0 = new MovieBizImp0();

    @Test
    public void mainMenu() {
        while (true) {
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
                    break;
            }
            if (!MyUtil.isGoOn("是否退出本系统(y/n)", "y")) break;
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
            if (!MyUtil.isGoOn("是否继续登录(y/n)", "n")) break;
        }
    }

    @Test
    public void adminMenu() {
        while (true) {
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
                    filmManage();
                    break;
                case "4":
                    sessionManage();
                    break;
                default:
                    break;
            }
            if (!MyUtil.isGoOn("是否退出登录(y/n)", "y")) break;
        }
    }

    public void cinemaManege() {
        while (true) {
            System.out.println("==========影院管理==========");
            System.out.println("1.添加影院");
            System.out.println("2.查询影院");
            System.out.println("3.更改影院信息");
            System.out.println("4.删除影院");
            System.out.println("5.返回上级界面");
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
                    break;
            }
            if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "y")) break;
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
            if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "n")) break;
        }
    }

    @Test
    public void queryCinema() {
        while (true) {
            System.out.println("==========影院查询==========");
            System.out.println("1.影院名称查询(含模糊查询)");
            System.out.println("2.按地址位置查询");
            System.out.println("3.返回上级界面");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    queryCinemaByName();
                    break;
                case "2":
                    queryCinemaByAddress();
                    break;
                default:
                    break;
            }
            if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "y")) break;
        }
    }

    @Test
    public void queryCinemaByName() {
        while (true) {
            System.out.println("输入要查询的影院名称:");
            String name = scanner.next();
            Cinema cinema = new Cinema(name, null);
            ArrayList<Cinema> rs = cbi0.queryCinemaByName(cinema);
            MyUtil.showInfo(rs, "影院结果");
            if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "y")) break;
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
            if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "y")) break;
        }
    }

    public void updateCinema() {

    }

    public void deleteCinema() {

    }

    @Test
    public void filmManage() {
        while (true) {
            System.out.println("==========影片管理==========");
            System.out.println("1.上架影片");
            System.out.println("2.查询影片");
            System.out.println("3.更改影片信息");
            System.out.println("4.下架影片");
            System.out.println("5.返回上级界面");
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
                    break;
            }
            if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "y")) break;
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
            type = scanner.next();
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
            if (!MyUtil.isGoOn("是否继续添加影片(y/n)", "n")) break;
        }
    }

    @Test
    public void queryMovie() {
        while (true) {
            System.out.println("==========影片查询==========");
            System.out.println("1.按片名查询");
            System.out.println("2.按类型查询");
            System.out.println("3.按导演查询");
            System.out.println("4.按主演查询");
            System.out.println("5.按评分查询");
            System.out.println("6.按标签查询");
            System.out.println("7.返回上级界面");
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
                default:
                    break;
            }
            if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "y")) break;
        }
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
                default:
                    break;
            }
            ArrayList<Movie> rs = mbi0.queryMovieByField(movie, field);
            MyUtil.showInfo(rs, "影片结果");
            if (!MyUtil.isGoOn("是否继续查询(y/n)", "n")) break;
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

    private void queryMovieByScore() {
    }

    private void queryMovieByLabels() {
        queryMovieByFieldM("labels");
    }

    private void updateMovie() {
    }

    private void deleteMovie() {
    }


    public void hallManage() {

    }

    public void sessionManage() {

    }

    public void userCheck() {

    }
}