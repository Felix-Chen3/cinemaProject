/**
 * @author Felix
 * @describe
 * @date 2020/7/3 23:40
 */
package felix.entity;

import java.util.HashMap;

public class MyHashMap extends HashMap {

    public Object put(Object key, Double value) {
        Double newOne = value;
        if (containsKey(key)) {
            Double old = (Double) get(key);
            newOne = value + old;
        }
        return super.put(key, newOne);
    }
}
