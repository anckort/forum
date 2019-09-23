package com.example.forum.service;

import com.example.forum.entity.Comment;
import com.example.forum.entity.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    void save(Comment comment);
    List<Comment> findAllByTopic(Integer topicId);
    void delete(Comment comment);
}
