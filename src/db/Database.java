package db;
import java.util.ArrayList;
import db.exception.EntityNotFoundException;

public class Database {
    //  لیست موجودیت ها به نام entities
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int currentId = 1;
    public static void add(Entity e){
        e.id = currentId++;
        entities.add(e);
    }
    //پیدا کردن موجودیت با ایدی مدنظر
    public static Entity get(int id) throws EntityNotFoundException {
        for (Entity e : entities){
            if (e.id == id)
                return e;
        }
        throw new EntityNotFoundException(id);
    }

    public static  void delete(int id){
        Entity entity = get(id);
        entities.remove(entity);
    }

    public static void update(Entity e){
        Entity entity = get(e.id);
        entities.remove(entity);
        entities.add(e);

    }
}
