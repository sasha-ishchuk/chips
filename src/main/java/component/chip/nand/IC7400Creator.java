package component.chip.nand;

import component.chip.ChipCreator;
import component.ChipComponent;

public class IC7400Creator extends ChipCreator {

    @Override
    public ChipComponent createChip() {
        return new IC7400();
    }

}
