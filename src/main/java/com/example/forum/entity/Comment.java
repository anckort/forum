package com.example.forum.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Expose
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "idcomments", length = 6, nullable = false)
    private Integer idcomments;
    @Column@Expose
    private String text;
    @ManyToOne
    @JoinColumn(name = "userid")@Expose
    private User user;
    @ManyToOne
    @JoinColumn(name = "topicid")@Expose
    private Topic topic;

    @JsonCreator
    public Comment(@JsonProperty("id")Integer idcomments, @JsonProperty("text")String text, @JsonProperty("user")User user, @JsonProperty("topic")Topic topic) {
        this.idcomments = idcomments;
        this.text = text;
        this.user = user;
        this.topic = topic;
    }

    public Comment(String text, User user, Topic topic) {
        this.text = text;
        this.user = user;
        this.topic = topic;
    }

    public Comment() {
    }

    public Integer getIdcomments() {
        return idcomments;
    }

    public void setIdcomments(Integer idcomments) {
        this.idcomments = idcomments;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
