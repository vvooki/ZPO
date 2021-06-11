package model;

import annotation.MyAnnotation;
import annotation.Named;

import java.util.Date;

public class Bean {
    @Named(value = "String")
    String name;
    @Named(value = "Date")
    Date date;
    @Named(value = "Enum")
    Enum beanEnum;
    @Named(value = "Int")
    int number;


    public Bean() {
        name = "Default Name";
        date = new Date(System.currentTimeMillis());
        beanEnum = BeanEnum.TYPE1;
        number = 5;
    }

    public Bean(String name, Date date, Enum beanEnum, int number) {
        this.name = name;
        this.date = date;
        this.beanEnum = beanEnum;
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

