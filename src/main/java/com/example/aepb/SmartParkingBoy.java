package com.example.aepb;

import java.util.List;

public class SmartParkingBoy extends GraduateParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return super.park(car);
    }
}
