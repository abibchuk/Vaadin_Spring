package com.anjey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Anjey on 11.01.2018.
 */
@Entity
@Table(name = "user_data", catalog = "vaadindb")
public class User {
    @Column(name = "password",length = 20)
    private String password;
    @Id
    @Column(name = "name", unique = true,length = 20)
    private String name;

    public User() {
    }

    public User(String name, String password) {
        this.password = password;
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
