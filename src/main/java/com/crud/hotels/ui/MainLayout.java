package com.crud.hotels.ui;

import com.crud.hotels.backend.dto.UserDto;
import com.crud.hotels.backend.service.UserService;
import com.crud.hotels.ui.views.dashboard.DashboardView;
import com.crud.hotels.ui.views.hotels.HotelsView;
import com.crud.hotels.ui.views.reservations.ReservationsView;
import com.crud.hotels.ui.views.rooms.RoomsView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    public static UserDto currentUser;
    private UserService userService;

    public MainLayout(UserService userService) {
        this.userService = userService;
        getCurrentUser();
        createHeader();
        createDrawer();
    }

    private void createDrawer() {
        RouterLink hotelsList = new RouterLink("Hotels List", HotelsView.class);
        hotelsList.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink roomsList = new RouterLink("Rooms List", RoomsView.class);
        roomsList.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink reservationsList = new RouterLink("Reservations List", ReservationsView.class);
        reservationsList.setHighlightCondition(HighlightConditions.sameLocation());
        if (currentUser.getRole().equals("ROLE_USER"))
            addToDrawer(new VerticalLayout(
                    roomsList,
                    reservationsList,
                    new RouterLink("Dashboard", DashboardView.class)
            ));
        else if (currentUser.getRole().equals("ROLE_OWNER"))
            addToDrawer(new VerticalLayout(
                    hotelsList,
                    roomsList,
                    new RouterLink("Dashboard", DashboardView.class)
            ));
    }

    private void createHeader() {
        H1 logo = new H1("Vaadin CRM");
        logo.addClassName("logo");

        Anchor logout = new Anchor("logout", "Log out");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.addClassName("header");
        header.setWidth("100%");
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        addToNavbar(header);
    }

    private void getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                currentUserName = ((UserDetails) principal).getUsername();
            } else {
                currentUserName = principal.toString();
            }
        }
        currentUser = userService.getUserByLogin(currentUserName);
    }


}
