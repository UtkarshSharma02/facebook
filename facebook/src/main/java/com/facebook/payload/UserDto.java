package com.facebook.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID userId;
    private String userName;
    private String userImageURL;
    private String activityStatus;
    private Date joinedDate;
}
