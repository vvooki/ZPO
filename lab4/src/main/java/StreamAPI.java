import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class StreamAPI {

    public static void StreamApiRun(List<Item> items) {
        Instant t1 = Instant.now();

        items.parallelStream().forEach(item -> {
            item.produceMe();
            item.consumeMe();
        });

        System.out.println(Duration.between(t1,Instant.now()).toMillis()/1000 + " sekund");
    }

}
