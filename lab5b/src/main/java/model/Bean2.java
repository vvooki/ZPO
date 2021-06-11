package model;

import annotation.Named;
import model.BeanEnum.*;

public class Bean2 {
    @Named(value = "String")
    String name;
    @Named(value = "double")
    double age;
    @Named(value = "Boolean")
    Boolean isAdult;
    @Named(value = "Enum")
    Enum type;

    public Bean2() {
        name = "Default Name";
        age = 18;
        isAdult = true;
        type = BeanEnum.TYPE3;
    }

    public Bean2(String name, int number) {
        this.name = name;
        this.age = number;
    }

    public Boolean getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(Boolean adult) {
        isAdult = adult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }
}
