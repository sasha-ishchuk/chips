package org.example.component.pinheader.in;

import org.example.component.pinheader.PinHeaderCreator;
import org.example.component.PinHeaderComponent;

public class InPinHeaderCreator extends PinHeaderCreator {
    @Override
    public PinHeaderComponent createPinHeader(int size) {
        return new InPinHeader(size);
    }
}
