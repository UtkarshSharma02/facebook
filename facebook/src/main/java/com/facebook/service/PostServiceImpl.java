package com.facebook.service;

import com.facebook.entity.Post;
import com.facebook.entity.Status;
import com.facebook.exception.PostNotFoundException;
import com.facebook.exception.StatusNotFoundException;
import com.facebook.payload.PostDto;
import com.facebook.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    //this method will fetch all the post from database



    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        return optionalPost.map(this::mapEntityToDto).orElseThrow(()-> new PostNotFoundException("Post not found with Id: "+postId));
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapDtoToEntity(postDto);
        Post savedPost = postRepository.save(post);
        return mapEntityToDto(savedPost);
    }


    @Override
    public void deletePost(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            postRepository.deleteById(postId);
        } else {
            throw new PostNotFoundException("Post not found with ID: " + postId);
        }

    }

    private PostDto mapEntityToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }

    private Post mapDtoToEntity(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }
}
