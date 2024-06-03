package component.pinheader.out;

import component.PinHeaderComponent;
import component.pinheader.PinHeaderCreator;

public class OutPinHeaderCreator extends PinHeaderCreator {
    @Override
    public PinHeaderComponent createPinHeader(int size) {
        return new OutPinHeader(size);
    }
}
