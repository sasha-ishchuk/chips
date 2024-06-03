package component.chip.demultiplexer;

import component.ChipComponent;
import component.chip.ChipCreator;

public class IC74138Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC74138();
    }
}
