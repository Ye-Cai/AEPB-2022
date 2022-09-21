package com.example.AEPB;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int maxCapacity;
    private Map<Ticket, Car> ticketCarMap;

    public ParkingLot(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.ticketCarMap = new HashMap<>(maxCapacity);
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car pick(Ticket ticket) {
        return ticketCarMap.get(ticket);
    }
}
