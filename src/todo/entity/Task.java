// نشان دادن انجام یک کار
package todo.entity;

import db.Entity;
import db.Trackable;
import java.util.Date;

public class Task extends Entity implements Trackable {
    private String title;
    private String description;
    private Date dueDate;
    private Status status;
    private Date creationDate;
    private Date lastModificationDate;

    public Task(String title, String description, Date dueDate){
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    //setter & getter
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;

    }
    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Date getDueDate(){
        return dueDate;
    }

    public void setDueDate(Date dueDate){
        this.dueDate = dueDate;
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    //متد های ارث بری کرده Entity
    @Override
    public int getEntityCode(){
        return 4;
    }

    // کپی‌ از Task
    @Override
    public Entity copy() {
        Task taskCopy = new Task(this.title, this.description, this.dueDate);
        taskCopy.status = this.status;
        taskCopy.id = this.id;
        taskCopy.setCreationDate(this.getCreationDate());
        taskCopy.setLastModificationDate(this.getLastModificationDate());
        return taskCopy;
    }

    //متد های ارث بری کرده از Trackable
    @Override
    public void setCreationDate(Date date) {
        this.creationDate = date;
    }



    @Override
    public Date getCreationDate() {
        return this.creationDate;
    }

    @Override
    public void setLastModificationDate(Date date) {
        this.lastModificationDate = date;
    }

    @Override
    public Date getLastModificationDate() {
        return this.lastModificationDate;
    }

    //وضعیت کارمون
    public enum Status{
        NotStarted, InProgress, Completed
    }
}
