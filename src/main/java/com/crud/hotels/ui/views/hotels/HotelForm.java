package com.crud.hotels.ui.views.hotels;

import com.crud.hotels.backend.dto.HotelDto;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;


import java.util.*;

public class HotelForm extends FormLayout {

    TextField name = new TextField("Name");
    TextField city = new TextField("City");
    ComboBox<String> country = new ComboBox<>("Country");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Close");

    Binder<HotelDto> binder = new BeanValidationBinder<>(HotelDto.class);

    public HotelForm(){
        addClassName("hotel-form");
        binder.bindInstanceFields(this);

        String[] countryCodes = Locale.getISOCountries();
        List<String> countriesList = new ArrayList<>();
        for (String countryCode : countryCodes) {
            Locale obj = new Locale("", countryCode);
            countriesList.add(obj.getDisplayCountry(Locale.ENGLISH));
        }
        Collections.sort(countriesList);
        country.setItems(countriesList);

        add(name, city, country, createButtonsLayout());
    }

    public void setHotelDto(HotelDto hotelDto){
        binder.setBean(hotelDto);
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

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if(binder.isValid())
            fireEvent(new SaveEvent(this, binder.getBean()));
    }

    // Events
    public abstract static class HotelFormEvent extends ComponentEvent<HotelForm> {
        private HotelDto hotel;

        protected HotelFormEvent(HotelForm source, HotelDto hotel) {
            super(source, false);
            this.hotel = hotel;
        }

        public HotelDto getHotel() {
            return hotel;
        }
    }

    public static class SaveEvent extends HotelFormEvent {
        SaveEvent(HotelForm source, HotelDto hotel) {
            super(source, hotel);
        }
    }

    public static class DeleteEvent extends HotelFormEvent {
        DeleteEvent(HotelForm source, HotelDto hotel) {
            super(source, hotel);
        }

    }

    public static class CloseEvent extends HotelFormEvent {
        CloseEvent(HotelForm source) {
            super(source, null);
        }
    }

    @Override
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
