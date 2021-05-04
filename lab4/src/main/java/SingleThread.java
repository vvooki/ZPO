import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleThread {

    private static void singleThreadProduce(int ThreadId, List<Item> items) {
        for (int i = ThreadId; i < items.size(); i += 4)
            items.get(i).produceMe();
    }

    private static void singleThreadConsume(int ThreadId, List<Item> items) {
        for (int i = ThreadId; i < items.size(); i += 3)
            items.get(i).consumeMe();
    }

    public static void SingleThreadRun(List<Item> items) throws InterruptedException {
        Instant t1 = Instant.now();

        Thread threadProduce = null;
        Thread threadConsume = null;

        for (int i = 0; i < 4; i++) {
            int threadIdProduce= i;
            threadProduce = new Thread(() -> singleThreadProduce(threadIdProduce, items));
            threadProduce.start();

            if (i < 3) {
                int threadIdConsume = i;
                threadConsume = new Thread(() -> singleThreadConsume(threadIdConsume, items));
                threadConsume.start();
            }

        }
        threadProduce.join();
        threadConsume.join();
        System.out.println(Duration.between(t1,Instant.now()).toMillis()/1000 + " sekund");

    }


}
