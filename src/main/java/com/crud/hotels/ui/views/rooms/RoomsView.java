package com.crud.hotels.ui.views.rooms;


import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.dto.ReservationDto;
import com.crud.hotels.backend.dto.RoomDto;
import com.crud.hotels.backend.service.HotelService;
import com.crud.hotels.backend.service.ReservationService;
import com.crud.hotels.backend.service.RoomService;
import com.crud.hotels.ui.MainLayout;
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
    NumberField guestsNumber = new NumberField();
    NumberField pricePerNight = new NumberField();
    DatePicker dateFrom = new DatePicker();
    DatePicker dateTo = new DatePicker();
    NumberField tempMin = new NumberField();
    ComboBox<HotelDto> hotel = new ComboBox<>();

    private RoomService roomService;
    private HotelService hotelService;
    private ReservationService reservationService;
    private List<HotelDto> hotelsDisplayed;

    public RoomsView(RoomService roomService, HotelService hotelService, ReservationService reservationService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
        this.reservationService = reservationService;
        if ("ROLE_OWNER".equals(currentUser.getRole()))
            hotelsDisplayed = hotelService.getHotelsOwnedByUser(currentUser.getLogin());
        else hotelsDisplayed = hotelService.getAllHotels();
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        getToolBar();

        form = new RoomForm(
                hotelsDisplayed
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
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setDateFrom(dateFrom.getValue());
        reservationDto.setDateTo(dateTo.getValue());
        reservationDto.setRoom(evt.getRoomDto());
        reservationDto.setUser(currentUser);
        reservationService.createReservation(reservationDto);
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

        dateFrom.setPlaceholder("Date from");
        dateFrom.setClearButtonVisible(true);
        dateFrom.addValueChangeListener(e -> updateList());
        dateFrom.setLabel("Date from");

        dateTo.setPlaceholder("Date to");
        dateTo.setClearButtonVisible(true);
        dateTo.addValueChangeListener(e -> updateList());
        dateTo.setLabel("Date to");

        guestsNumber.setPlaceholder("Max guests number");
        guestsNumber.setClearButtonVisible(true);
        guestsNumber.addValueChangeListener(e -> updateList());
        guestsNumber.setLabel("Max guests number");

        pricePerNight.setPlaceholder("Set price max");
        pricePerNight.setClearButtonVisible(true);
        pricePerNight.addValueChangeListener(e -> updateList());
        pricePerNight.setLabel("Price max");

        tempMin.setPlaceholder("Set temp min");
        tempMin.setClearButtonVisible(true);
        tempMin.addValueChangeListener(e -> updateList());
        tempMin.setLabel("Temp min");

        hotel.setPlaceholder("Select hotel");
        hotel.setClearButtonVisible(true);
        hotel.addValueChangeListener(e -> updateList());
        hotel.setLabel("Hotel");
        hotel.setItems(hotelsDisplayed);
        hotel.setItemLabelGenerator(HotelDto::getName);

        Button addRoomButton = new Button("Add room", click -> addRoom());
        HorizontalLayout toolbar = null;
        if (currentUser.getRole().equals("ROLE_OWNER"))
            toolbar = new HorizontalLayout(name, guestsNumber, pricePerNight, hotel, addRoomButton);
        else
            toolbar = new HorizontalLayout(name, dateFrom, dateTo, guestsNumber, pricePerNight, tempMin, hotel);
        toolbar.expand(pricePerNight);
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
                h -> h.getHotel().getName()
        ).setHeader("Hotel");

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
        if (currentUser.getRole().equals("ROLE_OWNER"))
            grid.setItems(hotelsDisplayed
                    .stream()
                    .map(hotelDto -> roomService.getAllRoomsInHotelWithCriteria(Long.valueOf(hotelDto.getId()),
                            null, null, null, null))
                    .flatMap(List::stream)
                    .collect(Collectors.toList()));
        else
            grid.setItems(roomService.findAllWithCriteria(
                    name.getValue(),
                    dateFrom.getValue(),
                    dateTo.getValue(),
                    guestsNumber.getValue(),
                    pricePerNight.getValue(),
                    tempMin.getValue(),
                    hotel.getValue()
            ));
    }
}
