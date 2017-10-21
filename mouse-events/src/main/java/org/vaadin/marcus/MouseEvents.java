package org.vaadin.marcus;


import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.AbstractExtension;
import org.vaadin.marcus.client.MouseEventsRPC;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MouseEvents extends AbstractExtension {

	private Set<MouseOverListener> mouseOverListeners = new HashSet<MouseOverListener>();

	private Set<MouseOutListener> mouseOutListeners = new HashSet<MouseOutListener>();

	private Set<MouseDownListener> mouseDownListeners = new HashSet<MouseDownListener>();

	private Set<MouseUpListener> mouseUpListeners = new HashSet<MouseUpListener>();

	public interface MouseOverListener extends Serializable {
		void mouseOver();
	}

	public interface MouseOutListener extends Serializable {
		void mouseOut();
	}

	public interface MouseDownListener extends Serializable {
		void mouseDown();
	}

	public interface MouseUpListener extends Serializable {
		void mouseUp();
	}


	MouseEventsRPC rpc = new MouseEventsRPC() {
		@Override
		public void mouseOver() {
			fireMouseOverEvents();
		}

		@Override
		public void mouseOut() {
			fireMouseOutEvents();
		}

		@Override
		public void mouseDown() {
			fireMouseDownEvents();
		}

		@Override
		public void mouseUp() {
			fireMouseUpEvents();
		}
	};

	protected MouseEvents(AbstractClientConnector component) {
		registerRpc(rpc);
		extend(component);
	}

	public static MouseEvents enableFor(AbstractClientConnector component) {
		return new MouseEvents(component);
	}

	private void fireMouseOverEvents() {
		for (MouseOverListener listener : Collections.unmodifiableCollection(mouseOverListeners)) {
			listener.mouseOver();
		}
	}

	private void fireMouseOutEvents() {
		for (MouseOutListener listener : Collections.unmodifiableCollection(mouseOutListeners)) {
			listener.mouseOut();
		}
	}


	private void fireMouseDownEvents() {
		for (MouseDownListener listener : Collections.unmodifiableCollection(mouseDownListeners)) {
			listener.mouseDown();
		}
	}

	private void fireMouseUpEvents() {
		for (MouseUpListener listener : Collections.unmodifiableCollection(mouseUpListeners)) {
			listener.mouseUp();
		}
	}

	public void addMouseOverListener(MouseOverListener listener) {
		mouseOverListeners.add(listener);
	}

	public void removeMouseOverListener(MouseOverListener listener) {
		mouseOverListeners.remove(listener);
	}

	public void addMouseOutListener(MouseOutListener listener) {
		mouseOutListeners.add(listener);
	}

	public void removeMouseOutListener(MouseOutListener listener) {
		mouseOutListeners.remove(listener);
	}

	public void addMouseDownListener(MouseDownListener listener) {
		mouseDownListeners.add(listener);
	}

	public void removeMouseDownListener(MouseDownListener listener) {
		mouseDownListeners.remove(listener);
	}

	public void addMouseUpListener(MouseUpListener listener) {
		mouseUpListeners.add(listener);
	}

	public void removeMouseUpListener(MouseUpListener listener) {
		mouseUpListeners.remove(listener);
	}


}
