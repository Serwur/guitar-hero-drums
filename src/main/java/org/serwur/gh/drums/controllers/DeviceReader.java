package org.serwur.gh.drums.controllers;

import net.java.games.input.ControllerEnvironment;

import java.util.*;
import java.util.stream.Collectors;

public class DeviceReader {

    private List<Device> devices = new ArrayList<>();

    public DeviceReader() {
        reloadDevices();
    }

    public void reloadDevices() {
        devices = Arrays.stream(ControllerEnvironment.getDefaultEnvironment().getControllers())
                .map(Device::new)
                .collect(Collectors.toList());
    }

    public List<Device> getDevices() {
        return devices;
    }

    public Device getDevice(int index) {
        return devices.get(index);
    }
}
