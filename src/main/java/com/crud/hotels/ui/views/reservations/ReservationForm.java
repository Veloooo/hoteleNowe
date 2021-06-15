package com.crud.hotels.ui.views.reservations;

import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.dto.ReservationDto;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

public class ReservationForm extends FormLayout {

    Checkbox paid = new Checkbox("Paid");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Close");

    Binder<ReservationDto> binder = new BeanValidationBinder<>(ReservationDto.class);

    public ReservationForm(){
        addClassName("hotel-form");
        binder.bindInstanceFields(this);

        add(paid, createButtonsLayout());
    }

    public void setReservationDto(ReservationDto reservationDto){
        binder.setBean(reservationDto);
    }

    public Button getSave() {
        return save;
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
    public abstract static class ReservationFormEvent extends ComponentEvent<ReservationForm> {
        private ReservationDto reservationDto;

        protected ReservationFormEvent(ReservationForm source, ReservationDto reservationDto) {
            super(source, false);
            this.reservationDto = reservationDto;
        }

        public ReservationDto getReservation() {
            return reservationDto;
        }
    }

    public static class SaveEvent extends ReservationFormEvent {
        SaveEvent(ReservationForm source, ReservationDto reservationDto) {
            super(source, reservationDto);
        }
    }

    public static class DeleteEvent extends ReservationFormEvent {
        DeleteEvent(ReservationForm source, ReservationDto reservationDto) {
            super(source, reservationDto);
        }

    }

    public static class CloseEvent extends ReservationFormEvent {
        CloseEvent(ReservationForm source) {
            super(source, null);
        }
    }

    @Override
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
