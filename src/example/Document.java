package example;

import db.Entity;
import db.Trackable;
import java.util.Date;

public class Document extends Entity implements Trackable {
    public String content;
    private Date creationDate;
    private Date lastModificationDate;

    public Document(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Entity copy() {
        Document copy = new Document(this.content);
        copy.id = this.id;
        copy.setCreationDate(this.creationDate);
        copy.setLastModificationDate(this.lastModificationDate);
        return copy;
    }

    @Override
    public int getEntityCode() {
        // عدد 2 را برای Document  در نظر بگیریم و ولیدیتور نداریم
        return 2;
    }

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
}
