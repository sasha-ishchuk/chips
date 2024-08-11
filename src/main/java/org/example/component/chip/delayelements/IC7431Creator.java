package org.example.component.chip.delayelements;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;

public class IC7431Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC7431();
    }
}
