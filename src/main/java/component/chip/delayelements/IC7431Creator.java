package component.chip.delayelements;

import component.ChipComponent;
import component.chip.ChipCreator;

public class IC7431Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC7431();
    }
}
