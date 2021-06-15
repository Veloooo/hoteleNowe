package com.crud.hotels.backend.strategy;

public class RegularBooking implements BookingStrategy {
    @Override
    public double calculateDiscount() {
        return 1.1;
    }
}
