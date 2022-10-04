package com.utkuertunc.vf.dto.mapper;

import com.utkuertunc.vf.dto.ParkRequestDTO;
import com.utkuertunc.vf.model.Car;
import com.utkuertunc.vf.model.Jeep;
import com.utkuertunc.vf.model.Truck;
import org.springframework.stereotype.Component;

/*
    Main object to DTO mapper for ParkRequestDTO
*/
@Component
public class VehicleMapper {

    public Car requestDTOToCar(ParkRequestDTO parkRequestDTO){
        Car car = new Car();
        car.setVehiclePlate(parkRequestDTO.getVehiclePlate());
        car.setVehicleColour(parkRequestDTO.getVehicleColour());
        car.setVehicleType(parkRequestDTO.getVehicleType());
        return car;
    }

    public Jeep requestDTOToJeep(ParkRequestDTO parkRequestDTO){
        Jeep jeep = new Jeep();
        jeep.setVehiclePlate(parkRequestDTO.getVehiclePlate());
        jeep.setVehicleColour(parkRequestDTO.getVehicleColour());
        jeep.setVehicleType(parkRequestDTO.getVehicleType());
        return jeep;
    }

    public Truck requestDTOToTruck(ParkRequestDTO parkRequestDTO){
        Truck truck = new Truck();
        truck.setVehiclePlate(parkRequestDTO.getVehiclePlate());
        truck.setVehicleColour(parkRequestDTO.getVehicleColour());
        truck.setVehicleType(parkRequestDTO.getVehicleType());
        return truck;
    }
}
