package com.example.forum;

import com.example.forum.entity.Comment;
import com.example.forum.entity.Topic;
import com.example.forum.entity.User;
import com.example.forum.service.CommentService;
import com.example.forum.service.TopicService;
import com.example.forum.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicesTest {

    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;
    @Autowired
    CommentService commentService;

    @Test
    public void TestOfService(){
        User user = new User("test","test");
        userService.save(user);
        Assert.assertNotNull(user.getIdusers());
        Topic topic = new Topic("test",user);
        topicService.save(topic);
        Assert.assertNotNull(topic.getIdtopics());
        Comment comment = new Comment("test",user,topic);
        commentService.save(comment);
        Assert.assertNotNull(comment.getIdcomments());

        commentService.delete(comment);

        topicService.delete(topic);

        userService.delete(user);
    }

}
