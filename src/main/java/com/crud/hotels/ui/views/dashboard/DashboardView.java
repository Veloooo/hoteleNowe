package com.crud.hotels.ui.views.dashboard;

import com.crud.hotels.backend.service.HotelService;
import com.crud.hotels.ui.MainLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Dashboard | Vaadin CRM")
@Route(value = "dashboard", layout = MainLayout.class)
public class DashboardView extends VerticalLayout {

    private final HotelService hotelService;

    public DashboardView(HotelService service) {
        this.hotelService = service;

        addClassName("dashboard-view");

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(
                getHotelStatus()
        );
    }

    private Span getHotelStatus() {
        Span stats = new Span(hotelService.getAllHotels().size() + "hotels");
        stats.addClassName("hotel-stats");
        return stats;
    }
}
