package com.itheima.rxandroid;

/**
 * Created by lenovo on 2016/10/9.
 */
public class Course {
    private String name;
    private int id;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
