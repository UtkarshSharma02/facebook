package com.facebook.service;

import com.facebook.entity.Status;
import com.facebook.exception.StatusNotFoundException;
import com.facebook.payload.StatusDto;
import com.facebook.repository.StatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository, ModelMapper modelMapper) {
        this.statusRepository = statusRepository;
        this.modelMapper = modelMapper;
    }



    // this will create status

    public void test(){
        System.out.println("ok");
    }
    @Override
    public StatusDto createStatus(StatusDto statusDto) {
        Status status = convertToEntity(statusDto);
        Status savedStatus = statusRepository.save(status);
        return convertToDto(savedStatus);
    }

    @Override
    public List<StatusDto> getAllStatuses() {
        List<Status> allStatuses = statusRepository.findAll();
        return allStatuses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StatusDto getStatusById(Long statusID) {
        Optional<Status> optionalStatus = statusRepository.findById(statusID);
        return optionalStatus.map(this::convertToDto).orElseThrow(()->new StatusNotFoundException("Status not found with this ID:"+statusID));
    }

    @Override
    public void deleteStatusById(Long statusID) {
        Optional<Status> optionalStatus = statusRepository.findById(statusID);
        if (optionalStatus.isPresent()) {
            statusRepository.deleteById(statusID);
        } else {
            throw new StatusNotFoundException("Status not found with ID: " + statusID);
        }
    }



    private StatusDto convertToDto(Status status) {
        return modelMapper.map(status, StatusDto.class);
    }

    private Status convertToEntity(StatusDto statusDto) {
        return modelMapper.map(statusDto, Status.class);
    }
}

