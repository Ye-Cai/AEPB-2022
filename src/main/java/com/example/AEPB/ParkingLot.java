package com.example.AEPB;

import com.example.AEPB.exception.ParkingLotFullException;

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
        if (ticketCarMap.containsValue(car)) {
            throw new RuntimeException("the car has checked in");
        }
        Ticket ticket = new Ticket(this);
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car pick(Ticket ticket) {
        if (!ticketCarMap.containsKey(ticket)) {
            throw new RuntimeException("the ticket is invalid");
        }
        Car car = ticketCarMap.get(ticket);
        ticketCarMap.remove(ticket);
        return car;
    }

    public boolean isFull() {
        return ticketCarMap.size() == maxCapacity;
    }
}
