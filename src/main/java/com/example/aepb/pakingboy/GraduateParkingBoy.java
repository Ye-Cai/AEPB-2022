package com.example.aepb.pakingboy;

import com.example.aepb.ParkingLot;
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
