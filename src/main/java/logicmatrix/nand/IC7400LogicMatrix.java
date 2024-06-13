package logicmatrix.nand;

import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.LogicMatrix;

import java.util.List;

public class IC7400LogicMatrix extends LogicMatrix {

    @Override
    public void checkInputsValid(List<PinState> input) {
        if (input.size() != 8) {
            throw new IllegalArgumentException("IC7400LogicMatrix requires 8 input pins");
        }
    }

    @Override
    public List<PinState> map(List<PinState> input) {
        return List.of(
                simulateNand2(input.get(0), input.get(1)),
                simulateNand2(input.get(2), input.get(3)),
                simulateNand2(input.get(4), input.get(5)),
                simulateNand2(input.get(6), input.get(7))
        );
    }

    private PinState simulateNand2(PinState a, PinState b) {
        if (a == PinState.HIGH && b == PinState.HIGH) {
            return PinState.LOW;
        }
        else if (a == PinState.LOW || b == PinState.LOW) {
            return PinState.HIGH;
        }
        return PinState.UNKNOWN;
    }
}
