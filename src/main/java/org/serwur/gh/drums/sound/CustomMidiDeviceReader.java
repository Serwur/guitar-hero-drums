package org.serwur.gh.drums.sound;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.util.ArrayList;
import java.util.List;

public class CustomMidiDeviceReader {
    private final List<CustomMidiDevice> inputDevices = new ArrayList<>();
    private final List<CustomMidiDevice> outputDevices = new ArrayList<>();
    private final List<CustomMidiDevice> sequencerDevices = new ArrayList<>();

    public CustomMidiDeviceReader() throws MidiUnavailableException {
        reloadDevices();
    }

    public void reloadDevices() throws MidiUnavailableException {
        outputDevices.clear();
        inputDevices.clear();
        sequencerDevices.clear();

        for (MidiDevice.Info info : MidiSystem.getMidiDeviceInfo()) {
            CustomMidiDevice customMidiDevice = new CustomMidiDevice(info);
            if (customMidiDevice.isOutput()) outputDevices.add(customMidiDevice);
            else if (customMidiDevice.isInput()) inputDevices.add(customMidiDevice);
            else sequencerDevices.add(customMidiDevice);
        }
    }

    public boolean hasInputDevices() {
        return !inputDevices.isEmpty();
    }

    public boolean hasOutputDevices() {
        return !outputDevices.isEmpty();
    }

    public boolean hasSequencerDevices() {
        return !sequencerDevices.isEmpty();
    }

    public List<CustomMidiDevice> getInputDevices() {
        return inputDevices;
    }

    public List<CustomMidiDevice> getOutputDevices() {
        return outputDevices;
    }

    public CustomMidiDevice getInputDevice(int index) {
        return inputDevices.get(index);
    }

    public CustomMidiDevice getOutputDevice(int index) {
        return outputDevices.get(index);
    }

    public void printDevices() {
        printInputDevices();
        printOutputDevices();
        printSequencerDevices();
    }

    public void printInputDevices() {
        printDevicesWithIndex("Input", inputDevices);
    }

    public void printOutputDevices() {
        printDevicesWithIndex("Output", outputDevices);
    }

    public void printSequencerDevices() {
        printDevicesWithIndex("Sequencer", sequencerDevices);
    }

    private void printDevicesWithIndex(String devicesType, List<CustomMidiDevice> devices) {
        System.out.printf("--- %s Devices ---%n", devicesType);
        for (int i = 0; i < devices.size(); i++) {
            System.out.printf("[%s] %s%n", i, devices.get(i));
        }
    }
}
