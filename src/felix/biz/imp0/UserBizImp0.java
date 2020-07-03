/**
 * @author Felix
 * @describe
 * @date 2020/6/29 10:39
 */
package felix.biz.imp0;

import felix.biz.UserBiz;
import felix.dao.Imp0.MovieDaoImp0;
import felix.dao.Imp0.SessionDaoImp0;
import felix.dao.Imp0.UserDaoImp0;
import felix.entity.Session;
import felix.entity.User;

public class UserBizImp0 implements UserBiz {
    private UserDaoImp0 udi0 = new UserDaoImp0();
    private SessionDaoImp0 sdi0 = new SessionDaoImp0();
    private MovieDaoImp0 mdi0 = new MovieDaoImp0();

    @Override
    public boolean login(User user) {
        User rs = udi0.queryByName(user);
        if (rs == null) {
            return false;
        } else {
            if (rs.getPassword().equals(user.getPassword())) {
                return true;
            } else return false;
        }
    }


    @Override
    public boolean register(User user) {
        User rs = udi0.queryByName(user);
        if (rs == null) {
            return udi0.createUser(user);
        }
        return false;
    }

    @Override
    public void recharger(int uid, double amount) {
        User thisUser = queryUserById(uid);
        udi0.update("update user set balance = ?,totalAmount = ? where id = ?", thisUser.getBalance() + amount, thisUser.getTotalAmount() + amount, uid);
        checkAndGetUserLevel(uid);
    }

    @Override
    public User queryUserById(int uid) {
        return udi0.queryOne(User.class, "select id,name,password,level,balance,totalAmount,level from user where id = ?", uid);
    }

    @Override
    public String checkAndGetUserLevel(int uid) {
        String level = null;
        User thisUser = queryUserById(uid);
        double totalAmount = thisUser.getTotalAmount();
        if (totalAmount >= 200 && totalAmount < 2000) {
            level = "Vip1";
        }
        if (totalAmount >= 2000 && totalAmount < 5000) {
            level = "Vip2";
        }
        if (totalAmount >= 5000 && totalAmount < 10000) {
            level = "Vip3";
        }
        if (totalAmount >= 10000) {
            level = "sVip";
        }
        setUserLevel(uid, level);
        return level;
    }

    @Override
    public void setUserLevel(int uid, String level) {
        udi0.update("update user set level = ? where id = ?", level, uid);
    }

    @Override
    public boolean checkEnoughBalance(int uid, int sid, int num) {
        User thisUser = queryUserById(uid);
        double balance = thisUser.getBalance();
        String level = thisUser.getLevel();
        double discount = 1;
        switch (level) {
            case "Vip1":
                discount = 0.9;
                break;
            case "Vip2":
                discount = 0.8;
                break;
            case "Vip3":
                discount = 0.7;
                break;
            case "sVip":
                discount = 0.5;
                break;
        }
        Session session = sdi0.queryOne(Session.class, "select price from Session where id = ?", sid);
        double price = session.getPrice();
        double totalCost = price * discount * num;
        return balance >= totalCost;
    }

    @Override
    public void deductBalance(int uid, int sid) {
        User thisUser = queryUserById(uid);
        double balance = thisUser.getBalance();
        String level = thisUser.getLevel();
        double discount = 1;
        switch (level) {
            case "Vip1":
                discount = 0.9;
                break;
            case "Vip2":
                discount = 0.8;
                break;
            case "Vip3":
                discount = 0.7;
                break;
            case "sVip":
                discount = 0.5;
                break;
        }
        Session session = sdi0.queryOne(Session.class, "select price from Session where id = ?", sid);
        double price = session.getPrice();
        double cost = price*discount;
        sdi0.update("update user set balance = ? where id = ?", balance - cost,uid);
    }

    @Override
    public void updatePassword(int uid,String password) {
        udi0.update("update user set password = ? where id = ?", password, uid);
    }
}


