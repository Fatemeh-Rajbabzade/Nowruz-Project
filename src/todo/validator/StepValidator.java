// وظیفهٔ validate کردن Stepها برای دیتابیس رو بر عهده داره

package todo.validator;

import db.Validator;
import db.Database;
import db.Entity;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import todo.entity.Step;
import todo.entity.Task;

public class StepValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException{
        if (!(entity instanceof Step))
            throw new IllegalArgumentException("Entity must be in Step.");
        Step step = (Step) entity;
        if (step.getTitle() == null || step.getTitle().trim().isEmpty())
            throw new IllegalArgumentException("step cannot be null or empty.");

        // تست اینکه Taskای با idی مساویِ taskRef توی دیتابیس باشه
        try {
            Entity task = Database.get(step.getTaskRef());
            if (!(task instanceof Task))
                throw new InvalidEntityException("taskRef musst be valid task.");
        }

            catch(EntityNotFoundException e){
                throw new InvalidEntityException("No Task with " + step.getTaskRef() + " id ");
            }

    }
}
