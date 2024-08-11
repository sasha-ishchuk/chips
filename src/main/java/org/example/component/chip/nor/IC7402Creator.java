package org.example.component.chip.nor;

import org.example.component.chip.ChipCreator;
import org.example.component.ChipComponent;

public class IC7402Creator extends ChipCreator {

    @Override
    public ChipComponent createChip() {
        return new IC7402();
    }
}
