package org.example.component.chip.and;

import org.example.component.chip.ChipCreator;
import org.example.component.ChipComponent;

public class IC7411Creator extends ChipCreator {
    @Override
    public ChipComponent createChip() {
        return new IC7411();
    }
}
