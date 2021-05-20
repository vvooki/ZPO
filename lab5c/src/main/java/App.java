import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class App {

    public static void runMethod(Method method, Object object, long time) {
        if(!method.isAccessible())
            method.setAccessible(true);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                try {
                    method.invoke(object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, time);
    }

    public static void findMethods(String className) {
        try {
            Class cls = Class.forName(className);
            Object classInstance = (Object) cls.newInstance();
            System.out.println("instance: " + classInstance.getClass());

            Car car = new Car();

            Method methods[] = classInstance.getClass().getDeclaredMethods();
            Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(Scheduled.class) && method.getParameterCount()==0)
                    .forEach(method -> {
                        Scheduled scheduled = method.getAnnotation(Scheduled.class);
                        System.out.println("\nWywolanie metody po nazwie\n");
                        runMethod(method,classInstance, scheduled.period());

                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("nie podano klasy");
            System.exit(0);
        }

        Arrays.stream(args).forEach(App::findMethods);
    }
}
