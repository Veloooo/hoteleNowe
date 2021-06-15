package com.crud.hotels.backend.strategy;

import com.crud.hotels.backend.domain.Hotel;

public class LateBooking implements BookingStrategy {
    @Override
    public double calculateDiscount() {
        return 0.8;
    }
}
