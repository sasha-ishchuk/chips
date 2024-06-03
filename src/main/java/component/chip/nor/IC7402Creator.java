package component.chip.nor;

import component.chip.ChipCreator;
import component.ChipComponent;

public class IC7402Creator extends ChipCreator {

    @Override
    public ChipComponent createChip() {
        return new IC7402();
    }
}
