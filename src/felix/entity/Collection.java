/**
 * @author Felix
 * @describe
 * @date 2020/7/2 10:31
 */
package felix.entity;
/*
Collection	收藏	收藏ID	用户ID	电影ID
        id	uid	mid
*/


import java.util.Objects;

public class Collection {
    private int id;
    private int uid;
    private int mid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return uid == that.uid &&
                mid == that.mid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, mid);
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

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public Collection() {
    }

    public Collection(int uid, int mid) {
        this.uid = uid;
        this.mid = mid;
    }
}
