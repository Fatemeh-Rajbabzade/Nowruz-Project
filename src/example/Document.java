package example;

import db.Entity;
import db.Trackable;
import java.util.Date;

public class Document extends Entity implements Trackable{
    private String content;
    private Date creationDate;
    private Date LastModificationDate;

    public Document(String content){
        this.content = content;
        //زمان اولین ایجاد
        this.creationDate = new Date();
        // زمان ایچاد و اخرین ایجاد
        this.LastModificationDate = this.creationDate;
    }

    @Override
    public void setCreationDate(Date date){
        this.creationDate = date;
    }

    @Override
    public Date getCreationDate(){
        return this.creationDate;
    }

    @Override
    public void setLastModificationDate(Date date){
        this.LastModificationDate = date;
    }

    @Override
    public Date getLastModificationDate(){
        return this.LastModificationDate;
    }

    //متد های کلاس Entity
    @Override
    public Entity copy(){
        Document copy = new Document(this.content);
        copy.setCreationDate(this.creationDate);
        copy.setLastModificationDate(this.LastModificationDate);
        return copy;
    }

    @Override
    public int getEntityCode(){
        //برای وجود 1 را برگردانذ
        return 1;
    }
    // content
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
        this.LastModificationDate = new Date();
    }
}
