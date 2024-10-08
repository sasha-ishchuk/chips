package org.example.logicmatrix.and;

import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.LogicMatrix;

import java.util.List;

public class IC7411LogicMatrix extends LogicMatrix {

    @Override
    public void checkInputsValid(List<PinState> input) {
        if (input.size() != 9) {
            throw new IllegalArgumentException("IC7411LogicMatrix requires 9 input pins");
        }
    }

    @Override
    public List<PinState> map(List<PinState> input) {
        return List.of(
                simulateAnd3(input.get(2), input.get(3), input.get(4)),
                simulateAnd3(input.get(5), input.get(6), input.get(7)),
                simulateAnd3(input.get(0), input.get(1), input.get(8))
        );
    }

    private PinState simulateAnd3(PinState a, PinState b, PinState c) {
        if (a == PinState.HIGH && b == PinState.HIGH && c == PinState.HIGH) {
            return PinState.HIGH;
        }
        else if (a == PinState.LOW || b == PinState.LOW || c == PinState.LOW) {
            return PinState.LOW;
        }
        return PinState.UNKNOWN;
    }
}
