package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;
import com.example.aepb.exception.TicketInvalidException;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        parkingLots.forEach(lot -> lot.checkCarExistInParkingLot(car));
        return parkingLots.stream()
                          .filter(parkingLot -> !parkingLot.isFull())
                          .max(Comparator.comparingInt(ParkingLot::getEmptySize))
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
