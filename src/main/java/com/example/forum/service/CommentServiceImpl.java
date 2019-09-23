package com.example.forum.service;

import com.example.forum.entity.Comment;
import com.example.forum.entity.Topic;
import com.example.forum.logger.Logging;
import com.example.forum.repository.CommentRepository;
import com.example.forum.repository.TopicRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TopicRepository topicRepository;

    Logger logger = Logging.getLogger();

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
        logger.info("Saving comment into db id="+comment.getIdcomments());
    }

    @Override
    public List<Comment> findAllByTopic(Integer topicId) {
        Topic topic = topicRepository.findByIdtopics(topicId);
        List<Comment> comments = commentRepository.findAllByTopic(topic);
        logger.info("Getting comments by id="+topicId);
        return comments;
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
