package component.chip.and;

import component.chip.ChipCreator;
import component.ChipComponent;

public class IC7408Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC7408();
    }
}
