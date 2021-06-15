package com.crud.hotels.backend.strategy;

public class LongTimeBooking implements BookingStrategy{
    @Override
    public double calculateDiscount() {
        return 0.4;
    }
}
