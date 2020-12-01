package org.serwur.gh.drums.sound;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MidiDeviceReader {
    private List<CustomMidiDevice> devices = new ArrayList<>();

    public MidiDeviceReader() throws MidiUnavailableException {
        reloadDevices();
    }

    public void reloadDevices() throws MidiUnavailableException {
        List<CustomMidiDevice> list = new ArrayList<>();
        for (MidiDevice.Info info : MidiSystem.getMidiDeviceInfo()) {
            CustomMidiDevice customMidiDevice = new CustomMidiDevice(info);
            list.add(customMidiDevice);
        }
        devices = list;
    }

    public List<CustomMidiDevice> getDevices() {
        return devices;
    }

    public CustomMidiDevice getDevice(int index) {
        return devices.get(index);
    }
}
