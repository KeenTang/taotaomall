package taotao.common.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-07
 * Time: 17:37
 */
public class IDUtils {
    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static Long getItemID() {
        long millis = System.currentTimeMillis();
        int i = random.nextInt(1000);
        System.out.println(i);
        String id = String.valueOf(millis) + String.format("%04d", i);
        System.out.println(String.format("%04d", i));
        return Long.valueOf(id);
    }
}
