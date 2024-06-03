package component.chip.decoder;

import component.ChipComponent;
import component.chip.ChipCreator;

public class IC7442Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC7442();
    }
}
