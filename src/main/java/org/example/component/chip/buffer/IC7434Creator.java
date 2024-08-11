package org.example.component.chip.buffer;

import org.example.component.chip.ChipCreator;
import org.example.component.ChipComponent;

public class IC7434Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC7434();
    }
}
