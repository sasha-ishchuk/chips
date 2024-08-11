package org.example.component.chip.multiplexer;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;

public class IC74152Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC74152();
    }
}
