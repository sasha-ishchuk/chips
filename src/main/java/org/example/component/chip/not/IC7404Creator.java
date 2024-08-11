package org.example.component.chip.not;

import org.example.component.chip.ChipCreator;
import org.example.component.ChipComponent;

public class IC7404Creator extends ChipCreator {

    @Override
    public ChipComponent createChip() {
        return new IC7404();
    }
}
