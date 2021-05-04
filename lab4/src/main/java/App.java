import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class App {

    public static List<Item> generateList() {
        List<Item> myList = new ArrayList<Item>(Arrays.asList());

        for(int i=0; i<100; i++) {
            Item item = new Item();
            myList.add(item);
        }
        return myList;

    }

    //private static volatile List<Item> items = generateList();

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerPool = new Scanner(System.in);

        List<Item> items;
        for( ; ; ) {
            System.out.println("1 - Pojedyńcze wątki pracujące na liście (4 produkujące i 3 konsumujące)");
            System.out.println("2 - ThreadPool");
            System.out.println("3 - StreamAPI");
            int option = scanner.nextInt();

            switch(option) {
                case 1:
                    items = generateList();
                    SingleThread.SingleThreadRun(items);
                    break;
                case 2:
                    System.out.println("1-NewFixed");
                    System.out.println("2-NewCashed");
                    System.out.println("3-NewScheduled");
                    int optionPool = scannerPool.nextInt();
                    switch (optionPool) {
                        case 1:
                            ThreadPool.NewFixed();
                            break;
                        case 2:
                            items = generateList();
                            ThreadPool.newCashed(items);
                            break;
                        case 3:
                            ThreadPool.newScheduled();
                            break;
                    }
                    break;
                case 3:
                    items = generateList();
                    StreamAPI.StreamApiRun(items);
                    break;
                case 4:
                    System.out.println("test");
                    break;
            }
        }
    }




}
