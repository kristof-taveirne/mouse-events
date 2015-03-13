package org.vaadin.marcus.client;

import com.vaadin.shared.communication.ServerRpc;

public interface MouseEventsRPC extends ServerRpc {
    public void mouseOver();
    public void mouseOut();
}
