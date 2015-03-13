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


    public interface MouseOverListener extends Serializable {
        void mouseOver();
    }

    public interface MouseOutListener extends Serializable  {
        void mouseOut();
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
}
