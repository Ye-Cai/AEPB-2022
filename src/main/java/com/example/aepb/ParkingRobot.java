package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;

import java.util.Comparator;
import java.util.List;

public class ParkingRobot implements ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public ParkingRobot(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
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
