package com.utkuertunc.vf.controller;

import com.utkuertunc.vf.constants.SizeConstants;
import com.utkuertunc.vf.dto.StatusResponseDTO;
import com.utkuertunc.vf.model.base.Vehicle;
import com.utkuertunc.vf.dto.ParkRequestDTO;
import com.utkuertunc.vf.service.GarageService;
import com.utkuertunc.vf.service.impl.GarageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/*
    RestAPI controller for garage management
*/
@RestController("/garage")
public class GarageController {

    Logger logger = LoggerFactory.getLogger(GarageServiceImpl.class);

    //Storing each vehicle in array list for history of vehicles and business operations
    private List<Vehicle> allVehicles = new ArrayList<>();

    //Array with 10 index for garage
    private int[] garage = new int[SizeConstants.GARAGE_MAX_SIZE];

    //Array list for return type of status
    private List<StatusResponseDTO> statuses = new ArrayList<>();

    @Autowired
    private GarageService garageService;

    @PostMapping("/park")
    public synchronized ResponseEntity<ParkRequestDTO> park(@Valid @RequestBody ParkRequestDTO parkRequestDTO) {
        logger.info("park {} {} {}", parkRequestDTO.getVehiclePlate(), parkRequestDTO.getVehicleColour(), parkRequestDTO.getVehicleType());
        garageService.park(allVehicles, garage, parkRequestDTO);
        return new ResponseEntity<>(parkRequestDTO, HttpStatus.OK);
    }

    @DeleteMapping("/leave")
    public synchronized ResponseEntity<String> leave(@RequestParam int id){
        logger.info("leave {}", id);
        garageService.leave(garage, allVehicles, id-1);
        return new ResponseEntity<>("leaved vehicle: " + id, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<StatusResponseDTO>> status(){
        logger.info("status");
        garageService.status(allVehicles, statuses);
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }
}