package felix.dao;

import felix.entity.User;

public interface UserDao {

    User queryByName(User user);

    boolean createUser(User user);
}
