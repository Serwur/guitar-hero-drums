package org.serwur.gh.drums.controllers;

import net.java.games.input.Controller;
import net.java.games.input.EventQueue;

public class Device {
    private final Controller controller;

    public Device(Controller controller) {
        this.controller = controller;
    }

    public Controller getController () {
        return controller;
    }

    public EventQueue getEventQueue() {
        controller.poll();
        return controller.getEventQueue();
    }

    @Override
    public String toString() {
        return String.format("Device {name: %s, portNumber: %s, portType: %s, type: %s}",
                controller.getName(),
                controller.getPortNumber(),
                controller.getPortType(),
                controller.getType().toString());
    }
}
