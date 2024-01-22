package com.facebook.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Table(name="user")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private UUID userId;
    private String userName;
    private String userImageURL;
    private String activityStatus;
    private Date joinedDate;
}