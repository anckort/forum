package com.example.forum.controller;

import com.example.forum.constants.ErrorCodes;
import com.example.forum.entity.Topic;
import com.example.forum.entity.User;
import com.example.forum.logger.Logging;
import com.example.forum.service.TopicService;
import com.example.forum.service.UserService;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TopicControllerImpl implements TopicController, Response {

    @Autowired
    private TopicService service;
    @Autowired
    private UserService userService;
    Logger logger = Logging.getLogger();

    @Override
    public String addTopic(String topicData) {
            Map<String, String> topicDataMap = getMapFromJson(topicData);
            int errorCode = 0;
            String errorMsg = "";
            JsonObject responseData = new JsonObject();
            JsonObject response = new JsonObject();
            try {
                User user = userService.findById(Integer.parseInt(topicDataMap.get("user")));
                if(user == null){
                    errorCode = ErrorCodes.INVALID_CREDENTIALS;
                    errorMsg = "User with such id does no exist";
                    logger.error("User with such id "+topicDataMap.get("user")+" does no exist");
                }else {
                    Topic topic = new Topic(topicDataMap.get("name"), user);
                    service.save(topic);
                    responseData = fillTopicData(topic);
                }
            }catch (Exception e){
                errorCode = ErrorCodes.INVALID_CREDENTIALS;
                errorMsg = "Creation topic error";
                logger.error("Creation topic error // "+ e.getStackTrace());
            }
            response = createResponseContainer(errorCode, errorMsg, responseData);
            return response.toString();
    }

    @Override
    public String findAllByUser(String iduser) {
        Integer id = Integer.parseInt(getMapFromJson(iduser).get("iduser"));
        int errorCode = 0;
        String errorMsg = "";
        JsonObject responseData = new JsonObject();
        JsonObject response = new JsonObject();
        try {
            List<Topic>topicList = service.findAllByUser(id);
            responseData = fillTopicData(topicList);
        }catch (Exception e){
            errorCode = ErrorCodes.INVALID_CREDENTIALS;
            errorMsg = "Getting topic error";
            logger.error("Getting topic error//" + e.getStackTrace());
        }
        response = createResponseContainer(errorCode, errorMsg, responseData);
        return response.toString();
    }
}
