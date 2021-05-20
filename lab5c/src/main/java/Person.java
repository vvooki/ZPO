public class Person {
    String name;
    int age;
    int height;

    public Person() {
        this.name ="human";
        this.age = 18;
        this.height = 180;
    }

    @Scheduled(period = 1500)
    public void isAdult() {
        if(age >= 18) {
            System.out.println(name + " jest pelnoletni");
        } else System.out.println(name + " jest niepelnoletni");;
    }

    @Scheduled(period = 5000)
    public void printAge() {
        System.out.println(name + " is now " + age + "yo");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
