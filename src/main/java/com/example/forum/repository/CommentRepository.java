package com.example.forum.repository;

import com.example.forum.entity.Comment;
import com.example.forum.entity.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findAllByTopic(Topic topic);
}
