package felix.view;

import felix.biz.imp0.*;
import felix.entity.Movie;
import felix.util.MyUtil;
import felix.util.Print;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieManage {
    private final Scanner scanner = new Scanner(System.in);
    private String choice;
    private final MovieBizImp0 mbi0 = new MovieBizImp0();


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


    /**
     * @return int
     * @author Felix
     * @date 2020/7/3 17:57
     * @describe 获取一个存在的电影id
     */
    public int isMovieIdExistAndGet() {
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

    public ArrayList<Movie> queryMovieByUp() {
        Movie m = new Movie(null, null, null, null, null, null, 0, null, true);
        ArrayList<Movie> rs = mbi0.queryMovieByField(m, "isReleased");
        MyUtil.showInfo(rs, "时下热映");
        return rs;
    }

}
