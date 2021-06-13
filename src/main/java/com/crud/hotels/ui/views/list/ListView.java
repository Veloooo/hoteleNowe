package com.crud.hotels.ui.views.list;


import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.service.HotelService;
import com.crud.hotels.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@Route(value = "index", layout = MainLayout.class)
@PageTitle("Hotels | Vaadin CRM")
@CssImport("./styles/shared-styles.css")
public class ListView extends VerticalLayout {

    private final HotelForm form;
    Grid<HotelDto> grid = new Grid<>(HotelDto.class);
    TextField filterText = new TextField();

    private HotelService hotelService;

    public ListView(HotelService hotelService) {
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
        if(evt.getHotel().getId() == null) {
            HotelDto hotelDto = evt.getHotel();
            hotelDto.setFreeRooms(0);
            hotelDto.setTotalRooms(0);
            hotelService.createHotel(hotelDto);
        }
        else
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

        Button addHotelButton = new Button("Add hotel", click -> addHotel());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addHotelButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addHotel() {
        grid.asSingleSelect().clear();
        editHotel(new HotelDto());
    }

    private void configureGrid(){
        grid.addClassName("hotel-grid");
        grid.setSizeFull();
        grid.setColumns("name", "city", "country", "freeRooms", "totalRooms");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> editHotel(event.getValue()));
    }

    private void editHotel(HotelDto hotel) {
        if(hotel == null)
            closeEditor();
        else {
            form.setHotelDto(hotel);
            form.setVisible(true);
            setClassName("editing");
        }
    }

    private void updateList(){
        grid.setItems(hotelService.getAllHotelsWithFreeRooms(filterText.getValue()));
    }
}
