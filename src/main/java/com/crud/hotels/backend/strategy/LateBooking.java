package com.crud.hotels.backend.strategy;

public class LateBooking implements BookingStrategy {
    @Override
    public double calculateDiscount() {
        return 0.8;
    }
}
