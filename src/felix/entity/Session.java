/**
 * @author Felix
 * @describe
 * @date 2020/6/28 14:13
 */
package felix.entity;

import java.time.LocalDateTime;

public class Session {
    private int id;
    private int hid;
    private int mid;
    private LocalDateTime time ;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Session() {
    }

    public Session(int hid, int mid, LocalDateTime time, double price) {
        this.hid = hid;
        this.mid = mid;
        this.time = time;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", hid=" + hid +
                ", mid=" + mid +
                ", time=" + time +
                ", price=" + price +
                '}';
    }
}
