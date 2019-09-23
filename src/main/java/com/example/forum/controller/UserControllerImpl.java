package com.example.forum.controller;

import com.example.forum.constants.ErrorCodes;
import com.example.forum.entity.User;
import com.example.forum.logger.Logging;
import com.example.forum.service.UserService;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserControllerImpl implements UserController, Response{
    @Autowired
    UserService service;
    Logger logger = Logging.getLogger();

    @Override
    public String addUser(User user) {
        int errorCode = 0;
        String errorMsg = "";
        JsonObject responseData = new JsonObject();
        JsonObject response = new JsonObject();
        try {
            service.save(user);
            responseData = fillUserData(user);
        }catch (Exception e){
            errorCode = ErrorCodes.INVALID_CREDENTIALS;
            errorMsg = "Creation user error";
            logger.error("Creation user error//" + e.getStackTrace());
        }
        response = createResponseContainer(errorCode, errorMsg, responseData);
        return response.toString();
    }

    @Override
    public String authorisation(String loginData) {
        Map<String, String> loginDataMap = getMapFromJson(loginData);

        int errorCode = ErrorCodes.STATUS_OK;
        String errorMsg = "";
        JsonObject responseData = new JsonObject();
        JsonObject response = new JsonObject();
        try {
            User user = service.authorisation(loginDataMap.get("username"), loginDataMap.get("password"));
            if (user == null) {
                errorCode = ErrorCodes.INVALID_CREDENTIALS;
                errorMsg = "Incorrect authorisation data";
                logger.error("Incorrect authorisation data username - "+loginDataMap.get("username") + " password - " + loginDataMap.get("password"));
            }else{
                responseData = fillUserData(user);
            }

        }catch (Exception e){
            errorCode = ErrorCodes.INVALID_CREDENTIALS;
            errorMsg = "Authorisation error";
            logger.error("Authorisation error//" + e.getStackTrace());
        }
        response = createResponseContainer(errorCode,errorMsg,responseData);
        return response.toString();
    }
}
