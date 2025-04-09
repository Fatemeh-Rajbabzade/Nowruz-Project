package example;
import db.Database;
import example.Human;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;

public class Main {
    public static void main(String[] args) throws InvalidEntityException {
        Database.registerValidator(Human.HUMAN_ENTITY_CODE, new HumanValidator());

        Human ali = new Human("Ali", -10);
        Database.add(ali);
    }
}