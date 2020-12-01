package org.serwur.gh.drums;

import org.serwur.gh.drums.controllers.DeviceReader;
import org.serwur.gh.drums.sound.MidiDeviceReader;

import javax.sound.midi.MidiUnavailableException;

public class Main {

    public static void main(String ...args) throws MidiUnavailableException {
        DeviceReader reader = new DeviceReader();
        reader.getDevices().forEach(System.out::println);

        MidiDeviceReader midiReader = new MidiDeviceReader();
        midiReader.getDevices().forEach(System.out::println);
    }

}
