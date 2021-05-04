import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    public static void NewFixed() {


        BlockingQueue<Item> queue = new LinkedBlockingQueue<>(100);
        for(int i = 0; i < 100; i++)
            queue.add( new Item());


        ExecutorService executorService = Executors.newFixedThreadPool(7);
        Instant t1 = Instant.now();


        Runnable task = () -> {
            while(queue.size()>0) {
                Item item = null;
                try {
                    item = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                item.produceMe();
                item.consumeMe();
            }
            executorService.shutdown();

        };

        for (int i = 0; i < 7; i++) {
            executorService.execute(task);
        }

        while(!executorService.isTerminated()) {}
        System.out.println(Duration.between(t1,Instant.now()).toMillis()/1000 + " sekund");

    }

    public static void newCashed(List<Item> items) {
        Instant t1 = Instant.now();
        ExecutorService executorService = Executors.newCachedThreadPool();

        items.stream().forEach(item -> {
            executorService.execute(() -> {
                item.produceMe();
                item.consumeMe();
            });
        });

        executorService.shutdown();
        while(!executorService.isTerminated()) {}
        System.out.println(Duration.between(t1,Instant.now()).toMillis()/1000 + " sekund");
    }

    public static void newScheduled() {
        Instant t1 = Instant.now();

        BlockingQueue<Item> queue = new LinkedBlockingQueue<>(100);
        for(int i = 0; i < 100; i++)
            queue.add( new Item());

        ExecutorService executorService = Executors.newScheduledThreadPool(7);
        Runnable task = () -> {
            while(queue.size()>0) {
                Item item = null;
                try {
                    item = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                item.produceMe();
                item.consumeMe();
            }
            executorService.shutdown();
        };

        for (int i = 0; i < 7; i++)
            executorService.execute(task);

        while(!executorService.isTerminated()) {}
        System.out.println(Duration.between(t1,Instant.now()).toMillis()/1000 + " sekund");
    }


}
