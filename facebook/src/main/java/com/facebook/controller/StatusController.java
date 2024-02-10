package com.facebook.controller;

import com.facebook.payload.StatusDto;
import com.facebook.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    // Endpoint to create a new status
    // http://localhost:8080/api/status
    @PostMapping
    public ResponseEntity<StatusDto> createStatus(@RequestBody StatusDto statusDto) {
        StatusDto createdStatus = statusService.createStatus(statusDto);
        return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
    }

    // Endpoint to get all statuses
    // http://localhost:8080/api/status
    @GetMapping
    public ResponseEntity<List<StatusDto>> getAllStatuses() {
        List<StatusDto> allStatuses = statusService.getAllStatuses();
        return new ResponseEntity<>(allStatuses, HttpStatus.OK);
    }

    // Endpoint to get a specific status by ID
    // http://localhost:8080/api/status/1
    @GetMapping("/{statusID}")
    public ResponseEntity<StatusDto> getStatusById(@PathVariable Long statusID) {
        StatusDto statusDto = statusService.getStatusById(statusID);
        return new ResponseEntity<>(statusDto, HttpStatus.OK);
    }

    // Endpoint to delete a status by ID
    // http://localhost:8080/api/status/1
    @DeleteMapping("/{statusID}")
    public ResponseEntity<Void> deleteStatusById(@PathVariable Long statusID) {
        statusService.deleteStatusById(statusID) ;
            return new ResponseEntity<>(HttpStatus.OK);
    }
}