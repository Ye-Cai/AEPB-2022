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
                          .orElseThrow(() -> new RuntimeException("all parking lot is full"));
    }

    public Car pick(Ticket ticket) {
        String parkingLotName = ticket.getParkingLotName();
        return parkingLots.stream()
                          .filter(lot -> lot.getName().equals(parkingLotName))
                          .findFirst().map(lot -> lot.pick(ticket)).orElse(null);
    }
}
