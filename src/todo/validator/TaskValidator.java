//وظیفهٔ ولیدیت کردن Taskها برای دیتابیس رو بر عهده داره

package todo.validator;

import db.Entity;
import db.Validator;
import db.exception.InvalidEntityException;
import todo.entity.Task;

public class TaskValidator implements Validator {

    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if (!(entity instanceof Task)) {
            throw new IllegalArgumentException("Entity must be type of Task.");
        }

        Task task = (Task) entity;

        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            throw new InvalidEntityException("Task title cannot be null or empty.");
        }
    }
}
