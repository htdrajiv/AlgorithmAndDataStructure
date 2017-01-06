import java.io.IOException;
import java.lang.Runtime;

/**
 * Created by Rajiv on 12/1/2016.
 */
public class RunTimeExecution {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("notepad");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Runtime.getRuntime().exec("netsh wlan stop hostednetwork");
    }
}
