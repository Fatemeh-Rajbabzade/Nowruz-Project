package example;
public class Human extends db.Entity {
    public String name;
    public int age;
    public static final int codeOfHumanEntity = 14;
    public Human(String name , int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public Human copy() {
        Human copyHuman = new Human(name, age);
        copyHuman.id = id;

        return copyHuman;
    }

    @Override
    public int getEntityCode()
        return codeOfHumanEntity;
}