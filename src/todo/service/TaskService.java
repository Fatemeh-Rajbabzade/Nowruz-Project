package todo.service;

import db.Database;
import db.exception.InvalidEntityException;
import todo.entity.Task;

public class TaskService {
    public static void setAsCompleted(int taskId){
        Task task = (Task) Database.get(taskId);
        task.setStatus(Task.Status.Completed);
        Database.update(task);
    }

    public static void setAsInProgress(int taskId) throws InvalidEntityException{
        Task task =(Task) Database.get(taskId);
        task.setStatus(Task.Status.InProgress);
        Database.update(task);
    }

    public static void setAsNotStarted(int taskId) throws InvalidEntityException{
        Task task =(Task) Database.get(taskId);
        task.setStatus(Task.Status.NotStarted);
        Database.update(task);
    }
}
