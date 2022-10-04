package com.utkuertunc.vf.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusResponseDTO {

    private String vehiclePlate;

    private String vehicleColour;

    private int[] parkedSlots;
}
