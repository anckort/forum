package com.example.forum.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public interface CommentController {
    @PostMapping("/addComment")
    String addComment(@RequestBody String commentData);
    @GetMapping("findAllByTopic")
    String findAllByTopic(@RequestBody String topicId);
}
