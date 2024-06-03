package component.chip.not;

import component.chip.ChipCreator;
import component.ChipComponent;

public class IC7404Creator extends ChipCreator {

    @Override
    public ChipComponent createChip() {
        return new IC7404();
    }
}
