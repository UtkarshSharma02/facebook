package com.facebook.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto{
    private Long statusID;
    private Long userID;
    private String statusImageURL;
    private Date dateTime;
}
