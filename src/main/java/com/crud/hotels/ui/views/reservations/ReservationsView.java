package com.crud.hotels.ui.views.reservations;


import com.crud.hotels.backend.dto.ReservationDto;
import com.crud.hotels.backend.service.ReservationService;
import com.crud.hotels.ui.MainLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import static com.crud.hotels.ui.MainLayout.currentUser;


@Route(value = "reservations", layout = MainLayout.class)
@PageTitle("Reservations | Vaadin CRM")
@CssImport("./styles/shared-styles.css")
public class ReservationsView extends VerticalLayout {

    private final ReservationForm form;
    Grid<ReservationDto> grid = new Grid<>(ReservationDto.class);

    private ReservationService reservationService;

    public ReservationsView(ReservationService reservationService) {
        this.reservationService = reservationService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new ReservationForm();
        form.addListener(ReservationForm.SaveEvent.class, this::saveReservation);
        form.addListener(ReservationForm.DeleteEvent.class, this::deleteReservation);
        form.addListener(ReservationForm.CloseEvent.class, e -> closeEditor());
        Div reservations = new Div(grid, form);
        reservations.addClassName("content");
        reservations.setSizeFull();

        add(reservations);
        updateList();

        closeEditor();
    }


    private void saveReservation(ReservationForm.SaveEvent evt) {
        ReservationDto reservationDto = evt.getReservation();
        reservationService.updateReservation(reservationDto);
        updateList();
        closeEditor();
    }

    private void deleteReservation(ReservationForm.DeleteEvent evt) {
        reservationService.deleteReservation(evt.getReservation().getId());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setReservationDto(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void configureGrid() {
        grid.addClassName("hotel-grid");
        grid.setSizeFull();
        grid.setColumns("dateFrom", "dateTo", "daysTotal", "priceTotal", "paid");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.addColumn(h -> h.getRoom().getHotel().getName()).setHeader("Hotel");
        grid.addColumn(h -> h.getRoom().getName()).setHeader("Room");

        grid.asSingleSelect().addValueChangeListener(event -> editReservation(event.getValue()));
    }

    private void editReservation(ReservationDto reservationDto) {
        if (reservationDto == null)
            closeEditor();
        else {
            form.setReservationDto(reservationDto);
            form.setVisible(true);
            setClassName("editing");
        }
    }

    private void updateList() {
        grid.setItems(reservationService.getAllUserReservations(currentUser));
    }
}
