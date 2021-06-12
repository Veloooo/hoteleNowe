package com.crud.hotels.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class HotelForm extends FormLayout {

    TextField name = new TextField("Name");
    TextField city = new TextField("City");
    TextField country = new TextField("Country");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Close");


    public HotelForm(){
        addClassName("hotel-form");

        add(name, city, country, createButtonsLayout());
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save, delete, close);
    }
}
