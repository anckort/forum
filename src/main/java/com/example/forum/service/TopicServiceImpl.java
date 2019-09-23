package com.example.forum.service;

import com.example.forum.entity.Topic;
import com.example.forum.entity.User;
import com.example.forum.logger.Logging;
import com.example.forum.repository.TopicRepository;
import com.example.forum.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicRepository repository;
    @Autowired
    UserRepository userRepository;
    Logger logger = Logging.getLogger();

    @Override
    public void save(Topic topic) {
        repository.save(topic);
        logger.info("Saving topic id="+topic.getIdtopics());
    }

    @Override
    public List<Topic> findAllByUser(Integer idusers) {
        User user = userRepository.findByIdusers(idusers);
        List<Topic> topicList = repository.findAllByUser(user);
        logger.info("Getting topics by user id="+idusers);
        return  topicList;
    }

    @Override
    public Topic findById(Integer id) {
        Topic topic = repository.findByIdtopics(id);
        logger.info("Getting topic by id="+id);
        return topic;
    }

    @Override
    public void delete(Topic topic) {
        repository.delete(topic);
    }

}
