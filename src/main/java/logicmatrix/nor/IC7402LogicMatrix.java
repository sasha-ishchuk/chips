package logicmatrix.nor;

import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.LogicMatrix;

import java.util.List;

public class IC7402LogicMatrix extends LogicMatrix {

    @Override
    public void checkInputsValid(List<PinState> input) {
        if (input.size() != 8) {
            throw new IllegalArgumentException("IC7402LogicMatrix requires 8 input pins");
        }
    }

    @Override
    public List<PinState> map(List<PinState> input) {
        return List.of(
                simulateNor4(input.get(0), input.get(1)),
                simulateNor4(input.get(2), input.get(3)),
                simulateNor4(input.get(4), input.get(5)),
                simulateNor4(input.get(6), input.get(7))
        );
    }

    private PinState simulateNor4(PinState a, PinState b) {
        if (a == PinState.LOW && b == PinState.LOW) {
            return PinState.HIGH;
        } else if (isAnyPinStateUnknown(List.of(a, b))) {
            return PinState.UNKNOWN;
        }
        return PinState.LOW;
    }
}
