package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;

import java.util.Comparator;
import java.util.List;

public class ParkingRobot extends ParkingBoy {

    public ParkingRobot(List<ParkingLot> parkingLots) {
        super( parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        parkingLots.forEach(lot -> lot.checkCarExistInParkingLot(car));
        return parkingLots.stream()
                          .filter(parkingLot -> !parkingLot.isFull())
                          .max(Comparator.comparingDouble(ParkingLot::getEmptyRate))
                          .map(parkingLot -> parkingLot.park(car))
                          .orElseThrow(ParkingLotFullException::ofAll);
    }

    @Override
    public Car pick(Ticket ticket) {
        return null;
    }
}
