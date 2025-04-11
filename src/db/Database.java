package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;

public class Database {

    // لیست موجودیت‌ها
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int currentId = 1;
    private static Validator validator;
    private static HashMap<Integer, Validator> validators = new HashMap<>();

    // ثبت Validator برای Entity ها
    static {
        // ثبت Validator برای Human (با کد 2)
        validators.put(3, new Validator() {
            @Override
            public void validate(Entity entity) throws InvalidEntityException {
                if (entity == null) {
                    throw new InvalidEntityException("Human cannot be null.");
                }
            }
        });

        // ثبت Validator برای Document)
        validators.put(2, new Validator() {
            @Override
            public void validate(Entity entity) throws InvalidEntityException {
                if (entity == null) {
                    throw new InvalidEntityException("Document cannot be null.");
                }
            }
        });
    }

    // ثبت Validator جدید برای Entity
    public static void registerValidator(int entityCode, Validator validator) {
        if (validators.containsKey(entityCode))
            throw new IllegalArgumentException("Validator with this entityCode exists.");
        validators.put(entityCode, validator);
    }

    // تنظیم Validator پیش‌فرض
    public static void setValidator(Validator validator) {
        Database.validator = validator;
    }

    // بررسی صحت موجودیت‌ها
    private static boolean validEntity(Entity e) {
        return e.id > 0;
    }

    // اضافه کردن موجودیت جدید
    public static void add(Entity e) throws InvalidEntityException {
        if (e == null) {
            throw new InvalidEntityException("Your Entity cannot be null.");
        }

        Validator validator = validators.get(e.getEntityCode());
        if (validator != null) {
            validator.validate(e);
        } else {
            // Document اگر Validator نداریم، نیازی به اعتبارسنجی نیست
        }

        e.id = currentId++;

        // تنظیم تاریخ و زمان برای موجودیت‌های Trackable
        if (e instanceof Trackable) {
            Date now = new Date();
            ((Trackable) e).setCreationDate(now);
            ((Trackable) e).setLastModificationDate(now);
        }

        entities.add(e.copy());
    }

    // دریافت موجودیت با id مشخص
    public static Entity get(int id) {
        for (Entity e : entities) {
            if (e.id == id)
                return e.copy();
        }
        throw new EntityNotFoundException(id);
    }

    // حذف موجودیت با id مشخص
    public static void delete(int id) {
        Entity entity = get(id);
        entities.remove(entity);
    }

    // بروزرسانی موجودیت
    public static void update(Entity e) throws InvalidEntityException {
        if (e == null || !validEntity(e))
            throw new InvalidEntityException("Your Entity is invalid!");

        Validator validator = validators.get(e.getEntityCode());
        if (validator != null) {
            validator.validate(e);
        } else {
            // اگر Validator نداریم، نیازی به اعتبارسنجی نیست
        }

        // بروزرسانی تاریخ و زمان برای موجودیت‌های Trackable
        if (e instanceof Trackable) {
            Date now = new Date();
            ((Trackable) e).setLastModificationDate(now);
        }

        Entity entity = get(e.id);
        entities.remove(entity);
        entities.add(e.copy());
    }
}
