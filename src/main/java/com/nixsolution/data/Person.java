package com.nixsolution.data;

import com.nixsolution.annotation.PropertyKey;

public class Person {

    @PropertyKey("connections.limit")
    private Integer connectionNumber;
    @PropertyKey("name")
    private String name;
    @PropertyKey("age")
    private int age;
    @PropertyKey("gender")
    private Gender gender;
    @PropertyKey("isUser")
    private boolean isUser;

    @Override
    public String toString() {
        return "Person{ " +
                "Number of Connections = " + connectionNumber +
                ", Name = '" + name + '\'' +
                ", Age =  " + age +
                ", Gender = " + gender +
                ", isUser = " + isUser +
                '}';
    }
}
