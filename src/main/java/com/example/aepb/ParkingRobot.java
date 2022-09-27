package com.example.aepb;

import java.util.Comparator;
import java.util.List;

public class ParkingRobot implements ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public ParkingRobot(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public Ticket park(Car car) {
        return parkingLots.stream()
                          .filter(parkingLot -> !parkingLot.isFull())
                          .max(Comparator.comparingDouble(ParkingLot::getEmptyRat))
                          .map(parkingLot -> parkingLot.park(car))
                          .orElse(null);
    }

    @Override
    public Car pick(Ticket ticket) {
        return null;
    }
}
