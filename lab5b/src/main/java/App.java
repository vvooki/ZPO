import annotation.MyAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        Bean object = new Bean("aaa", new Date(System.currentTimeMillis()), BeanEnum.TYPE3);
        System.out.println(object.toString()+'\n');

        Field[] fields = object.getClass().getDeclaredFields();
        System.out.println();
        for(Field field: fields) {
            Annotation[] annotations = field.getAnnotations();
            for(Annotation annotation : annotations) {
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }
    }
}

