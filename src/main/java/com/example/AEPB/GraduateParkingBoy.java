package com.example.AEPB;

import java.util.List;

public class GraduateParkingBoy {
    private final List<ParkingLot> parkingLots;

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        return parkingLots.stream()
                          .dropWhile(ParkingLot::isFull)
                          .findFirst()
                          .map(parkingLot -> parkingLot.park(car))
                          .orElse(null);
    }

    public Car pick(Ticket ticket) {
        return null;
    }
}
