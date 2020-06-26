/**
 * @author Felix
 * @describe 个人工具类
 * @date 2020/6/24 14:59
 */
package felix.util;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class MyUtil {
    private static Scanner scanner = new Scanner(System.in);
    /**
     * @author Felix
     * @date 2020-06-24 15:22
     * @describe 控制台输出给定语句，并判断控制台时候接收到给定标记字符串
     * @return boolean
     * @throws null
    */
    public static boolean isGoOn(String msg, String choice) {
        System.out.println(msg);
        String c = scanner.next();
        if (c.equals(choice)) {
            return false;
        }
        return true;
    }
    /**
     * @author Felix
     * @date 2020-06-25 11:15
     * @describe 根据boolean值输出给定语句
     * @return
     * @throws
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
     * @return
     * @throws
    */
    public static <T> void showInfo(ArrayList<T> al, String msg) {
        if (al.size() == 0) {
            System.out.println("无相关数据");
        } else {
            System.out.println("================" + msg + "================");
            for (T t : al) {
                System.out.println(t);
            }
            System.out.println("========================================");
        }
    }
    /**
     * @author Felix
     * @date 2020-06-26 14:11
     * @describe  失败的尝试
     * @return
     * @throws
    */
    public static <T> void showField(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            System.out.print(fields[i].getName()+"\t");
        }
    }
    /**
     * @author Felix
     * @date 2020-06-26 14:34
     * @describe 录入单一字段的多条数据
     * @return String[]
     * @throws
    */
    public static String scanInfo(String field) {
        System.out.println(field+"数量:");
        int length = scanner.nextInt();
        if (length < 1) {
            return null;
        }
        ArrayList list = new ArrayList();
        for (int i = 0; i < length; i++) {
            System.out.println(field+(i+1)+":");
            String tmp = scanner.next();
            list.add(tmp);
        }
        String afterChange = StringUtils.join(list, "|");
        return afterChange;
    }
}
