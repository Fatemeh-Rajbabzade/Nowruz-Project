// نشان دادن قدم های لازم برای انجام دادن یک کار
package todo.entity;

import db.Entity;
import java.util.Date;

public class Step extends Entity {
    private String title;
    private Status status;
    private int taskRef;

    public Step(String title, int taskRef){
        this.title = title;
        this.status = Status.NotStarted;
        this.taskRef = taskRef;
    }

    //getter & setter
    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getTaskRef() {
        return taskRef;
    }

    public void setTaskRef(int taskRef) {
        this.taskRef = taskRef;
    }

    // متد هایی که ارث بری کرده از Entity
    @Override
    public int getEntityCode(){
        return 5;
    }

    @Override
    public Entity copy() {
        Step stepCopy = new Step(this.title, this.taskRef);
        stepCopy.status = this.status;
        stepCopy.id = this.id;
        return stepCopy;
    }

    public enum Status{
        NotStarted, Completed
    }
}
