package com.vaadin.flow.component.dialog.tests;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
@Theme(Lumo.class)
public class HomeView extends Div {

    public HomeView() {
        getStyle().set("padding", "1em");
        add(
          new H2("Dialog Flow"),
          new Anchor("dialog", "Task"),
          new Text(" | "),
          new Anchor("solution/dialog","Solution")
        );
    }
}