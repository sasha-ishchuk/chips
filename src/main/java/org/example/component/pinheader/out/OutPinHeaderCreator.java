package org.example.component.pinheader.out;

import org.example.component.PinHeaderComponent;
import org.example.component.pinheader.PinHeaderCreator;

public class OutPinHeaderCreator extends PinHeaderCreator {
    @Override
    public PinHeaderComponent createPinHeader(int size) {
        return new OutPinHeader(size);
    }
}
