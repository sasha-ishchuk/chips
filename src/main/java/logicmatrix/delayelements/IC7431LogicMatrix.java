package logicmatrix.delayelements;

import logicmatrix.LogicMatrix;
import edu.uj.po.simulation.interfaces.PinState;

import java.util.List;

public class IC7431LogicMatrix extends LogicMatrix {

    @Override
    public void checkInputsValid(List<PinState> input) {
        if (input.size() != 8) {
            throw new IllegalArgumentException("IC7431LogicMatrix requires 8 input pins");
        }
    }

    @Override
    public List<PinState> map(List<PinState> input) {
        return List.of(
                simulateNot(input.get(0)),
                simulateIdentity(input.get(1)),
                simulateNand2(input.get(2), input.get(3)),
                simulateNand2(input.get(4), input.get(5)),
                simulateIdentity(input.get(6)),
                simulateNot(input.get(7))
        );
    }
}
