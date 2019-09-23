package com.example.forum.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "topics")
public class Topic {
    @Id@Expose
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "idtopics", length = 6, nullable = false)
    private Integer idtopics;
    @Column@Expose
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")@Expose
    private User user;

    @JsonCreator
    public Topic(@JsonProperty("id")Integer idtopics, @JsonProperty("name")String name, @JsonProperty("user")User user) {
        this.idtopics = idtopics;
        this.name = name;
        this.user = user;
    }

    public Topic(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Topic() {}

    public Integer getIdtopics() {
        return idtopics;
    }

    public void setIdtopics(Integer idtopics) {
        this.idtopics = idtopics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
