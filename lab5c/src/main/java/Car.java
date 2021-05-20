public class Car {
    private String brand;
    private String model;
    private int engineSize;
    private int horsePower;

    public Car() {
        this.brand = "Audi";
        this.model = "A3";
        this.engineSize = 2;
        this.horsePower = 100;
    }

    public Car(String brand, String model, Enum type, int engineSize, int horsePower) {
        this.brand = brand;
        this.model = model;
        this.engineSize = engineSize;
        this.horsePower = horsePower;
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

    @Scheduled(period = 2000)
    private void PowerToEngineRatio() {
        System.out.println("PowerToEnginge: " + this.horsePower/this.engineSize);
    }

    @Scheduled(period = 5000)
    public void drive() {
        System.out.println("Enjoy driving " + this.brand + " " + this.model);
    }
}