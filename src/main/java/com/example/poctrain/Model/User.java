package com.example.poctrain.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    private String usrname;
    private String pwd;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User(String usrname, String pwd) {
        this.usrname = usrname;
        this.pwd = pwd;
    }

    public User() {

    }



    public String getUsrname() {
        return usrname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
