package org.vaadin.marcus.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;
import org.vaadin.marcus.MouseEvents;

@Connect(MouseEvents.class)
public class MouseEventsConnector extends AbstractExtensionConnector implements MouseOverHandler, MouseOutHandler, MouseUpHandler, MouseDownHandler {

	@Override
	protected void extend(ServerConnector serverConnector) {
		Widget target = ((ComponentConnector) serverConnector).getWidget();
		target.sinkEvents(Event.ONMOUSEOVER | Event.ONMOUSEOUT);
		target.addHandler(this, MouseOverEvent.getType());
		target.addHandler(this, MouseOutEvent.getType());
	}

	@Override
	public void onMouseOver(MouseOverEvent mouseOverEvent) {
		console("MouseOver event!!");
		getRpcProxy(MouseEventsRPC.class).mouseOver();
	}

	@Override
	public void onMouseOut(MouseOutEvent mouseOutEvent) {
		console("MouseOut event!!");
		getRpcProxy(MouseEventsRPC.class).mouseOut();
	}


	@Override
	public void onMouseDown(MouseDownEvent mouseDownEvent) {
		console("MouseDown event!!");
		getRpcProxy(MouseEventsRPC.class).mouseDown();
	}

	@Override
	public void onMouseUp(MouseUpEvent mouseUpEvent) {
		console("MouseUp event!!");
		getRpcProxy(MouseEventsRPC.class).mouseUp();
	}

	static native void console(String text)
	/*-{
        console.log(text);
    }-*/;
}
