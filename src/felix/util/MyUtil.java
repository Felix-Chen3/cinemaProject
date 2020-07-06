/**
 * @author Felix
 * @describe 个人工具类
 * @date 2020/6/24 14:59
 */
package felix.util;

import felix.dao.Imp0.MovieDaoImp0;
import felix.entity.Cinema;
import felix.entity.Hall;
import felix.entity.Movie;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MyUtil {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final MovieDaoImp0 mdi0 = new MovieDaoImp0();
    /**
     * @author Felix
     * @date 2020-06-24 15:22
     * @describe 控制台输出给定语句，并判断控制台时候接收到给定标记字符串
     * @return boolean
     * @throws null
    */
    public static boolean isGoOn(String msg, String outChoice) {
        System.out.println(msg);
        String c = scanner.next();
        return !c.equals(outChoice);
    }
    /**
     * @author Felix
     * @date 2020-06-25 11:15
     * @describe 根据boolean值输出给定语句
     * @return void
    */
    public static void showIsOk(boolean b, String ok, String no) {
        if (b) {
            System.out.println(ok);
        } else {
            System.out.println(no);
        }
    }
    /**
     * @author Felix
     * @date 2020-06-25 11:20
     * @describe 展示对应list结果
     * @return void
    */
    public static <T> void showInfo(ArrayList<T> al, String msg)  {
        if (al.size() == 0) {
            System.out.println("----------------------------");
            System.out.println("无相关数据");
        } else {
            System.out.println("----------" + msg + "----------");
            for (T t : al) {
                System.out.println(t);
                }
            }
            System.out.println("----------------------------");
        }

    /**
     * @author Felix
     * @date 2020-06-26 14:11
     * @describe  失败的尝试
     * @return void
    */
    public static <T> void showField(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.print(field.getName() + "\t");
        }
    }
    /**
     * @author Felix
     * @date 2020-06-26 14:34
     * @describe 录入单一字段的多条数据
     * @return String
    */
    public static String scanInfo(String field) {
        System.out.println(field+"数量:");
        int length = Print.getPositiveInt();
        ArrayList list = new ArrayList();
        for (int i = 0; i < length; i++) {
            System.out.println(field+(i+1)+":");
            String tmp = scanner.next();
            list.add(tmp);
        }
        String afterChange = StringUtils.join(list, "|");
        return afterChange;
    }
    /**
     * @author Felix
     * @date 2020-06-27 00:30
     * @describe 刷新缓存区
     * @return void
    */
    public static void flush() {
        scanner.nextLine();
    }

    /**
     * @author Felix
     * @date 2020-07-02 10:21
     * @describe 获取验证码并返回是否输入正确的结果
     */
    public static boolean getCaptcha() {
        while (true) {
            System.out.println("请输入验证码");
            String test = "";
            String[] strings = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
            for (int i = 0; i < 4; i++) {
                test = test + strings[random.nextInt(26)];
            }
            System.out.println(test);
            String userText = scanner.next();
            if (!userText.equals(test)) {
                System.out.println("验证码有误");
            } else {
                return true;
            }
        }
    }
}
