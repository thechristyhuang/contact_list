package com.example.christy0514.homework5;

import java.io.Serializable;

/**
 * Created by christy0514 on 2017/12/20.
 */

public class Contact implements Serializable {
    private long id;
    private String name;
    private String phone;
    private String text;


    public Contact(String name, String phone, String text){
        this(0,name,phone,text);
    }

    public Contact(long id,String name,String phone,String text){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.text = text;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
