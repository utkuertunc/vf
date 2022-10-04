package com.utkuertunc.vf.service.impl;

import com.utkuertunc.vf.constants.ErrorMessageConstants;
import com.utkuertunc.vf.constants.SizeConstants;
import com.utkuertunc.vf.dto.ParkRequestDTO;
import com.utkuertunc.vf.dto.StatusResponseDTO;
import com.utkuertunc.vf.dto.mapper.StatusResponseMapper;
import com.utkuertunc.vf.exception.AlreadyNotParkingException;
import com.utkuertunc.vf.model.Car;
import com.utkuertunc.vf.model.Jeep;
import com.utkuertunc.vf.model.Truck;
import com.utkuertunc.vf.model.base.Vehicle;
import com.utkuertunc.vf.service.CarService;
import com.utkuertunc.vf.service.GarageService;
import com.utkuertunc.vf.service.JeepService;
import com.utkuertunc.vf.service.TruckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    Base service class for each type of car.
    The role is check each type of vehicle then invoke their service
 */
@Service
public class GarageServiceImpl implements GarageService {

    Logger logger = LoggerFactory.getLogger(GarageServiceImpl.class);

    @Autowired
    CarService carService;

    @Autowired
    JeepService jeepService;

    @Autowired
    TruckService truckService;

    @Autowired
    StatusResponseMapper statusResponseMapper;

    @Override
    public ParkRequestDTO park(List<Vehicle> allVehicles, int[] garage, ParkRequestDTO parkRequestDTO) {
        if (carService.checkVehicleTypeForCar(parkRequestDTO)) {
            carService.parkCar(garage, allVehicles, parkRequestDTO);
            logger.info("Allocated {} slot", SizeConstants.CAR_MAX_SIZE);
        } else if (jeepService.checkVehicleTypeForJeep(parkRequestDTO)) {
            jeepService.parkJeep(garage, allVehicles, parkRequestDTO);
            logger.info("Allocated {} slots", SizeConstants.JEEP_MAX_SIZE);
        } else if (truckService.checkVehicleTypeForTruck(parkRequestDTO)) {
            truckService.parkTruck(garage, allVehicles, parkRequestDTO);
            logger.info("Allocated {} slots", SizeConstants.TRUCK_MAX_SIZE);
        }
        return parkRequestDTO;
    }

    @Override
    public Vehicle leave(int[] garage, List<Vehicle> allVehicles, int id) {
        if (!allVehicles.get(id).getIsParking()) {
            throw new AlreadyNotParkingException(ErrorMessageConstants.ALREADY_NOT_PARKING_MESSAGE);
        }

        if (allVehicles.get(id) instanceof Car) {
            carService.leaveCar(garage, allVehicles, id);
        }

        else if (allVehicles.get(id) instanceof Jeep) {
            jeepService.leaveJeep(garage, allVehicles, id);
        }

        if (allVehicles.get(id) instanceof Truck) {
            truckService.leaveTruck(garage, allVehicles, id);
        }
        return allVehicles.get(id);
    }

    @Override
    public List<StatusResponseDTO> status(List<Vehicle> allVehicles, List<StatusResponseDTO> statuses) {

        for (int i = 0; i < allVehicles.size(); i++) {
            if (allVehicles.get(i).getIsParking()) {
                StatusResponseDTO statusResponseDTO = new StatusResponseDTO();
                statuses.add(statusResponseMapper.vehicleToStatusResponseDTO(allVehicles, statusResponseDTO, i));
            }
        }
        logger.info("Status");
        return statuses;
    }
}
