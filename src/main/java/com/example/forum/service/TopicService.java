package com.example.forum.service;

import com.example.forum.entity.Topic;

import java.util.List;

public interface TopicService {
    void save(Topic topic);
    List<Topic> findAllByUser(Integer idusers);
    Topic findById(Integer id);
    void delete(Topic topic);
}
