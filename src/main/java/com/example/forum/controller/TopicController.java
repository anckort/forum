package com.example.forum.controller;

import com.example.forum.entity.Topic;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public interface TopicController {

    @PostMapping(value = "/addTopic")
    public String addTopic(@RequestBody String topicData);

    @GetMapping(value = "/findAllByUser")
    public String findAllByUser(@RequestBody String iduser);

}
