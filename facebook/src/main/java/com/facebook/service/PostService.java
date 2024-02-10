package com.facebook.service;

import com.facebook.payload.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto getPostById(Long postId);

    PostDto createPost(PostDto postDto);

    void deletePost(Long postId);
}

