package felix.view;

import felix.biz.imp0.*;
import felix.entity.Collection;
import felix.entity.Movie;
import felix.util.MyUtil;
import java.util.ArrayList;
import java.util.Scanner;

public class MyCollection {
    private final Scanner scanner = new Scanner(System.in);
    private final MovieBizImp0 mbi0 = new MovieBizImp0();
    private final CollectionBizImp0 collectionBizImp0 = new CollectionBizImp0();
    private final MovieManage mm = new MovieManage();


    void myCollection(int uid) {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========欢迎进入收藏界面==========");
            System.out.println("1.浏览收藏");
            System.out.println("2.添加收藏");
            System.out.println("0.返回上一级");
            String choice = scanner.next();
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
            int mid = mm.isMovieIdExistAndGet();
            Collection collection = new Collection(uid, mid);
            MyUtil.showIsOk(collectionBizImp0.create(collection), "添加成功", "添加失败,该片已被收藏");
        } while (MyUtil.isGoOn("是否继续添加收藏(y/n)", "n"));
    }


}
