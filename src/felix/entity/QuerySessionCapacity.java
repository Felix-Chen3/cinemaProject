/**
 * @author Felix
 * @describe
 * @date 2020/6/30 10:13
 */
package felix.entity;

public class QuerySessionCapacity {
    private int id;
    private String capacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public QuerySessionCapacity() {
    }

    public QuerySessionCapacity(int id, String capacity) {
        this.id = id;
        this.capacity = capacity;
    }
}
