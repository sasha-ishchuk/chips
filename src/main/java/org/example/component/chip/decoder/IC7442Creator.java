package org.example.component.chip.decoder;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;

public class IC7442Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC7442();
    }
}
