package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;

import java.util.List;

public class SmartParkingBoy extends GraduateParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return parkingLots.stream()
                          .filter(parkingLot -> !parkingLot.isFull())
                          .min((parkingLotX, parkingLotY) ->
                              Integer.compare(parkingLotY.getEmptySize(), parkingLotX.getEmptySize()))
                          .map(parkingLot -> parkingLot.park(car))
                          .orElseThrow(ParkingLotFullException::ofAll);
    }
}
