package component.chip.or;

import component.chip.ChipCreator;
import component.ChipComponent;

public class IC7432Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC7432();
    }
}
