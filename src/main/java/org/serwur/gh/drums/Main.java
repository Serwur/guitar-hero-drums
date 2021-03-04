package org.serwur.gh.drums;

import org.serwur.gh.drums.controllers.DeviceInputListener;
import org.serwur.gh.drums.controllers.ExternalDevice;
import org.serwur.gh.drums.controllers.ExternalDeviceHandler;
import org.serwur.gh.drums.controllers.ExternalDeviceReader;
import org.serwur.gh.drums.sound.CustomMidiDeviceReader;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main implements DeviceInputListener {

    public static void main(String... args) throws Exception {
        CustomMidiDeviceReader midiReader = new CustomMidiDeviceReader();
        midiReader.printDevices();

        if (args.length > 1) {
            int deviceIndex = Integer.parseInt(args[1]);
            switch (args[0]) {
                case "i":
                    midiReader.getInputDevice(deviceIndex).getReceiver();
                    break;
                case "o":
                    midiReader.getOutputDevice(deviceIndex).getTransmitter();
                    break;
                default:
                    throw new Exception("Wrong first argument");
            }
        }

        ExternalDeviceReader reader = new ExternalDeviceReader();
        reader.getDevices().forEach(System.out::println);

        List<ExternalDevice> xboxDevices = reader.getXbox360Controllers();
        if (!xboxDevices.isEmpty()) {
            ExternalDeviceHandler handler = new ExternalDeviceHandler(xboxDevices.get(1));
            handler.start();
        } else {
            System.out.println("Xbox360 devices not found");
        }
    }


    @Override
    public void onInputReceived(int key) {
        System.out.printf("Fetched key: %s", key);
    }

}
