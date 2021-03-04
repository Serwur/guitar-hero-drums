package org.serwur.gh.drums.controllers;

import net.java.games.input.Event;
import net.java.games.input.EventQueue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * The purpose is to handle all incoming inputs
 */
public class ExternalDeviceHandler extends Thread implements Iterable<Event> {
    private final ExternalDevice externalDevice;

    private RunningHandler runner = new RunningHandler(this);
    private Set<DeviceInputListener> listeners = new HashSet<>();

    public ExternalDeviceHandler(ExternalDevice externalDevice) {
        this.externalDevice = externalDevice;
    }

    public ExternalDevice getDevice() {
        return externalDevice;
    }

    public boolean addListener(DeviceInputListener listener) {
        return listeners.add(listener);
    }

    public boolean removeListener(DeviceInputListener listener) {
        return listeners.remove(listener);
    }

    @Override
    public Iterator<Event> iterator() {
        return new EventIterator(externalDevice.getEventQueue());
    }

    @Override
    public void run() {
        System.out.printf("Started listening device: %s%n", externalDevice);
        boolean errorOccurred = false;

        if (canStart()) {
            try {
                runner.handle();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                errorOccurred = true;
            }
        }

        if (errorOccurred) System.out.println("Listening has been ended");
        else System.err.println("Listening has been ended due to error");
    }

    public boolean isConnected() {
        return false;
    }

    private boolean canStart() {
        return true;
    }

    private static class RunningHandler {
        public final static long DEFAULT_IDLE_MS = 20L;
        private final ExternalDeviceHandler handler;
        private final int maxSleepErrors = 20;

        private int currentSleepError = 0;
        private boolean isRunning = false;

        public RunningHandler(ExternalDeviceHandler handler) {
            this.handler = handler;
        }

        public void handle() throws Exception {
            isRunning = true;

            while (isRunning) {
                for (Event event : handler) {
                    int key = -1;
                    try {
                        Integer.parseInt(event.getComponent().getIdentifier().toString());
                    } catch (NumberFormatException e) {
                        System.err.printf("%s%n", e.getMessage());
                    }
                    handler.listeners.forEach(listener -> listener.onInputReceived(key));
                }
                sleep(DEFAULT_IDLE_MS);
            }
        }


        private void sleep(long ms) throws Exception {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                currentSleepError++;
                System.err.printf("Thread.sleep error, current count: %s", currentSleepError);
                if (currentSleepError >= maxSleepErrors) {
                    throw new Exception("Exceeded maximum Thread.sleep errors");
                }
            }
        }
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
