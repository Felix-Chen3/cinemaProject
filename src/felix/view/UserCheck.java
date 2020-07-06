package felix.view;

import felix.biz.imp0.*;
import felix.dao.Imp0.*;
import felix.entity.User;
import felix.util.MyUtil;
import org.junit.Test;
import java.util.Scanner;

public class UserCheck {
    private final Scanner scanner = new Scanner(System.in);
    private final UserDaoImp0 udi0 = new UserDaoImp0();
    private final UserBizImp0 ubi0 = new UserBizImp0();
    private final static Menu m = new Menu();


    public void userCheck() {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========用户账号==========");
            System.out.println("1.注册");
            System.out.println("2.登录");
            System.out.println("0.返回上一级");
            String choice = scanner.next();
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
                m.UserMenu(uid);
                break;
            } else {
                System.out.println("登录失败，请确认账号密码是否正确");
            }
            if (!MyUtil.isGoOn("是否继续登录(y/n)", "n")) {
                break;
            }
        }
    }
}
