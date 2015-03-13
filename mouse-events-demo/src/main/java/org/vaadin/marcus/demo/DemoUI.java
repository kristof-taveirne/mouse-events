package org.vaadin.marcus.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.marcus.MouseEvents;

import javax.servlet.annotation.WebServlet;

@Theme("demo")
@Title("MyComponent Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.marcus.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);

        Button button = new Button("Button with hover", e -> layout.addComponent(new Label("Click")));
        MouseEvents mouseEvents = MouseEvents.enableFor(button);
        mouseEvents.addMouseOverListener(() -> layout.addComponent(new Label("Mouse over")));
        mouseEvents.addMouseOutListener(() -> layout.addComponent(new Label("Mouse out")));

        layout.addComponent(button);
        setContent(layout);
    }

}
