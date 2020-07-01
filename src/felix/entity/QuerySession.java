/**
 * @author Felix
 * @describe
 * @date 2020/6/29 18:09
 */
package felix.entity;

import java.time.LocalDateTime;

public class QuerySession {
    private int id;
    private String movie_name;
    private String cinema_name;
    private String cinema_address;
    private String hall_name;
    private LocalDateTime session_time;
    private Double session_price;

    @Override
    public String toString() {
        return "QuerySession{" +
                "id=" + id +
                ", movie_name='" + movie_name + '\'' +
                ", cinema_name='" + cinema_name + '\'' +
                ", cinema_address='" + cinema_address + '\'' +
                ", hall_name='" + hall_name + '\'' +
                ", session_time=" + session_time +
                ", session_price=" + session_price +
                '}';
    }

    public QuerySession() {
    }

    public QuerySession(int id, String movie_name, String cinema_name, String cinema_address, String hall_name, LocalDateTime session_time, Double session_price) {
        this.id = id;
        this.movie_name = movie_name;
        this.cinema_name = cinema_name;
        this.cinema_address = cinema_address;
        this.hall_name = hall_name;
        this.session_time = session_time;
        this.session_price = session_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getCinema_name() {
        return cinema_name;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }

    public String getCinema_address() {
        return cinema_address;
    }

    public void setCinema_address(String cinema_address) {
        this.cinema_address = cinema_address;
    }

    public String getHall_name() {
        return hall_name;
    }

    public void setHall_name(String hall_name) {
        this.hall_name = hall_name;
    }

    public LocalDateTime getSession_time() {
        return session_time;
    }

    public void setSession_time(LocalDateTime session_time) {
        this.session_time = session_time;
    }

    public Double getSession_price() {
        return session_price;
    }

    public void setSession_price(Double session_price) {
        this.session_price = session_price;
    }
}
