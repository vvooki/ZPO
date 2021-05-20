package model;

import static model.BeanEnum.*;

public class Bean3 {
    private String brand;
    private String model;
    private Enum type;
    private int engineSize;
    private int horsePower;

    public Bean3() {
        this.brand = "unknown";
        this.model = "unknow";
        this.type = TYPE1;
        this.engineSize = 1;
        this.horsePower = 50;
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

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}
