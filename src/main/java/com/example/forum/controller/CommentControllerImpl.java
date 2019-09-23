package com.example.forum.controller;

import com.example.forum.constants.ErrorCodes;
import com.example.forum.entity.Comment;
import com.example.forum.entity.Topic;
import com.example.forum.entity.User;
import com.example.forum.logger.Logging;
import com.example.forum.service.CommentService;
import com.example.forum.service.TopicService;
import com.example.forum.service.UserService;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CommentControllerImpl implements CommentController, Response {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;
    Logger logger = Logging.getLogger();
    @Override
    public String addComment(String commentData) {
        Map<String, String> commentDataMap = getMapFromJson(commentData);
        int errorCode = 0;
        String errorMsg = "";
        JsonObject responseData = new JsonObject();
        JsonObject response = new JsonObject();
        try {
            User user = userService.findById(Integer.parseInt(commentDataMap.get("user")));
            Topic topic = topicService.findById(Integer.parseInt(commentDataMap.get("topic")));
            if(topic == null){
                errorCode = ErrorCodes.INVALID_CREDENTIALS;
                errorMsg = "Not found topic with such id";
                logger.error("Not found topic with such id " + commentDataMap.get("topic"));
            }else {
                Comment comment = new Comment(commentDataMap.get("text"), user, topic);
                commentService.save(comment);
                responseData = fillCommentData(comment);
            }
        }catch (Exception e){
            errorCode = ErrorCodes.INVALID_CREDENTIALS;
            errorMsg = "Creation comment error";
            logger.error("Creation comment error//"+e.getStackTrace());
        }
        response = createResponseContainer(errorCode, errorMsg, responseData);
        return response.toString();
    }

    @Override
    public String findAllByTopic(String topicId) {
        Integer id = Integer.parseInt(getMapFromJson(topicId).get("topicId"));
        int errorCode = 0;
        String errorMsg = "";
        JsonObject responseData = new JsonObject();
        JsonObject response = new JsonObject();
        try {
            List<Comment> comments = commentService.findAllByTopic(id);
            responseData = fillCommentData(comments);
        }catch (Exception e){
            errorCode = ErrorCodes.INVALID_CREDENTIALS;
            errorMsg = "Getting comment error";
            logger.error("Getting comment error//"+e.getStackTrace());
        }
        response = createResponseContainer(errorCode, errorMsg, responseData);
        return response.toString();
    }
}
