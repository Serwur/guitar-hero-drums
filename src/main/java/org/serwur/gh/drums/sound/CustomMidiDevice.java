package org.serwur.gh.drums.sound;

import javax.sound.midi.*;
import java.util.List;

public class CustomMidiDevice implements MidiDevice {
    private final MidiDevice.Info deviceInfo;
    private final MidiDevice device;

    public CustomMidiDevice(MidiDevice.Info midiDevice) throws MidiUnavailableException {
        this.deviceInfo = midiDevice;
        this.device = MidiSystem.getMidiDevice(midiDevice);
    }

    public boolean isMidiPort() {
        return !(device instanceof Sequencer) && !(device instanceof Synthesizer);
    }

    @Override
    public Info getDeviceInfo() {
        return deviceInfo;
    }

    @Override
    public void open() throws MidiUnavailableException {
        device.open();
    }

    @Override
    public void close() {
        device.close();
    }

    @Override
    public boolean isOpen() {
        return device.isOpen();
    }

    @Override
    public long getMicrosecondPosition() {
        return device.getMicrosecondPosition();
    }

    @Override
    public int getMaxReceivers() {
        return device.getMaxReceivers();
    }

    @Override
    public int getMaxTransmitters() {
        return device.getMaxTransmitters();
    }

    @Override
    public Receiver getReceiver() throws MidiUnavailableException {
        return device.getReceiver();
    }

    @Override
    public List<Receiver> getReceivers() {
        return device.getReceivers();
    }

    @Override
    public Transmitter getTransmitter() throws MidiUnavailableException {
        return device.getTransmitter();
    }

    @Override
    public List<Transmitter> getTransmitters() {
        return device.getTransmitters();
    }

    @Override
    public String toString() {
        return String.format("MidiDevice {name: %s, description: %s, vendor: %s, version: %s, isMidiPort: %s}",
                deviceInfo.getName(),
                deviceInfo.getDescription(),
                deviceInfo.getVendor(),
                deviceInfo.getVersion(),
                isMidiPort());
    }
}
