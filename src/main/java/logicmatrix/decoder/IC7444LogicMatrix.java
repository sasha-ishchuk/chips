package logicmatrix.decoder;

import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.LogicMatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IC7444LogicMatrix extends LogicMatrix {

    @Override
    public void checkInputsValid(List<PinState> input) {
        if (input.size() != 4) {
            throw new IllegalArgumentException("IC7444LogicMatrix requires 4 input pins");
        }
    }

    @Override
    public List<PinState> map(List<PinState> input) {
        // 0-D, 1-C, 2-B, 3-A
        return simulateGraysDecoder(input.get(3), input.get(2), input.get(1), input.get(0));
    }

    private List<PinState> simulateGraysDecoder(PinState a, PinState b, PinState c, PinState d) {
        List<PinState> output = new ArrayList<>(Collections.nCopies(10, PinState.HIGH));
        if (a == PinState.LOW && b == PinState.LOW && c == PinState.HIGH && d == PinState.LOW) {
            output.set(0, PinState.LOW);
        } else if (a == PinState.LOW && b == PinState.HIGH && c == PinState.HIGH && d == PinState.LOW) {
            output.set(1, PinState.LOW);
        } else if (a == PinState.LOW && b == PinState.HIGH && c == PinState.HIGH && d == PinState.HIGH) {
            output.set(2, PinState.LOW);
        } else if (a == PinState.LOW && b == PinState.HIGH && c == PinState.LOW && d == PinState.HIGH) {
            output.set(3, PinState.LOW);
        } else if (a == PinState.LOW && b == PinState.HIGH && c == PinState.LOW && d == PinState.LOW) {
            output.set(4, PinState.LOW);
        } else if (a == PinState.HIGH && b == PinState.HIGH && c == PinState.LOW && d == PinState.LOW) {
            output.set(5, PinState.LOW);
        } else if (a == PinState.HIGH && b == PinState.HIGH && c == PinState.LOW && d == PinState.HIGH) {
            output.set(6, PinState.LOW);
        } else if (a == PinState.HIGH && b == PinState.HIGH && c == PinState.HIGH && d == PinState.HIGH) {
            output.set(7, PinState.LOW);
        } else if (a == PinState.HIGH && b == PinState.HIGH && c == PinState.HIGH && d == PinState.LOW) {
            output.set(8, PinState.LOW);
        } else if (a == PinState.HIGH && b == PinState.LOW && c == PinState.HIGH && d == PinState.LOW) {
            output.set(9, PinState.LOW);
        }
        return output;
    }
}
