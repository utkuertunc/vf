package com.utkuertunc.vf.service.impl;

import com.utkuertunc.vf.constants.ErrorMessageConstants;
import com.utkuertunc.vf.constants.SizeConstants;
import com.utkuertunc.vf.dto.ParkRequestDTO;
import com.utkuertunc.vf.dto.mapper.VehicleMapper;
import com.utkuertunc.vf.exception.NoAvailableSlotException;
import com.utkuertunc.vf.model.Car;
import com.utkuertunc.vf.model.base.Vehicle;
import com.utkuertunc.vf.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    Logger logger = LoggerFactory.getLogger(GarageServiceImpl.class);

    @Autowired
    VehicleMapper vehicleMapper;

    //After detect the available slot, put the car in garage and set 99 next slot. (99 is number of holding slot)
    @Override
    public void parkCar(int[] garage, List<Vehicle> allVehicles, ParkRequestDTO parkRequestDTO) {
        int availableSlot = detectAvailableSlotForCar(garage);
        garage[availableSlot] = availableSlot + 1;
        garage[availableSlot + 1] = 99;

        int[] parkedSlotsForCar = new int[1];
        parkedSlotsForCar[0] = availableSlot + 1;

        addCarToList(allVehicles, parkRequestDTO, parkedSlotsForCar);
    }

    @Override
    public boolean checkVehicleTypeForCar(ParkRequestDTO parkRequestDTO) {
        return parkRequestDTO.getVehicleType().equals("Car");
    }

    //Release old car slot and holding slot
    @Override
    public void leaveCar(int[] garage, List<Vehicle> allVehicles, int id) {
        int[] tempArrayForLeave = ((Car) allVehicles.get(id)).getParkedSlots();
        garage[tempArrayForLeave[0] - 1] = 0;
        garage[tempArrayForLeave[0]] = 0;

        allVehicles.get(id).setIsParking(false);
    }

    private int detectAvailableSlotForCar(int[] garage) {
        for (int i = 0; i < garage.length; i++) {
            if (garage[i] == 0 && garage[i + 1] == 0) {
                return i;
            }
        }

        logger.info("Garage is full");
        throw new NoAvailableSlotException(ErrorMessageConstants.NO_AVAILABLE_SLOTS_MESSAGE);
    }

    private void addCarToList(List<Vehicle> allVehicles, ParkRequestDTO parkRequestDTO, int[] parkedSlotsForCar){
        if (checkVehicleTypeForCar(parkRequestDTO)) {
            Car car = vehicleMapper.requestDTOToCar(parkRequestDTO);
            car.setParkedSlots(parkedSlotsForCar);
            allVehicles.add(car);
        }
    }
}
