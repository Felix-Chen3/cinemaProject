/**
 * @author Felix
 * @describe
 * @date 2020/6/29 9:57
 */
package felix.entity;


import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String password;
    private double balance;
    private double totalAmount;
    private String level;

    public User() {
    }

    public User(String name, String password, double balance, double totalAmount, String level) {
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.totalAmount = totalAmount;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
