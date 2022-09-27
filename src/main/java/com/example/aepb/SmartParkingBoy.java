package com.example.aepb;

import java.util.List;

public class SmartParkingBoy extends GraduateParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        ParkingLot emptyMaxParkingLot = parkingLots.stream().min((x, y) -> Integer.compare(y.getEmptySize(), x.getEmptySize()))
                .orElseThrow(() -> new RuntimeException("get emptyMaxParkingLot fail"));
        return emptyMaxParkingLot.park(car);
    }
}
