package com.crud.hotels.ui.views.hotels;


import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.service.HotelService;
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


@Route(value = "hotels", layout = MainLayout.class)
@PageTitle("Hotels | Vaadin CRM")
@CssImport("./styles/shared-styles.css")
public class HotelsView extends VerticalLayout {

    private final HotelForm form;
    Grid<HotelDto> grid = new Grid<>(HotelDto.class);
    TextField filterText = new TextField();
    DatePicker dateFrom = new DatePicker();
    DatePicker dateTo = new DatePicker();
    NumberField tempMin = new NumberField();
    NumberField tempMax = new NumberField();
    NumberField priceMin = new NumberField();
    NumberField priceMax = new NumberField();
    private HotelService hotelService;

    public HotelsView(HotelService hotelService) {
        this.hotelService = hotelService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        getToolBar();

        form = new HotelForm();
        form.addListener(HotelForm.SaveEvent.class, this::saveHotel);
        form.addListener(HotelForm.DeleteEvent.class, this::deleteHotel);
        form.addListener(HotelForm.CloseEvent.class, e -> closeEditor());
        Div hotel = new Div(grid, form);
        hotel.addClassName("content");
        hotel.setSizeFull();

        add(getToolBar(), hotel);
        updateList();

        closeEditor();
    }


    private void saveHotel(HotelForm.SaveEvent evt) {
        if (evt.getHotel().getId() == null) {
            HotelDto hotelDto = evt.getHotel();
            hotelDto.setFreeRooms(0);
            hotelDto.setTotalRooms(0);
            hotelService.createHotel(hotelDto);
        } else
            hotelService.editHotel(Long.valueOf(evt.getHotel().getId()), evt.getHotel());
        updateList();
        closeEditor();
    }

    private void deleteHotel(HotelForm.DeleteEvent evt) {
        hotelService.deleteHotel(Long.valueOf(evt.getHotel().getId()));
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setHotelDto(null);
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
        editHotel(new HotelDto());
    }

    private void configureGrid() {
        grid.addClassName("hotel-grid");
        grid.setSizeFull();
        grid.setColumns("name", "city", "country", "freeRooms", "totalRooms");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> editHotel(event.getValue()));
    }

    private void editHotel(HotelDto hotel) {
        if (hotel == null)
            closeEditor();
        else {
            form.setHotelDto(hotel);
            form.setVisible(true);
            setClassName("editing");
        }
    }

    private void updateList() {
        grid.setItems(hotelService.getAllHotelsWithFreeRooms(filterText.getValue()));
    }
}