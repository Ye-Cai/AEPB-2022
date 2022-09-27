package com.example.aepb;

import java.util.List;

public class ParkingRobot implements ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public ParkingRobot(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public Ticket park(Car car) {
        return parkingLots.get(0).park(car);
    }

    @Override
    public Car pick(Ticket ticket) {
        return null;
    }
}
