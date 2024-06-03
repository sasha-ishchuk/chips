package component.chip.buffer;

import component.chip.ChipCreator;
import component.ChipComponent;

public class IC7434Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC7434();
    }
}
