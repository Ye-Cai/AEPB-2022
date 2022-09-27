package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;
import com.example.aepb.exception.TicketInvalidException;

import java.util.List;

public class SmartParkingBoy implements ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public Ticket park(Car car) {
        parkingLots.forEach(lot -> lot.checkCarExistInParkingLot(car));
        return parkingLots.stream()
                          .filter(parkingLot -> !parkingLot.isFull())
                          .min((parkingLotX, parkingLotY) ->
                              Integer.compare(parkingLotY.getEmptySize(), parkingLotX.getEmptySize()))
                          .map(parkingLot -> parkingLot.park(car))
                          .orElseThrow(ParkingLotFullException::ofAll);
    }

    @Override
    public Car pick(Ticket ticket) {
        return parkingLots.stream()
                          .filter(lot -> lot.isContainTicket(ticket))
                          .findFirst()
                          .map(lot -> lot.pick(ticket))
                          .orElseThrow(TicketInvalidException::new);
    }
}
