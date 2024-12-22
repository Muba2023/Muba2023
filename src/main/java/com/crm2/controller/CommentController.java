package com.crm2.controller;

import com.crm2.entity.Comment;
import com.crm2.entity.Post;
import com.crm2.repository.CommentRepository;
import com.crm2.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    public CommentController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
    @PostMapping()
    public String createComment(
            @RequestBody Comment comment,
            @RequestParam long postId
    ){
        Post post = postRepository.findById(postId).get();
        comment.setPost(post);
        commentRepository.save(comment);
        return "Comment created successfully";
    }
}
