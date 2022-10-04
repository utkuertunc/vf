package com.utkuertunc.vf.dto.mapper;

import com.utkuertunc.vf.dto.StatusResponseDTO;
import com.utkuertunc.vf.model.Car;
import com.utkuertunc.vf.model.Jeep;
import com.utkuertunc.vf.model.Truck;
import com.utkuertunc.vf.model.base.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

/*
    DTO to main object mapper for StatusResponseDTO
*/
@Component
public class StatusResponseMapper {

    public StatusResponseDTO vehicleToStatusResponseDTO(List<Vehicle> allVehicles, StatusResponseDTO statusResponseDTO, int i) {
        statusResponseDTO.setVehiclePlate(allVehicles.get(i).getVehiclePlate());
        statusResponseDTO.setVehicleColour(allVehicles.get(i).getVehicleColour());

        if (allVehicles.get(i) instanceof Car) {
            statusResponseDTO.setParkedSlots(((Car) allVehicles.get(i)).getParkedSlots());
        }

        else if (allVehicles.get(i) instanceof Jeep) {
            statusResponseDTO.setParkedSlots(((Jeep) allVehicles.get(i)).getParkedSlots());
        }

        if (allVehicles.get(i) instanceof Truck) {
            statusResponseDTO.setParkedSlots(((Truck) allVehicles.get(i)).getParkedSlots());
        }
        return statusResponseDTO;
    }
}
