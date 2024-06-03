package component.chip.adder;

import component.ChipComponent;
import component.chip.ChipCreator;

public class IC7482Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC7482();
    }
}
