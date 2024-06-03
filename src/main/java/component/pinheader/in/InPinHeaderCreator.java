package component.pinheader.in;

import component.pinheader.PinHeaderCreator;
import component.PinHeaderComponent;

public class InPinHeaderCreator extends PinHeaderCreator {
    @Override
    public PinHeaderComponent createPinHeader(int size) {
        return new InPinHeader(size);
    }
}
