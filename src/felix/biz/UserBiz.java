package felix.biz;

import felix.entity.User;

public interface UserBiz {
    boolean login(User user);

    boolean register(User user);

    void recharger(int uid,double amount);

    User queryUserById(int uid);

    String checkAndGetUserLevel(int uid);

    void setUserLevel(int uid, String level);

    boolean checkEnoughBalance(int uid, int sid, int num);

    void deductBalance(int uid, int sid);
}
