package org.serwur.gh.drums.controllers;

import net.java.games.input.ControllerEnvironment;

import java.util.*;
import java.util.stream.Collectors;

public class ExternalDeviceReader {
    public static final String XBOX360_CONTROLLER_NAME = "xbox 360";

    private List<ExternalDevice> externalDevices = new ArrayList<>();

    public ExternalDeviceReader() {
        reloadDevices();
    }

    public void reloadDevices() {
        externalDevices = Arrays.stream(ControllerEnvironment.getDefaultEnvironment().getControllers())
                .map(ExternalDevice::new)
                .collect(Collectors.toList());
    }

    public boolean hasDevices() {
        return !externalDevices.isEmpty();
    }

    public List<ExternalDevice> getDevices() {
        return externalDevices;
    }

    public ExternalDevice getDevice(int index) {
        return externalDevices.get(index);
    }

    public boolean hasXbox360Controller() {
        return !getDevicesWithPartOfName(XBOX360_CONTROLLER_NAME).isEmpty();
    }

    public List<ExternalDevice> getXbox360Controllers() {
        return getDevicesWithPartOfName(XBOX360_CONTROLLER_NAME);
    }

    public List<ExternalDevice> getDevicesWithPartOfName(String deviceName) {
        return externalDevices.stream()
                .filter(device -> device.getController().getName().toLowerCase().contains(deviceName.toLowerCase()))
                .collect(Collectors.toList());
    }
}
