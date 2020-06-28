/**
 * @author Felix
 * @describe
 * @date 2020/6/26 22:54
 */
package felix.util;

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


}
