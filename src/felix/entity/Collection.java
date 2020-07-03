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


public class Collection {
    private int id;
    private int uid;
    private int mid;

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
