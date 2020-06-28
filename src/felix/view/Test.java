/**
 * @author Felix
 * @describe
 * @date 2020/6/28 15:47
 */
package felix.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args)  {
        LocalDateTime d = LocalDateTime.now();
        for (int i = 0; i < 10000000; i++) {
            String a = "s";
        }
        LocalDateTime d1 = LocalDateTime.now();
        LocalDateTime d2 = LocalDateTime.of(2020, 6, 28, 13, 23);
        System.out.println(d2.compareTo(d));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    }
}
