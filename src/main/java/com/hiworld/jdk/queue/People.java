package com.hiworld.jdk.queue;

public class People {
    
    public int id;
    public String name;
    
    public People() {
        
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public People(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
