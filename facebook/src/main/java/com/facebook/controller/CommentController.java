package com.facebook.controller;

import com.facebook.payload.CommentDto;
import com.facebook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // http://localhost:8080/api/post/1/comments
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId, @RequestBody CommentDto commentDto) {
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/post/1/comments
    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<CommentDto>> retrieveCommentsByPostId(@PathVariable("postId") long postId) {
        List<CommentDto> commentsDto = commentService.retrieveCommentsByPostId(postId);
        return new ResponseEntity<>(commentsDto, HttpStatus.OK);
    }

    // http://localhost:8080/api/post/1/comments/2
    @GetMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> retrieveCommentByCommentId(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId) {
        CommentDto commentDto = commentService.retrieveCommentByCommentId(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }


    @DeleteMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment has been deleted.", HttpStatus.OK);
    }
}


