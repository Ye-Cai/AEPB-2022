package com.example.AEPB;

import com.example.AEPB.exception.CarHasCheckedInException;
import com.example.AEPB.exception.ParkingLotFullException;
import com.example.AEPB.exception.TicketInvalidException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int maxCapacity;
    private final Map<Ticket, Car> ticketCarMap;
    private final String name;

    public ParkingLot(int maxCapacity, String name) {
        this.maxCapacity = maxCapacity;
        this.ticketCarMap = new HashMap<>(maxCapacity);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Ticket park(Car car) {
        if (ticketCarMap.size() >= maxCapacity) {
            throw ParkingLotFullException.ofOne();
        }
        checkCarExistInParkingLot(car);
        Ticket ticket = new Ticket(this);
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public void checkCarExistInParkingLot(Car car) {
        if (ticketCarMap.containsValue(car)) {
            throw new CarHasCheckedInException();
        }
    }

    public Car pick(Ticket ticket) {
        if (!ticketCarMap.containsKey(ticket)) {
            throw new TicketInvalidException();
        }
        Car car = ticketCarMap.get(ticket);
        ticketCarMap.remove(ticket);
        return car;
    }

    public boolean isFull() {
        return ticketCarMap.size() == maxCapacity;
    }
}
