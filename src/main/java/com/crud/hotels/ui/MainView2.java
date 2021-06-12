package com.crud.hotels.ui;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route(value = "asda")
public class MainView2 extends VerticalLayout {

    public MainView2() {
        Button button = new Button("Click me");
        add(button);
    }
}
