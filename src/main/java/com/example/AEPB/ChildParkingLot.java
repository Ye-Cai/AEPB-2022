package com.example.AEPB;


import java.util.Objects;

public class ChildParkingLot {
    private final int order;
    private final String name;
    private final int size = 100;


    public ChildParkingLot(int order, String name) {
        this.order = order;
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildParkingLot that = (ChildParkingLot) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
