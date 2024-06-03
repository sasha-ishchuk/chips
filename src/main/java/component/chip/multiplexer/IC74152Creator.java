package component.chip.multiplexer;

import component.ChipComponent;
import component.chip.ChipCreator;

public class IC74152Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC74152();
    }
}
