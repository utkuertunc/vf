package com.utkuertunc.vf.service.impl;

import com.utkuertunc.vf.constants.ErrorMessageConstants;
import com.utkuertunc.vf.constants.SizeConstants;
import com.utkuertunc.vf.dto.ParkRequestDTO;
import com.utkuertunc.vf.dto.mapper.VehicleMapper;
import com.utkuertunc.vf.exception.NoAvailableSlotException;
import com.utkuertunc.vf.model.Truck;
import com.utkuertunc.vf.model.base.Vehicle;
import com.utkuertunc.vf.service.TruckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TruckServiceImpl implements TruckService {

    Logger logger = LoggerFactory.getLogger(GarageServiceImpl.class);

    @Autowired
    VehicleMapper vehicleMapper;

    @Override
    public void parkTruck(int[] garage, List<Vehicle> allVehicles, ParkRequestDTO parkRequestDTO) {
        int availableSlot = detectAvailableSlotForTruck(garage);
        garage[availableSlot] = availableSlot + 1;
        garage[availableSlot + 1] = availableSlot + 2;
        garage[availableSlot + 2] = availableSlot + 3;
        garage[availableSlot + 3] = availableSlot + 4;
        garage[availableSlot + 4] = 99;

        int[] parkedSlotsForTruck = new int[4];
        parkedSlotsForTruck[0] = availableSlot + 1;
        parkedSlotsForTruck[1] = availableSlot + 2;
        parkedSlotsForTruck[2] = availableSlot + 3;
        parkedSlotsForTruck[3] = availableSlot + 4;

        addTruckToList(allVehicles, parkRequestDTO, parkedSlotsForTruck);
    }

    @Override
    public boolean checkVehicleTypeForTruck(ParkRequestDTO parkRequestDTO) {
        return parkRequestDTO.getVehicleType().equals("Truck");
    }

    //After detect the available slots, put the truck in garage and set 99 next slot. (99 is number of holding slot)
    @Override
    public void leaveTruck(int[] garage, List<Vehicle> allVehicles, int id) {
        int[] tempArrayForLeave = ((Truck) allVehicles.get(id)).getParkedSlots();
        garage[tempArrayForLeave[0] - 1] = 0;
        garage[tempArrayForLeave[0]] = 0;
        garage[tempArrayForLeave[0] + 1] = 0;
        garage[tempArrayForLeave[0] + 2] = 0;
        garage[tempArrayForLeave[0] + 3] = 0;

        allVehicles.get(id).setIsParking(false);
    }

    //Release old truck slots and holding slot
    private int detectAvailableSlotForTruck(int[] garage) {
        for (int i = 0; i < garage.length; i++) {
            if (garage[i] == 0 && garage[i + 1] == 0 && garage[i + 2] == 0 && garage[i + 3] == 0 && garage[i + 4] == 0) {
                return i;
            }
        }

        logger.info("Garage is full");
        throw new NoAvailableSlotException(ErrorMessageConstants.NO_AVAILABLE_SLOTS_MESSAGE);
    }

    private void addTruckToList(List<Vehicle> allVehicles, ParkRequestDTO parkRequestDTO, int[] parkedSlotsForTruck) {
        if (checkVehicleTypeForTruck(parkRequestDTO)) {
            Truck truck = vehicleMapper.requestDTOToTruck(parkRequestDTO);
            truck.setParkedSlots(parkedSlotsForTruck);
            allVehicles.add(truck);
        }
    }

}
