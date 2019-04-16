package com.example.androidcsdcourse.models;

public class Student {
    String name;
    String dept;
    String email;

    public Student(String name, String dept, String email) {
        this.name = name;
        this.dept = dept;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
