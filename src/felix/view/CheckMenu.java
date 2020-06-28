/**
 * @author Felix
 * @describe
 * @date 2020/6/28 10:51
 */
package felix.view;

import felix.biz.imp0.AdminBizImp0;
import felix.util.MyUtil;
import org.junit.Test;

import java.util.Scanner;

public class CheckMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final AdminBizImp0 abi0 = new AdminBizImp0();
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

    public void userCheck() {

    }
}
