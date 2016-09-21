package org.vaadin.marcus.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
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
    public static class Servlet extends VaadinServlet
    {
    }

    @Override
    protected void init(VaadinRequest request)
    {
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);

        final String loremIpsum = "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, " +
                "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, " +
                "sunt in culpa qui officia deserunt mollit anim id est laborum.";

        final Label dummyLabel = new Label(loremIpsum);
        dummyLabel.setWidth("500px");
        HorizontalLayout hl = new HorizontalLayout(dummyLabel);
        hl.setHeight("600px");
        hl.setSpacing(true);
        hl.addStyleName("demoContentLayout");

        final MouseEvents hlMouseEvents = MouseEvents.enableFor(hl);
        hlMouseEvents.addMouseOverListener(() -> Notification.show("Over event"));
        hlMouseEvents.addMouseOutListener(() -> Notification.show("Out event"));
        layout.addComponent(hl);

        setContent(layout);
    }

}
