package com.crud.hotels.ui.views.rooms;

import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.dto.RoomDto;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.shared.Registration;

import java.util.List;
import java.util.function.DoubleToIntFunction;

import static com.crud.hotels.ui.MainLayout.currentUser;

public class RoomForm extends FormLayout {

    TextField name = new TextField("Name");
    NumberField pricePerNight = new NumberField("Price per night");
    NumberField guestsNumber = new NumberField("Max Guests");
    ComboBox<HotelDto> hotel = new ComboBox<>("Hotel");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Close");

    Binder<RoomDto> binder = new BeanValidationBinder<>(RoomDto.class);
    private List<HotelDto> hotels;

    public RoomForm(List<HotelDto> hotels){
        addClassName("hotel-form");
        binder.bindInstanceFields(this);
        hotel.setItems(hotels);
        hotel.setItemLabelGenerator(HotelDto::getName);
        add(name, guestsNumber, pricePerNight, hotel, createButtonsLayout());
    }

    public void setRoomDto(RoomDto roomDto){
        binder.setBean(roomDto);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        if("ROLE_OWNER".equals(currentUser.getRole()))
            return new HorizontalLayout(save, delete, close);
        else 
            return new HorizontalLayout();
    }

    private void validateAndSave() {
        if(binder.isValid())
            fireEvent(new SaveEvent(this, binder.getBean()));
    }

    // Events
    public abstract static class RoomFormEvent extends ComponentEvent<RoomForm> {
        private RoomDto roomDto;

        protected RoomFormEvent(RoomForm source, RoomDto roomDto) {
            super(source, false);
            this.roomDto = roomDto;
        }

        public RoomDto getRoomDto() {
            return roomDto;
        }
    }

    public static class SaveEvent extends RoomFormEvent {
        SaveEvent(RoomForm source, RoomDto roomDto) {
            super(source, roomDto);
        }
    }

    public static class DeleteEvent extends RoomFormEvent {
        DeleteEvent(RoomForm source, RoomDto roomDto) {
            super(source, roomDto);
        }

    }

    public static class CloseEvent extends RoomFormEvent {
        CloseEvent(RoomForm source) {
            super(source, null);
        }
    }

    public static class BookEvent extends RoomFormEvent {
        BookEvent(RoomForm source, RoomDto roomDto) {
            super(source, roomDto);
        }
    }

    @Override
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}