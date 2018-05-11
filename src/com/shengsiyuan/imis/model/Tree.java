package com.shengsiyuan.imis.model;

import java.util.List;

public class Tree {

    private DataObj data;
    private String state;
    private List<Tree> children;
    public DataObj getData() {
        return data;
    }
    public void setData(DataObj data) {
        this.data = data;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public List<Tree> getChildren() {
        return children;
    }
    public void setChildren(List<Tree> children) {
        this.children = children;
    }
    
}
