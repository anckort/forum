package com.example.forum.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id@Expose
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "idusers", length = 6, nullable = false)
    private Integer idusers;
    @Column@Expose
    private String username;
    @Column @Expose(serialize = false)
    private String password;

    @JsonCreator
    public User(@JsonProperty("id") Integer idusers, @JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.idusers = idusers;
        this.username = username;
        this.password = password;
    }

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getIdusers() {
        return idusers;
    }

    public void setIdusers(Integer idusers) {
        this.idusers = idusers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
