package com.example.aepb;

import lombok.Getter;

import java.util.Optional;

@Getter
public class Ticket {
    private final String parkingLotName;

    public Ticket(ParkingLot parkingLot) {
        this.parkingLotName = Optional.ofNullable(parkingLot)
                                      .map(ParkingLot::getName)
                                      .orElse(null);
    }
}
