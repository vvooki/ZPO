package Cars;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import static Cars.CarType.HATCHBACK;

public class App {
    public static void main(String[] args) throws NoSuchFieldException {
        String className = "Cars.Car";
        Object classInstance = null;
        Object classInstance2 = null;

        ObservableList<Object> objectsList = FXCollections.observableArrayList();

        //czytanie classname
        //className = App.class.getPackage().getName() + "." + className;

        //tworzenie instancji 'className'
        //1
        try {
            Class cls = Class.forName(className);
            Class partypes[] = new Class[5];
            partypes[0] = String.class;
            partypes[1] = String.class;
            partypes[2] = Enum.class;
            partypes[3] = Integer.TYPE;
            partypes[4] = Integer.TYPE;

            Constructor classConstructor = cls.getConstructor(partypes);
            classConstructor.setAccessible(true);

            Object arglist[] = new Object[5];
            arglist[0] = "Honda";
            arglist[1] = "Civic";
            arglist[2] = HATCHBACK;
            arglist[3] = 988;
            arglist[4] = 129;
            classInstance = classConstructor.newInstance(arglist);
            System.out.println("instance1: " + classInstance.getClass().getName());
            System.out.println(classInstance);

            //2
            classInstance2 = (Object) cls.newInstance();
            System.out.println("instance2: " + classInstance2.getClass());

        } catch (Exception e) {
            e.printStackTrace();
        }
        objectsList.add(classInstance);
        objectsList.add(classInstance2);

        //listowanie pól
        System.out.println("\nListowanie pól\n");
        try {
            Field fields[] = classInstance.getClass().getDeclaredFields();

            for(Field field : fields) {
                field.setAccessible(true);
                System.out.println(field.getGenericType().getTypeName() + " " + field.getName() + " = " + field.get(classInstance));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //listowanie metod
        System.out.println("\nListowanie metod\n");
        try {
            Method methods[] = classInstance.getClass().getDeclaredMethods();
            Arrays.stream(methods).forEach(method -> {
                method.setAccessible(true);
                System.out.println(method.getReturnType().toString() + " " + method.getName());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        //wywołanie metody po nazwie
        System.out.println("\nWywolanie metody po nazwie\n");
        try {
            Method m = classInstance.getClass().getMethod("setHorsePower", int.class);
            System.out.println(m.invoke(classInstance, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String test = "horsePower";
        System.out.println("\nTyp pola\n");
        Field field = classInstance.getClass().getDeclaredField(test);
        System.out.println(field.getGenericType().getTypeName());




    }
}
