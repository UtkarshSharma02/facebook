package com.facebook.service;

import com.facebook.entity.Comment;
import com.facebook.entity.Post;
import com.facebook.exception.CommentNotFoundException;
import com.facebook.exception.PostNotFoundException;
import com.facebook.payload.CommentDto;
import com.facebook.repository.CommentRepository;
import com.facebook.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post not found with postId: " + postId)
        );

        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    @Override
    public List<CommentDto> retrieveCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findCommentsByPostId(postId);
        List<CommentDto> commentsDto = comments.stream().map(this::mapToDto).collect(Collectors.toList());
        return commentsDto;
    }

    @Override
    public CommentDto retrieveCommentByCommentId(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post not found with postId " + postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CommentNotFoundException("Comment not found with commentId " + commentId)
        );
        CommentDto commentDto = mapToDto(comment);
        return commentDto;
    }



    @Override
    public void deleteComment(long postId, long commentId) {
        postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post not found with postId:" + postId)
        );
        commentRepository.deleteById(commentId);
    }

    public CommentDto mapToDto(Comment savedComment) {
        return modelMapper.map(savedComment, CommentDto.class);
    }

    public Comment mapToEntity(CommentDto commentDto) {
        return modelMapper.map(commentDto, Comment.class);
    }
}


