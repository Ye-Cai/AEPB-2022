package com.example.aepb.pakingboy;

import com.example.aepb.Car;
import com.example.aepb.ParkingLot;
import com.example.aepb.Ticket;
import com.example.aepb.exception.TicketInvalidException;

import java.util.List;

public abstract class ParkingBoy {

    protected final List<ParkingLot> parkingLots;

    protected ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        parkingLots.forEach(lot -> lot.checkCarExistInParkingLot(car));
        ParkingLot parkingLot = findParkingLot();
        return parkingLot.park(car);
    }

    protected abstract ParkingLot findParkingLot();

    public Car pick(Ticket ticket) {
        return parkingLots.stream()
                          .filter(lot -> lot.isContainTicket(ticket))
                          .findFirst()
                          .map(lot -> lot.pick(ticket))
                          .orElseThrow(TicketInvalidException::new);
    }
}
