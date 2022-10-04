package com.utkuertunc.vf.service;

import com.utkuertunc.vf.dto.ParkRequestDTO;
import com.utkuertunc.vf.dto.StatusResponseDTO;
import com.utkuertunc.vf.model.base.Vehicle;

import java.util.List;

public interface GarageService {

    ParkRequestDTO park(List<Vehicle> allVehicles, int[] garage, ParkRequestDTO parkRequestDTO);

    Vehicle leave(int[] garage, List<Vehicle> allVehicles, int id);

    List<StatusResponseDTO> status(List<Vehicle> allVehicles, List<StatusResponseDTO> statuses);
}
