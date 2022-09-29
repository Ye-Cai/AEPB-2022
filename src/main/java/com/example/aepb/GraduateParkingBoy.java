package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;

import java.util.List;

public class GraduateParkingBoy extends ParkingBoy {

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super( parkingLots);
    }

    @Override
    protected ParkingLot findParkingLot() {
        return parkingLots.stream()
                          .dropWhile(ParkingLot::isFull)
                          .findFirst()
                          .orElseThrow(ParkingLotFullException::ofAll);
    }
}
