package org.example.component.chip.or;

import org.example.component.chip.ChipCreator;
import org.example.component.ChipComponent;

public class IC7432Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC7432();
    }
}
