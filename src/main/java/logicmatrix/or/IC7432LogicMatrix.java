package logicmatrix.or;

import logicmatrix.LogicMatrix;
import edu.uj.po.simulation.interfaces.PinState;

import java.util.List;

public class IC7432LogicMatrix extends LogicMatrix {
    @Override
    public List<PinState> map(List<PinState> input) {
        return List.of(
                simulateOr2(input.get(0), input.get(1)),
                simulateOr2(input.get(2), input.get(3)),
                simulateOr2(input.get(4), input.get(5)),
                simulateOr2(input.get(6), input.get(7))
        );
    }

    private PinState simulateOr2(PinState a, PinState b) {
        if (a == PinState.LOW && b == PinState.LOW) {
            return PinState.LOW;
        } else if (a == PinState.HIGH || b == PinState.HIGH) {
            return PinState.HIGH;
        }
        return PinState.UNKNOWN;
    }
}
