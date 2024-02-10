package com.facebook.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long postId;
    private Long userId;
    private String username;
    private String description;
    private String postImageURL;
    private int likes;
    private Date dateTime;
}
