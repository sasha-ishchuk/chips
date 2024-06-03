package logicmatrix.buffer;

import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.LogicMatrix;

import java.util.List;

public class IC7434LogicMatrix extends LogicMatrix {
    @Override
    public List<PinState> map(List<PinState> input) {
        return List.of(
                simulateIdentity(input.get(0)),
                simulateIdentity(input.get(1)),
                simulateIdentity(input.get(2)),
                simulateIdentity(input.get(3)),
                simulateIdentity(input.get(4)),
                simulateIdentity(input.get(5))
        );
    }

    private PinState simulateIdentity(PinState a) {
        if (a == PinState.UNKNOWN) {
            return PinState.UNKNOWN;
        }
        return a == PinState.HIGH ? PinState.HIGH : PinState.LOW;
    }
}
