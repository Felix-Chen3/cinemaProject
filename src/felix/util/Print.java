/**
 * @author Felix
 * @describe
 * @date 2020/6/26 22:54
 */
package felix.util;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Print {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * @return
     * @throws
     * @author Felix
     * @date 2020-06-27 00:17
     * @describe 重复获取字符直到得到一个正整数
     */
    public static int getPositiveInt() {
        int rs;
        while (true) {
            try {
                rs = scanner.nextInt();
                if (rs < 1) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("输入的数必须为正整数,请再次输入");
                scanner.nextLine();
                continue;
            }
            return rs;
        }
    }

    /**
     * @return
     * @throws
     * @author Felix
     * @date 2020-06-27 00:32
     * @describe 重复获取字符直到得到一个正数
     */
    public static Double getPositiveDouble() {
        double rs;
        while (true) {
            try {
                rs = scanner.nextDouble();
                if (rs < 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("输入的数必须为正数,请再次输入");
                scanner.nextLine();
                continue;
            }
            return rs;
        }
    }

    /**
     * @return
     * @throws
     * @author Felix
     * @date 2020-06-28 16:23
     * @describe 重复获取字符直到得到一个布尔值
     */
    public static boolean getBoolean() {
        String rs;
        while (true) {
            rs = scanner.next();
            if (rs.equals("true")) {
                return true;
            }
            if (rs.equals("false")) {
                return false;
            }
            System.out.println("输入有误");
        }
    }

    public static LocalDateTime getLocalDateTime() {
        System.out.println("年");
        int year = getPositiveInt();
        System.out.println("月");
        int month = getPositiveInt();
        System.out.println("日");
        int dayOfMonth = getPositiveInt();
        System.out.println("小时");
        int hour = getPositiveInt();
        System.out.println("分钟");
        int minute = getPositiveInt();
        System.out.println("秒");
        int second = getInt();
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
    }
    /**
     * @author Felix
     * @date 2020-06-28 16:55
     * @describe 得到一个非负整数
     * @return
     * @throws
    */
    public static int getInt() {
        int rs;
        while (true) {
            try {
                rs = scanner.nextInt();
                if (rs < 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("输入的数不能为复数,请再次输入");
                scanner.nextLine();
                continue;
            }
            return rs;
        }
    }

}
