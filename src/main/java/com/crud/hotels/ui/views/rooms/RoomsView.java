package com.crud.hotels.ui.views.rooms;


import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.dto.RoomDto;
import com.crud.hotels.backend.service.HotelService;
import com.crud.hotels.backend.service.RoomService;
import com.crud.hotels.ui.MainLayout;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
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
import org.apache.el.stream.Stream;

import java.util.List;
import java.util.stream.Collectors;

import static com.crud.hotels.ui.MainLayout.currentUser;


@Route(value = "rooms", layout = MainLayout.class)
@PageTitle("Rooms | Vaadin CRM")
@CssImport("./styles/shared-styles.css")
public class RoomsView extends VerticalLayout {

    private final RoomForm form;
    Grid<RoomDto> grid = new Grid<>(RoomDto.class);
    TextField name = new TextField();
    NumberField minGuestsNumber = new NumberField();
    NumberField maxGuestsNumber = new NumberField();
    NumberField pricePerNightMin = new NumberField();
    NumberField pricePerNightMax = new NumberField();
    ComboBox<HotelDto> hotel = new ComboBox<>();

    private RoomService roomService;
    private HotelService hotelService;

    public RoomsView(RoomService roomService, HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        getToolBar();

        form = new RoomForm(
                hotelService.getHotelsOwnedByUser(currentUser.getId())
        );
        form.addListener(RoomForm.SaveEvent.class, this::saveRoom);
        form.addListener(RoomForm.DeleteEvent.class, this::deleteRoom);
        form.addListener(RoomForm.CloseEvent.class, e -> closeEditor());
        form.addListener(RoomForm.BookEvent.class, this::bookRoom);
        Div hotel = new Div(grid, form);
        hotel.addClassName("content");
        hotel.setSizeFull();

        add(getToolBar(), hotel);
        updateList();

        closeEditor();
    }

    private void bookRoom(RoomForm.BookEvent evt) {
    }

    private void saveRoom(RoomForm.SaveEvent evt) {
        if (evt.getRoomDto().getId() == null) {
            RoomDto roomDto = evt.getRoomDto();
            roomService.createRoom(roomDto);
        } else
            roomService.editRoom(evt.getRoomDto().getId(), evt.getRoomDto());
        updateList();
        closeEditor();
    }

    private void deleteRoom(RoomForm.DeleteEvent evt) {
        roomService.deleteRoom(evt.getRoomDto().getId());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setRoomDto(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private HorizontalLayout getToolBar() {
        name.setPlaceholder("Filter by name");
        name.setClearButtonVisible(true);
        name.setValueChangeMode(ValueChangeMode.LAZY);
        name.addValueChangeListener(e -> updateList());
        name.setLabel("Name");

        minGuestsNumber.setPlaceholder("Min guests number");
        minGuestsNumber.setClearButtonVisible(true);
        minGuestsNumber.addValueChangeListener(e -> updateList());
        minGuestsNumber.setLabel("Min guests number");

        maxGuestsNumber.setPlaceholder("Max guests number");
        maxGuestsNumber.setClearButtonVisible(true);
        maxGuestsNumber.addValueChangeListener(e -> updateList());
        maxGuestsNumber.setLabel("Max guests number");

        pricePerNightMin.setPlaceholder("Set price per night min");
        pricePerNightMin.setClearButtonVisible(true);
        pricePerNightMin.addValueChangeListener(e -> updateList());
        pricePerNightMin.setLabel("Price min");

        pricePerNightMax.setPlaceholder("Set temp min");
        pricePerNightMax.setClearButtonVisible(true);
        pricePerNightMax.addValueChangeListener(e -> updateList());
        pricePerNightMax.setLabel("Price max");

        hotel.setPlaceholder("Select hotel");
        hotel.setClearButtonVisible(true);
        hotel.addValueChangeListener(e -> updateList());
        hotel.setLabel("Hotel");
        hotel.setItems(hotelService.getHotelsOwnedByUser(currentUser.getId()));
        hotel.setItemLabelGenerator(HotelDto::getName);

        Button addRoomButton = new Button("Add room", click -> addRoom());
        HorizontalLayout toolbar = null;
        if(currentUser.getRole().equals("ROLE_OWNER"))
            toolbar = new HorizontalLayout(name, minGuestsNumber, maxGuestsNumber, pricePerNightMin, pricePerNightMax, hotel, addRoomButton);
        else
            toolbar = new HorizontalLayout(name, minGuestsNumber, maxGuestsNumber, pricePerNightMin, pricePerNightMax, hotel);
        toolbar.expand(pricePerNightMax);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addRoom() {
        grid.asSingleSelect().clear();
        editRoom(new RoomDto());
    }

    private void configureGrid() {
        grid.addClassName("hotel-grid");
        grid.setSizeFull();
        grid.setColumns("name", "pricePerNight", "guestsNumber");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.addColumn(
                hotel -> hotel.getHotel().getName()
        ).setHeader("Hotel");

        if(currentUser.getRole().equals("ROLE_OWNER"))
            grid.asSingleSelect().addValueChangeListener(event -> editRoom(event.getValue()));
    }

    private void editRoom(RoomDto roomDto) {
        if (roomDto == null)
            closeEditor();
        else {
            form.setRoomDto(roomDto);
            form.setVisible(true);
            setClassName("editing");
        }
    }

    private void updateList() {
        if(currentUser.getRole().equals("ROLE_OWNER"))
            grid.setItems(hotelService.getHotelsOwnedByUser(currentUser.getId())
                    .stream()
                    .map(hotelDto -> roomService.getAllRoomsInHotel(Long.valueOf(hotelDto.getId())))
                    .flatMap(List::stream)
                    .collect(Collectors.toList()));
        else
            grid.setItems(roomService.findAll());
    }
}
