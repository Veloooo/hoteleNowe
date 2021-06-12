package com.crud.hotels.ui;


import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.service.HotelService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;



@Route(value = "index")
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {

    private final HotelForm form;
    Grid<HotelDto> grid = new Grid<>(HotelDto.class);
    TextField filterText = new TextField();

    private HotelService service;

    public MainView(HotelService hotelService) {
        this.service = hotelService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureFilter();

        form = new HotelForm();
        Div hotel = new Div(grid, form);
        hotel.addClassName("content");
        hotel.setSizeFull();

        add(filterText, hotel);
        updateList();
    }

    private void configureFilter() {
        filterText.setPlaceholder("Filter by name");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    }

    private void configureGrid(){
        grid.addClassName("hotel-grid");
        grid.setSizeFull();
        grid.setColumns("name", "city", "country", "freeRooms", "totalRooms");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList(){
        grid.setItems(service.getAllHotelsWithFreeRooms(filterText.getValue()));
    }
}
