package org.example.component.chip.nand;

import org.example.component.chip.ChipCreator;
import org.example.component.ChipComponent;

public class IC7410Creator extends ChipCreator {

    @Override
    public ChipComponent createChip() {
        return new IC7410();
    }

}
