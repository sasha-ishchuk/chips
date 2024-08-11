package org.example.component.chip.demultiplexer;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;

public class IC74138Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC74138();
    }
}
