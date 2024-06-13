package logicmatrix.buffer;

import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.LogicMatrix;

import java.util.List;

public class IC7434LogicMatrix extends LogicMatrix {

    @Override
    public void checkInputsValid(List<PinState> input) {
        if (input.size() != 6) {
            throw new IllegalArgumentException("IC7434LogicMatrix requires 6 input pins");
        }
    }

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
}
