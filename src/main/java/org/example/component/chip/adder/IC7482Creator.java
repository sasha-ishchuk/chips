package org.example.component.chip.adder;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;

public class IC7482Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC7482();
    }
}
