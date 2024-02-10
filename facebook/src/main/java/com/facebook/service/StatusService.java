package com.facebook.service;

import com.facebook.payload.StatusDto;

import java.util.List;

public interface StatusService {

    StatusDto createStatus(StatusDto statusDto);

    List<StatusDto> getAllStatuses();

    StatusDto getStatusById(Long statusID);

    void deleteStatusById(Long statusID);
}

