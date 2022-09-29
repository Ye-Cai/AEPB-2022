package com.example.aepb;

import java.util.List;

public abstract class ParkingBoy {

    protected final List<ParkingLot> parkingLots;

    protected ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    abstract Ticket park(Car car);

    abstract Car pick(Ticket ticket);
}
