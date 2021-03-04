package org.serwur.gh.drums.controllers;

import net.java.games.input.Controller;
import net.java.games.input.EventQueue;

public class ExternalDevice {
    private static int ID_COUNTER = 0;

    private final int id;
    private final Controller controller;

    public ExternalDevice(Controller controller) {
        this.controller = controller;
        id = ID_COUNTER++;
    }

    public Controller getController() {
        return controller;
    }

    public EventQueue getEventQueue() {
        controller.poll();
        return controller.getEventQueue();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Device {id: %s, name: %s, portNumber: %s, portType: %s, type: %s}",
                getId(),
                controller.getName(),
                controller.getPortNumber(),
                controller.getPortType(),
                controller.getType().toString());
    }
}
