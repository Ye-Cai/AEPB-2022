package com.example.AEPB;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int maxCapacity;
    private final Map<Ticket, Car> ticketCarMap;

    public ParkingLot(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.ticketCarMap = new HashMap<>(maxCapacity);
    }

    public Ticket park(Car car) {
        if(ticketCarMap.size() >= maxCapacity){
            throw new RuntimeException("the parking lot is full");
        }
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car pick(Ticket ticket) {
        return ticketCarMap.get(ticket);
    }
}
