package org.serwur.gh.drums.sound;

import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

public class CustomMidiDeviceConnection {
    public CustomMidiDeviceConnection(CustomMidiDevice transmitter, CustomMidiDevice receiver) throws Exception {
        reconnectDevices(transmitter, receiver);
    }

    private void reconnectDevices(CustomMidiDevice transmitter, CustomMidiDevice receiver) throws Exception {
        if (!transmitter.isMidiPort() && !transmitter.isOutput()) throw new Exception(String.format("Given transmitter is not valid device: %s", transmitter));
        if (!receiver.isMidiPort() && !receiver.isInput()) throw new Exception(String.format("Given receiver is not valid device: %s", receiver));

        Transmitter trns = transmitter.getTransmitter();
        Receiver rcvr = receiver.getReceiver();

        trns.setReceiver(rcvr);

        System.out.println("Devices has been successfully connected");
    }
}
