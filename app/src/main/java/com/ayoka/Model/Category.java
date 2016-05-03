package com.ayoka.Model;

/**
 * Created by Ahmet&Korcan on 1.5.2016.
 */
public class Category {
    private String title, desc;
    private int id,maincategoryId,type,projectId;
    private String categoryName;

    public Category(){

    }
    public Category(String title, String desc, int id, int maincategoryId, int type) {
        this.title = title;
        this.desc = desc;
        this.id = id;
        this.maincategoryId = maincategoryId;
        this.type = type;
        this.categoryName=title;
        this.projectId=projectId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaincategoryId() {
        return maincategoryId;
    }

    public void setMaincategoryId(int maincategoryId) {
        this.maincategoryId = maincategoryId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
