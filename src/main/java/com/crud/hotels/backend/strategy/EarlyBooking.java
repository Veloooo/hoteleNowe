package com.crud.hotels.backend.strategy;

public class EarlyBooking implements BookingStrategy {

    @Override
    public double calculateDiscount() {
        return 0.7;
    }
}
