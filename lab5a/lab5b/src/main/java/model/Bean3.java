package model;

import annotation.Named;

import static model.BeanEnum.*;

public class Bean3 {
    @Named(value = "String")
    private String brand;
    @Named(value = "String")
    private String model;
    @Named(value = "Enum")
    private Enum type;
    @Named(value = "Int")
    private int engineSize;

    public Bean3() {
        this.brand = "unknown";
        this.model = "unknow";
        this.type = TYPE1;
        this.engineSize = 1;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }
}
