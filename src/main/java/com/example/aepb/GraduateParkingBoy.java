package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;
import com.example.aepb.exception.TicketInvalidException;

import java.util.List;

public class GraduateParkingBoy extends ParkingBoy {

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super( parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        parkingLots.forEach(lot -> lot.checkCarExistInParkingLot(car));
        return parkingLots.stream()
                          .dropWhile(ParkingLot::isFull)
                          .findFirst()
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
