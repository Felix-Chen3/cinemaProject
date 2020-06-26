/**
 * @author Felix
 * @describe
 * @date 2020/6/24 14:15
 */
package felix.dao;


import felix.entity.Admin;

public interface AdminDao {
    public Admin queryByAccount(Admin admin);
}
