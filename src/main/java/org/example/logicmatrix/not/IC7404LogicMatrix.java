package org.example.logicmatrix.not;

import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.LogicMatrix;

import java.util.List;

public class IC7404LogicMatrix extends LogicMatrix {

    @Override
    public void checkInputsValid(List<PinState> input) {
        if (input.size() != 6) {
            throw new IllegalArgumentException("IC7404LogicMatrix requires 6 input pins");
        }
    }

    @Override
    public List<PinState> map(List<PinState> input) {
        return List.of(
                simulateNot(input.get(0)),
                simulateNot(input.get(1)),
                simulateNot(input.get(2)),
                simulateNot(input.get(3)),
                simulateNot(input.get(4)),
                simulateNot(input.get(5))
        );
    }
}
