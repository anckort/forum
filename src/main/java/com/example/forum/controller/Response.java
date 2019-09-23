package com.example.forum.controller;

import com.example.forum.entity.Comment;
import com.example.forum.entity.Topic;
import com.example.forum.entity.User;
import com.example.forum.sigeltons.GsonSingelton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public interface Response {
    default JsonObject createResponseContainer(int errorCode, String errorMessage, JsonObject responseData) {
        JsonObject responseContainer = new JsonObject();
        responseContainer.addProperty("errorCode", errorCode);
        responseContainer.addProperty("errorMessage", errorMessage);
        responseContainer.add("data", responseData);

        return responseContainer;
    }
    default JsonObject fillUserData(User user) {
        Gson gson = GsonSingelton.getInstance();
        JsonObject userData = new JsonObject();
        userData.addProperty("id", user.getIdusers());
        userData.addProperty("username", user.getUsername());
        userData.addProperty("password", user.getPassword());
        return userData;
    }

    default Map<String, String> getMapFromJson(String json){
        Gson gson = GsonSingelton.getInstance();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> property = gson.fromJson(json, type);

        return property;
    }

    default JsonObject fillTopicData(Topic topic){
        Gson gson = GsonSingelton.getInstance();
        JsonObject topicData = new JsonObject();
        topicData.addProperty("id", topic.getIdtopics());
        topicData.addProperty("name", topic.getName());
        topicData.addProperty("idUser", topic.getUser().getIdusers());
        return topicData;
    }
    default JsonObject fillTopicData(List<Topic> topicList){
        Gson gson = GsonSingelton.getInstance();
        JsonObject topicData = new JsonObject();
        topicData.add("topics", gson.toJsonTree(topicList));
        return topicData;
    }
    default JsonObject fillCommentData(Comment comment){
        Gson gson = GsonSingelton.getInstance();
        JsonObject commentData = new JsonObject();
        commentData.addProperty("id", comment.getIdcomments());
        commentData.addProperty("text", comment.getText());
        commentData.addProperty("idUser", comment.getUser().getIdusers());
        commentData.addProperty("idTopic", comment.getTopic().getIdtopics());
        return commentData;
    }

    default JsonObject fillCommentData(List<Comment> comments){
        Gson gson = GsonSingelton.getInstance();
        JsonObject commentData = new JsonObject();
        commentData.add("comments", gson.toJsonTree(comments));
        return commentData;
    }
}
