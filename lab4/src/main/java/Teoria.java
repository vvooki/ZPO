import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Teoria {

    /*
    public static class MyThread extends Thread{
        public void run() {
            System.out.println("MyThread start");
            System.out.println("MyThread stop");
        }
    }

    public static List<Item> Lista() {
        List<Item> myList = new ArrayList<Item>(Arrays.asList());

        for(int i=0; i<100; i++) {
            Item item = new Item();
            //System.out.println(item.getName());
            myList.add(item);
        }
        return myList;

    }

    private static void singleThreadProduce(int singleThreadID, List<Item> items) {
        for (int i = singleThreadID; i < items.size(); i += 4)
            items.get(i).produceMe();
    }

    private static void singleThreadConsume(int singleThreadID, List<Item> items) {
        for (int i = singleThreadID; i < items.size(); i += 3)
            items.get(i).consumeMe();
    }


    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for(int j=0; j < Lista().size(); j+=4) {
                Lista().get(j).produceMe();
            }
        }

    }
    public static class PoolTheradRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("PoolTheradRunnable start");
            System.out.println("PoolTheradRunnable stop");
        }
    }
    public static class TheadPool{}
    */



    public static void main(String[] args) throws Exception {
        //List<Item> items = Lista();

        /*items.parallelStream().forEach(item -> {
            item.produceMe();
            item.consumeMe();
        });*/
        //ThreadPool.NewFixed();
        //ThreadPool.newScheduled();
        //ThreadPool.newCashed();
        //App.ThreadPool();
        /*
        Thread tp;
        Thread tc;


        for (int i = 0; i < 4; i++) {
            int finalI1 = i;
            tp = new Thread(() -> singleThreadProduce(finalI1, items));
            tp.start();

            if (i < 3) {
                int finalI = i;
                tc = new Thread(() -> singleThreadConsume(finalI, items));
                tc.start();
            }
        }


        //ExecutorService
        BlockingQueue<Item> queue = new LinkedBlockingQueue<>(100);
        for(int i = 0; i < 100; i++) {
            queue.add( new Item());
        }

        ExecutorService executorService = Executors.newFixedThreadPool(7);
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

          */
        /*

        //ExecutorService
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(100);
        for(int i = 0; i < 100; i++) {
            queue.add(new Runnable() {
                @Override
                public void run() {
                    System.out.println("MyRunnable Blocking start");
                    System.out.println("MyRunnable Blocking stop");
                }
            });
        }

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable task = () -> {
            while(qble runnable = null;
                try {ueue.size()>0) {
                Runna
                    runnable = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
            executorService.shutdown();
        };

        for (int i = 0; i < 2; i++) {
            executorService.execute(task);
        }

        //Thread
        Thread thread = new Thread();
        thread.start();*/

        //MyThread
        /*Thread thread = new Thread();
        thread.start();

        //MyRunnable
        Thread thread2 = new Thread( new MyRunnable() );
        thread2.start();

        //Anonymous
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous start");
                System.out.println("Anonymous stop");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        //Labmda
        Runnable runnableL = () ->{
            System.out.println("Lambda start");
            System.out.println("Lambda stop");
        };
        Thread thread3 = new Thread(runnableL);
        thread3.start();*/
    }
}
