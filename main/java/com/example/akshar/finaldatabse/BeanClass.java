package com.example.akshar.finaldatabse;

import java.io.Serializable;

/**
 * Created by Akshar on 9/4/2017.
 */

public class BeanClass implements Serializable {
    public  String id,name,number,image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BeanClass{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
