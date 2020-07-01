/**
 * @author Felix
 * @describe
 * @date 2020/6/30 13:13
 */
package felix.entity;

import java.time.LocalDateTime;

public class Recharger {
    private int id;
    private int uid;
    private double amount;
    private LocalDateTime time_recharger;

    public Recharger() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime_recharger() {
        return time_recharger;
    }

    public void setTime_recharger(LocalDateTime time_recharger) {
        this.time_recharger = time_recharger;
    }

    public Recharger(int uid, double amount, LocalDateTime time_recharger) {
        this.uid = uid;
        this.amount = amount;
        this.time_recharger = time_recharger;
    }
}
