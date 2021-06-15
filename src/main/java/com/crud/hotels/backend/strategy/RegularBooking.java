package com.crud.hotels.backend.strategy;

import com.crud.hotels.backend.domain.Hotel;

public class RegularBooking implements BookingStrategy {
    @Override
    public double calculateDiscount() {
        return 1.1;
    }
}
