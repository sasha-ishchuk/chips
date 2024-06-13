package logicmatrix.and;

import logicmatrix.LogicMatrix;
import edu.uj.po.simulation.interfaces.PinState;

import java.util.List;

public class IC7408LogicMatrix extends LogicMatrix {

    @Override
    public void checkInputsValid(List<PinState> input) {
        if (input.size() != 8) {
            throw new IllegalArgumentException("IC7408LogicMatrix requires 8 input pins");
        }
    }

    @Override
    public List<PinState> map(List<PinState> input) {
        return List.of(
                simulateAnd2(input.get(0), input.get(1)),
                simulateAnd2(input.get(2), input.get(3)),
                simulateAnd2(input.get(4), input.get(5)),
                simulateAnd2(input.get(6), input.get(7))
        );
    }
}
