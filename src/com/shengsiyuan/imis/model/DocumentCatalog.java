package com.shengsiyuan.imis.model;

public class DocumentCatalog {

    private long id;
    private long parentId;
    private String state;
    private String name;
    private long type;
    
    public long getType() {
        return type;
    }
    public void setType(long type) {
        this.type = type;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getParentId() {
        return parentId;
    }
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
