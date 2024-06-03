package logicmatrix.not;

import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.LogicMatrix;

import java.util.List;

public class IC7404LogicMatrix extends LogicMatrix {

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

    private PinState simulateNot(PinState a) {
        if (a == PinState.UNKNOWN) {
            return PinState.UNKNOWN;
        }
        return a == PinState.HIGH ? PinState.LOW : PinState.HIGH;
    }
}
