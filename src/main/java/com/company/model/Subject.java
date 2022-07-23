package com.company.model;

public class Subject {
    private long id;
    private String name;
    private int capacity;

    public Subject() {
    }

    public Subject(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public Subject(long id, String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", name=" + name + ", capacity=" + capacity + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
