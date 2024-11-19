package entities;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private Integer age;
    private List<Task> tasksList;

    public Customer(String name, Integer age){
        this.name = name;
        this.age = age;
        this.tasksList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Task> getTasksList() {
        return tasksList;
    }

    @Override
    public String toString() {
        return "Cliente - {" +
                "nome=" + name +
                ", age=" + age;
    }
}
