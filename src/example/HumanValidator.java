package example;
import db.exception.InvalidEntityException;
import db.Validator;
import db.Entity;

public class HumanValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if (!(entity instanceof Human))
            throw new IllegalArgumentException("The typr of Entity must be Human!");

        Human human = (Human) entity;
        if (human.age < 0)
            throw new InvalidEntityException("Age nust be positive.");

        if (human.name == null || human.name.isEmpty())
            throw new InvalidEntityException("Name can not be empty or null.");
    }
}