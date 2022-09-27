package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;
import com.example.aepb.exception.TicketInvalidException;

import java.util.List;

public class GraduateParkingBoy {
    protected final List<ParkingLot> parkingLots;

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        parkingLots.forEach(lot -> lot.checkCarExistInParkingLot(car));
        return parkingLots.stream()
                          .dropWhile(ParkingLot::isFull)
                          .findFirst()
                          .map(parkingLot -> parkingLot.park(car))
                          .orElseThrow(ParkingLotFullException::ofAll);
    }

    public Car pick(Ticket ticket) {
        String parkingLotName = ticket.getParkingLotName();
        return parkingLots.stream()
                          .filter(lot -> lot.getName().equals(parkingLotName))
                          .findFirst()
                          .map(lot -> lot.pick(ticket))
                          .orElseThrow(TicketInvalidException::new);
    }
}
