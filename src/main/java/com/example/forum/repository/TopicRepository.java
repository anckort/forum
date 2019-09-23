package com.example.forum.repository;

import com.example.forum.entity.Topic;
import com.example.forum.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Integer> {
    List<Topic> findAllByUser(User user);
    Topic findByIdtopics(Integer id);
}
