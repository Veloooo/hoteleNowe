package com.crud.hotels.ui.views.reservations;


import com.crud.hotels.backend.dto.ReservationDto;
import com.crud.hotels.backend.dto.RoomDto;
import com.crud.hotels.backend.service.ReservationService;
import com.crud.hotels.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import static com.crud.hotels.ui.MainLayout.currentUser;


@Route(value = "reservations", layout = MainLayout.class)
@PageTitle("Reservations | Vaadin CRM")
@CssImport("./styles/shared-styles.css")
public class ReservationsView extends VerticalLayout {

    private final ReservationForm form;
    Grid<RoomDto> grid = new Grid<>(RoomDto.class);
    TextField filterText = new TextField();
    DatePicker dateFrom = new DatePicker();
    DatePicker dateTo = new DatePicker();
    NumberField tempMin = new NumberField();
    NumberField tempMax = new NumberField();
    NumberField priceMin = new NumberField();
    NumberField priceMax = new NumberField();

    private ReservationService reservationService;

    public ReservationsView(ReservationService reservationService) {
        this.reservationService = reservationService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        getToolBar();

        form = new ReservationForm();
        //form.addListener(ReservationForm.SaveEvent.class, this::saveReservation);
        form.addListener(ReservationForm.DeleteEvent.class, this::deleteReservation);
        form.addListener(ReservationForm.CloseEvent.class, e -> closeEditor());
        Div hotel = new Div(grid, form);
        hotel.addClassName("content");
        hotel.setSizeFull();

        add(getToolBar(), hotel);
        updateList();

        closeEditor();
    }


    private void saveReservation(ReservationForm.SaveEvent evt) {
        if (evt.getReservation().getId() == null) {
            ReservationDto reservationDto = evt.getReservation();
            reservationService.createReservation(reservationDto);
        } //else
           // hotelService.editHotel(Long.valueOf(evt.getHotel().getId()), evt.getHotel());
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

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by name");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
        filterText.setLabel("Name");

        dateFrom.setPlaceholder("Set date from");
        dateFrom.setClearButtonVisible(true);
        dateFrom.addValueChangeListener(e -> updateList());
        dateFrom.setLabel("Visit from");

        dateTo.setPlaceholder("Set date to");
        dateTo.setClearButtonVisible(true);
        dateTo.addValueChangeListener(e -> updateList());
        dateTo.setLabel("Visit to");

        tempMin.setPlaceholder("Set temp min");
        tempMin.setClearButtonVisible(true);
        tempMin.addValueChangeListener(e -> updateList());
        tempMin.setLabel("Temp min");

        tempMax.setPlaceholder("Set temp max");
        tempMax.setClearButtonVisible(true);
        tempMax.addValueChangeListener(e -> updateList());
        tempMax.setLabel("Temp max");

        priceMin.setPlaceholder("Set price min");
        priceMin.setClearButtonVisible(true);
        priceMin.addValueChangeListener(e -> updateList());
        priceMin.setLabel("Price min");

        priceMax.setPlaceholder("Set price max");
        priceMax.setClearButtonVisible(true);
        priceMax.addValueChangeListener(e -> updateList());
        priceMax.setLabel("Price max");

        Button addHotelButton = new Button("Add hotel", click -> addHotel());
        HorizontalLayout toolbar = null;
        if(currentUser.getRole().equals("ROLE_OWNER"))
            toolbar = new HorizontalLayout(filterText, dateFrom, dateTo, tempMin, tempMax, priceMin, priceMax, addHotelButton);
        else
            toolbar = new HorizontalLayout(filterText, dateFrom, dateTo, tempMin, tempMax, priceMin, priceMax);
        toolbar.expand(tempMax);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addHotel() {
        grid.asSingleSelect().clear();
        editHotel(new ReservationDto());
    }

    private void configureGrid() {
        grid.addClassName("hotel-grid");
        grid.setSizeFull();
        grid.setColumns("name", "city", "country", "freeRooms", "totalRooms");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

       // grid.asSingleSelect().addValueChangeListener(event -> editHotel(event.getValue()));
    }

    private void editHotel(ReservationDto reservationDto) {
        if (reservationDto == null)
            closeEditor();
        else {
            form.setReservationDto(reservationDto);
            form.setVisible(true);
            setClassName("editing");
        }
    }

    private void updateList() {
      //  grid.setItems(hotelService.getAllHotelsWithFreeRooms(filterText.getValue()));
    }
}
