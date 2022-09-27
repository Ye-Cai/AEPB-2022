package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;

import java.util.List;

public class ParkingRobot extends GraduateParkingBoy {

    public ParkingRobot(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        parkingLots.forEach(lot -> lot.checkCarExistInParkingLot(car));
        return parkingLots.stream()
                          .filter(parkingLot -> !parkingLot.isFull())
                          .min((parkingLotX, parkingLotY) ->
                              Integer.compare(parkingLotY.getEmptySize(), parkingLotX.getEmptySize()))
                          .map(parkingLot -> parkingLot.park(car))
                          .orElseThrow(ParkingLotFullException::ofAll);
    }
}
