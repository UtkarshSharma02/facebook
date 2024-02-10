package com.facebook.service;

import com.facebook.payload.CommentDto;

import java.util.List;

public interface CommentService {
    public CommentDto createComment(long postId, CommentDto commentDto);
    public List<CommentDto> retrieveCommentsByPostId(long postId);
    public CommentDto retrieveCommentByCommentId(long postId, long commentId);
    public void deleteComment(long postId, long commentId);
}


