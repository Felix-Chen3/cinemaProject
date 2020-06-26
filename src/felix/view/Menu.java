/**
 * @author Felix
 * @describe 主菜单页面
 * @date 2020/6/24 13:32
 */
package felix.view;

import felix.biz.imp0.AdminBizImp0;
import felix.biz.imp0.CinemaBizImp0;
import felix.biz.imp0.MovieBizImp0;
import felix.entity.Cinema;
import felix.entity.Movie;
import felix.util.MyUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private String choice;
    private AdminBizImp0 abi0 = new AdminBizImp0();
    private CinemaBizImp0 cbi0 = new CinemaBizImp0();
    private MovieBizImp0 mbi0 = new MovieBizImp0();

    @Test
    public void mainMenu() {
        while (true) {
            System.out.println("==========欢迎来到电影综合购票管理系统==========");
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
            System.out.println("3.电影管理");
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

    public void filmManage() {
        while (true) {
            System.out.println("==========影院管理==========");
            System.out.println("1.添加电影");
            System.out.println("2.查询电影");
            System.out.println("3.更改电影信息");
            System.out.println("4.删除电影");
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

    private void createMovie() {
        while (true) {
            System.out.println("电影名称:");
            String name = scanner.next();
            System.out.println("电影类型:");
            String type = scanner.next();
            System.out.println("导演:");
            String director = scanner.next();
            ArrayList protagonists = MyUtil.scanInfo("主演");
            System.out.println("电影时常:");
            String duration = scanner.next();
            System.out.println("电影详情:");
            String detail = scanner.next();
            System.out.println("电影类型:");
            double score = scanner.nextDouble();
            Movie movie = null;
            if (!MyUtil.isGoOn("是否额外添加标签(y/n)", "y")){
                ArrayList labels = MyUtil.scanInfo("标签");
                movie = new Movie(name, type, director, protagonists, duration, detail, score,labels);
            }else {
                movie = new Movie(name, type, director, protagonists, duration, detail, score);
            }
            MyUtil.showIsOk(mbi0.create(movie), "添加成功", "添加失败");
            if (!MyUtil.isGoOn("是否返回上级界面(y/n)", "n")) break;
            System.out.println("yuwenfengsb");
        }
    }

    private void queryMovie() {
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