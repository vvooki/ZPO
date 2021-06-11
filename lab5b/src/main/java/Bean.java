import annotation.MyAnnotation;

import java.util.Date;

public class Bean {
    @MyAnnotation(name="Nazwa", value="Imie")
    String name;
    @MyAnnotation(name = "Data", value = "Data urodzenia")
    Date date;
    Enum beanEnum;

    public Bean() {
        name = "Default Name";
        date = new Date(System.currentTimeMillis());
        beanEnum = BeanEnum.TYPE1;
    }

    public Bean(String name, Date date, Enum beanEnum) {
        this.name = name;
        this.date = date;
        this.beanEnum = beanEnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Enum getBeanEnum() {
        return beanEnum;
    }

    public void setBeanEnum(Enum beanEnum) {
        this.beanEnum = beanEnum;
    }

    /*@Override
    public String toString() {
        return "Bean{" +
                "name:'" + name + '\'' +
                ",\ndate:" + date +
                ",\nbeanEnum:" + beanEnum +
                '}';

    }*/
}
