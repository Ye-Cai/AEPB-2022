package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected ParkingLot findParkingLot() {
        return parkingLots.stream()
                          .filter(parkingLot -> !parkingLot.isFull())
                          .max(Comparator.comparingInt(ParkingLot::getEmptySize))
                          .orElseThrow(ParkingLotFullException::ofAll);
    }
}
