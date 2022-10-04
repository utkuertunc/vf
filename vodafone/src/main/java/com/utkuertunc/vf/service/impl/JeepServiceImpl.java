package com.utkuertunc.vf.service.impl;

import com.utkuertunc.vf.constants.ErrorMessageConstants;
import com.utkuertunc.vf.constants.SizeConstants;
import com.utkuertunc.vf.dto.ParkRequestDTO;
import com.utkuertunc.vf.dto.mapper.VehicleMapper;
import com.utkuertunc.vf.exception.NoAvailableSlotException;
import com.utkuertunc.vf.model.Jeep;
import com.utkuertunc.vf.model.base.Vehicle;
import com.utkuertunc.vf.service.JeepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JeepServiceImpl implements JeepService {

    Logger logger = LoggerFactory.getLogger(GarageServiceImpl.class);

    @Autowired
    VehicleMapper vehicleMapper;

    //After detect the available slots, put the jeep in garage and set 99 next slot. (99 is number of holding slot)
    @Override
    public void parkJeep(int[] garage, List<Vehicle> allVehicles, ParkRequestDTO parkRequestDTO) {
        int availableSlot = detectAvailableSlotForJeep(garage);
        garage[availableSlot] = availableSlot + 1;
        garage[availableSlot + 1] = availableSlot + 2;
        garage[availableSlot + 2] = 99;

        int[] parkedSlotsForJeep = new int[2];
        parkedSlotsForJeep[0] = availableSlot + 1;
        parkedSlotsForJeep[1] = availableSlot + 2;

        addJeepToList(allVehicles, parkRequestDTO, parkedSlotsForJeep);
    }

    @Override
    public boolean checkVehicleTypeForJeep(ParkRequestDTO parkRequestDTO) {
        return parkRequestDTO.getVehicleType().equals("Jeep");
    }

    //Release old jeep slots and holding slot
    @Override
    public void leaveJeep(int[] garage, List<Vehicle> allVehicles, int id) {
        int[] tempArrayForLeave = ((Jeep) allVehicles.get(id)).getParkedSlots();
        garage[tempArrayForLeave[0] - 1] = 0;
        garage[tempArrayForLeave[0]] = 0;
        garage[tempArrayForLeave[0] + 1] = 0;

        allVehicles.get(id).setIsParking(false);
    }

    private int detectAvailableSlotForJeep(int[] garage) {
        for (int i = 0; i < garage.length; i++) {
            if (garage[i] == 0 && garage[i + 1] == 0 && garage[i + 2] == 0) {
                return i;
            }
        }

        logger.info("Garage is full");
        throw new NoAvailableSlotException(ErrorMessageConstants.NO_AVAILABLE_SLOTS_MESSAGE);
    }

    private void addJeepToList(List<Vehicle> allVehicles, ParkRequestDTO parkRequestDTO, int[] parkedSlotsForJeep){
        if (checkVehicleTypeForJeep(parkRequestDTO)) {
            Jeep jeep = vehicleMapper.requestDTOToJeep(parkRequestDTO);
            jeep.setParkedSlots(parkedSlotsForJeep);
            allVehicles.add(jeep);
        }
    }

}
