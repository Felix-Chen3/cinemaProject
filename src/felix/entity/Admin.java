/**
 * @author Felix
 * @describe
 * @date 2020/6/2413:37
 */
package felix.entity;

public class Admin {
    private String account;
    private String password;

    public Admin() {
    }

    public Admin(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
