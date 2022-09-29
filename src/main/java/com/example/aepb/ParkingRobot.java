package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;

import java.util.Comparator;
import java.util.List;

public class ParkingRobot {
    private final List<ParkingLot> parkingLots;

    public ParkingRobot(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        parkingLots.forEach(lot -> lot.checkCarExistInParkingLot(car));
        ParkingLot parkingLot = findParkingLot();
        return parkingLot.park(car);
    }

    private ParkingLot findParkingLot() {
        return parkingLots.stream()
                          .filter(parkingLot -> !parkingLot.isFull())
                          .max(Comparator.comparingDouble(ParkingLot::getEmptyRate))
                          .orElseThrow(ParkingLotFullException::ofAll);
    }
}
