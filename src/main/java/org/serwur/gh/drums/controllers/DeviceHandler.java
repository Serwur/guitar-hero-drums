package org.serwur.gh.drums.controllers;

import net.java.games.input.Event;
import net.java.games.input.EventQueue;

import java.util.Iterator;

/**
 * The purpose is to handle all incoming inputs
 */
public class DeviceHandler implements Iterable<Event>{
    private final Device device;

    public DeviceHandler(Device device) {
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }

    @Override
    public Iterator<Event> iterator() {
        return new EventIterator(device.getEventQueue());
    }

    private static class EventIterator implements Iterator<Event> {
        private final EventQueue eventQueue;
        private Event current;


        public EventIterator(EventQueue eventQueue) {
            this.eventQueue = eventQueue;
        }

        @Override
        public boolean hasNext() {
            current = new Event();
            return eventQueue.getNextEvent(current);
        }

        @Override
        public Event next() {
            return current;
        }
    }
}
