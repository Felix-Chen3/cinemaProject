/**
 * @author Felix
 * @describe
 * @date 2020/6/28 10:39
 */
package felix.entity;

import java.util.Objects;

public class Hall {
    private int id;
    private String name;
    private int cid;
    private String capacity;



    public Hall(String name, int cid, String capacity) {
        this.name = name;
        this.cid = cid;
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return cid == hall.cid &&
                Objects.equals(name, hall.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cid);
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

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Hall() {
    }

    @Override
    public String toString() {
        return "放映厅{" +
                "id=" + id +
                ", 名称(name)='" + name + '\'' +
                ", 影院id(cid)=" + cid +
                ", 容量(capacity)='" + capacity + '\'' +
                '}';
    }
}
