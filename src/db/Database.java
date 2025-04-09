package db;
import java.util.ArrayList;
import java.util.HashMap;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;

public class Database {
    //  لیست موجودیت ها به نام entities
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int currentId = 1;
    private static Validator validator;
    private static HashMap<Integer, Validator> validators = new HashMap<>();

    public static void registerValidator(int entityCode, Validator validator) {
        if (validators.containsKey(entityCode))
            throw new IllegalArgumentException("Validator with this entityCode exists.");
    validator.put(entityCode, validator);
    }

    public static void setValidator(Validator validator){
        Database.validator = validator;
    }

    private static boolean validEntity(Entity e){
        if (e.id <= 0)
            return false;
    }

    public static void add(Entity e) throws InvalidEntityException{
        if (e == null || !validEntity(e)) {
            throw new InvalidEntityException("Your Entity is invalid!");
        }

        Validator validator = validators.get(e.getEntityCode());
        if (validator != null)
            validator.validate(e);

        else
            throw new IllegalArgumentException("validator whith this entity code " + e.getEntityCode() + " not found.")
        e.id = currentId++;
        entities.add(e.copy());
    }

    //پیدا کردن موجودیت با ایدی مدنظر
    public static Entity get(int id) {
        for (Entity e : entities){
            if (e.id == id)
                return e.copy();
        }
        throw new EntityNotFoundException(id);
    }

    public static  void delete(int id) {
        Entity entity = get(id);
        entities.remove(entity);
    }

    public static void update(Entity e) throws InvalidEntityException {
        if (e == null || !validEntity(e))
            throw new InvalidEntityException("Your Entity is invalid!");
        Validator validator = validators.get(e.getEntityCode());
        if (validator != null)
            validator.validate(e);

        else
            throw new IllegalArgumentException("validator whith this entity code " + e.getEntityCode() + " not found.");

        Entity entity = get(e.id);
        entities.remove(entity);
        entities.add(e.copy());

    }
}
