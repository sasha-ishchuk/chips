package org.example.logicmatrix.nand;

import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.LogicMatrix;

import java.util.List;

public class IC7420LogicMatrix extends LogicMatrix {

    @Override
    public void checkInputsValid(List<PinState> input) {
        if (input.size() != 8) {
            throw new IllegalArgumentException("IC7420LogicMatrix requires 8 input pins");
        }
    }

    @Override
    public List<PinState> map(List<PinState> input) {
        return List.of(
                nand4(input.get(0), input.get(1), input.get(2), input.get(3)),
                nand4(input.get(4), input.get(5), input.get(6), input.get(7))
        );
    }

    private PinState nand4(PinState a, PinState b, PinState c, PinState d) {
        if (a == PinState.HIGH && b == PinState.HIGH && c == PinState.HIGH && d == PinState.HIGH) {
            return PinState.LOW;
        } else if (a == PinState.LOW || b == PinState.LOW || c == PinState.LOW || d == PinState.LOW) {
            return PinState.HIGH;
        }
        return PinState.UNKNOWN;
    }
}
