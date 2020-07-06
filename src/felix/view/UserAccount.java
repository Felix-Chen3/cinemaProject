package felix.view;

import felix.biz.imp0.*;
import felix.entity.Recharger;
import felix.entity.User;
import felix.util.MyUtil;
import felix.util.Print;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class UserAccount {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final UserBizImp0 ubi0 = new UserBizImp0();
    private final RechargerBizImp0 rbi0 = new RechargerBizImp0();


    void userAccount(int uid) {
        int flag = 0;
        while (flag < 100) {
            System.out.println("==========我的账号==========");
            System.out.println("1.查看余额");
            System.out.println("2.查看vip等级");
            System.out.println("3.充值");
            System.out.println("4.修改密码");
            System.out.println("0.返回上一级");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    queryBalance(uid);
                    break;
                case "2":
                    queryVip(uid);
                    break;
                case "3":
                    recharge(uid);
                    break;
                case "4":
                    changePassword(uid);
                    break;
                default:
                    if (!MyUtil.isGoOn("是否返回上一级(y/n)", "y")) {
                        flag = 100;
                        break;
                    }
            }
        }
    }

    private void queryBalance(int uid) {
        User user = ubi0.queryUserById(uid);
        user.showBalance();
    }

    private void queryVip(int uid) {
        User user = ubi0.queryUserById(uid);
        String discount = "";
        if (user.getLevel() != null) {
            switch (user.getLevel()) {
                case "Vip1":
                    discount = "9折";
                    break;
                case "Vip2":
                    discount = "8折";
                    break;
                case "Vip3":
                    discount = "7折";
                    break;
                case "sVip":
                    discount = "5折";
                    break;
                default:
                    break;
            }
            System.out.println("您的Vip等级为" + user.getLevel() + "\t享受的折扣优惠为" + discount);
        } else {
            System.out.println("您暂时无Vip等级,请充值后再试");
        }
    }

    /**
     * @return void
     * @author Felix
     * @date 2020/7/3 18:04
     * @describe 充值流程：需要输入密码、验证码
     */
    void recharge(int uid) {
        do {
            System.out.println("输入您要充值的金额");
            double amount = Print.getPositiveInt();
            System.out.println("请输入密码");
            String thisPassword = scanner.next();
            String password = ubi0.queryUserById(uid).getPassword();
            if (!password.equals(thisPassword)) {
                System.out.println("密码错误");
                return;
            }
            int flag = 0;
            while (flag < 100) {
                System.out.println("请输入验证码");
                StringBuilder test = new StringBuilder();
                //生成26个字母的字符串组
                String[] strings = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
                //随机抽取4个
                for (int i = 0; i < 4; i++) {
                    test.append(strings[random.nextInt(26)]);
                }
                System.out.println(test);
                String userText = scanner.next();
                if (!userText.equals(test.toString())) {
                    System.out.println("验证码有误");
                } else {
                    flag = 100;
                }
            }
            Recharger recharger = new Recharger(uid, amount, LocalDateTime.now());
            MyUtil.showIsOk(rbi0.create(recharger), "充值成功", "充值失败");
            ubi0.recharger(uid, amount);
        } while (MyUtil.isGoOn("是否继续充值(y/n)", "n"));
    }

    /**
     * @return void
     * @author Felix
     * @date 2020/7/3 17:49
     * @describe 修改密码：修改后密码相同不能修改
     */
    private void changePassword(int uid) {
        System.out.println("请输入当前密码:");
        String thisPassword = scanner.next();
        User user = ubi0.queryUserById(uid);
        String password = user.getPassword();
        if (!password.equals(thisPassword)) {
            System.out.println("输入错误");
            return;
        }
        String newPassword;
        while (true) {
            System.out.println("请输入您的新密码:");
            newPassword = scanner.next();
            if (thisPassword.equals(newPassword)) {
                System.out.println("新旧密码不能相同");
            } else {
                break;
            }
        }
        while (true) {
            System.out.println("再次确认您的新密码:");
            String doubleNewPassword = scanner.next();
            if (!newPassword.equals(doubleNewPassword)) {
                System.out.println("输入错误，请重新输入");
            } else {
                if (MyUtil.getCaptcha()) {
                    ubi0.updatePassword(uid, doubleNewPassword);
                    System.out.println("修改成功！");
                    return;
                }
            }
        }
    }

}
