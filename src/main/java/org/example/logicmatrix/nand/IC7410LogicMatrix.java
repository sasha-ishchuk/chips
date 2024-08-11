package org.example.logicmatrix.nand;

import org.example.logicmatrix.LogicMatrix;
import org.example.edu.uj.po.simulation.interfaces.PinState;

import java.util.List;

public class IC7410LogicMatrix extends LogicMatrix {

    @Override
    public void checkInputsValid(List<PinState> input) {
        if (input.size() != 9) {
            throw new IllegalArgumentException("IC7410LogicMatrix requires 9 input pins");
        }
    }

    @Override
    public List<PinState> map(List<PinState> input) {
        return List.of(
                simulateNand3(input.get(0), input.get(1), input.get(2)),
                simulateNand3(input.get(3), input.get(4), input.get(5)),
                simulateNand3(input.get(6), input.get(7), input.get(8))
        );
    }

    private PinState simulateNand3(PinState a, PinState b, PinState c) {
        if (a == PinState.HIGH && b == PinState.HIGH && c == PinState.HIGH) {
            return PinState.LOW;
        } else if (isAnyPinStateUnknown(List.of(a, b, c))) {
            return PinState.UNKNOWN;
        }
        return PinState.HIGH;
    }
}
