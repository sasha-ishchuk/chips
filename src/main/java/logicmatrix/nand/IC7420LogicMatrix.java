package logicmatrix.nand;

import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.LogicMatrix;

import java.util.List;

public class IC7420LogicMatrix extends LogicMatrix {

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
        } else {
            return PinState.HIGH;
        }
    }
}
