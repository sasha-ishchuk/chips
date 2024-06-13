package logicmatrix.adder;

import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.LogicMatrix;

import java.util.Collections;
import java.util.List;

public class IC7482LogicMatrix extends LogicMatrix {

    @Override
    public void checkInputsValid(List<PinState> input) {
        if (input.size() != 5) {
            throw new IllegalArgumentException("IC7482LogicMatrix requires 5 input pins");
        }
    }

    @Override
    public List<PinState> map(List<PinState> input) {
        // 0-A1, 1-B1, 2-C0, 3-B2, 4-A2
        return simulateAdder(input.get(0), input.get(1), input.get(4), input.get(3), input.get(2));
    }

    private List<PinState> simulateAdder(PinState a1, PinState b1, PinState a2, PinState b2, PinState c0) {
        if (c0 == PinState.LOW) {
            return simulateAdderLowC(a1, b1, a2, b2);
        } else if (c0 == PinState.HIGH) {
            return simulateAdderHighC(a1, b1, a2, b2);
        }
        return Collections.nCopies(3, PinState.UNKNOWN);
    }

    private List<PinState> simulateAdderLowC(PinState a1, PinState b1, PinState a2, PinState b2) {
        if (a1 == PinState.LOW && b1 == PinState.LOW && a2 == PinState.LOW && b2 == PinState.LOW) {
            return List.of(PinState.LOW, PinState.LOW, PinState.LOW); // E1, E2, C2
        } else if (a1 == PinState.HIGH && b1 == PinState.LOW && a2 == PinState.LOW && b2 == PinState.LOW) {
            return List.of(PinState.HIGH, PinState.LOW, PinState.LOW); // E1, E2, C2
        } else if (a1 == PinState.LOW && b1 == PinState.HIGH && a2 == PinState.LOW && b2 == PinState.LOW) {
            return List.of(PinState.HIGH, PinState.LOW, PinState.LOW); // E1, E2, C2
        } else if (a1 == PinState.HIGH && b1 == PinState.HIGH && a2 == PinState.LOW && b2 == PinState.LOW) {
            return List.of(PinState.LOW, PinState.HIGH, PinState.LOW); // E1, E2, C2
        } else if (a1 == PinState.LOW && b1 == PinState.LOW && a2 == PinState.HIGH && b2 == PinState.LOW) {
            return List.of(PinState.LOW, PinState.HIGH, PinState.LOW); // E1, E2, C2
        } else if (a1 == PinState.HIGH && b1 == PinState.LOW && a2 == PinState.HIGH && b2 == PinState.LOW) {
            return List.of(PinState.HIGH, PinState.HIGH, PinState.LOW); // E1, E2, C2
        } else if (a1 == PinState.LOW && b1 == PinState.HIGH && a2 == PinState.HIGH && b2 == PinState.LOW) {
            return List.of(PinState.HIGH, PinState.HIGH, PinState.LOW); // E1, E2, C2
        } else if (a1 == PinState.HIGH && b1 == PinState.HIGH && a2 == PinState.HIGH && b2 == PinState.LOW) {
            return List.of(PinState.LOW, PinState.LOW, PinState.HIGH); // E1, E2, C2
        } else if (a1 == PinState.LOW && b1 == PinState.LOW && a2 == PinState.HIGH && b2 == PinState.HIGH) {
            return List.of(PinState.LOW, PinState.HIGH, PinState.LOW); // E1, E2, C2
        } else if (a1 == PinState.HIGH && b1 == PinState.LOW && a2 == PinState.HIGH && b2 == PinState.HIGH) {
            return List.of(PinState.HIGH, PinState.LOW, PinState.HIGH); // E1, E2, C2
        } else if (a1 == PinState.LOW && b1 == PinState.HIGH && a2 == PinState.HIGH && b2 == PinState.HIGH) {
            return List.of(PinState.HIGH, PinState.LOW, PinState.HIGH); // E1, E2, C2
        } else if (a1 == PinState.HIGH && b1 == PinState.HIGH && a2 == PinState.HIGH && b2 == PinState.HIGH) {
            return List.of(PinState.LOW, PinState.HIGH, PinState.HIGH); // E1, E2, C2
        }
        return Collections.nCopies(3, PinState.UNKNOWN);
    }

    private List<PinState> simulateAdderHighC(PinState a1, PinState b1, PinState a2, PinState b2) {
        if (a1 == PinState.LOW && b1 == PinState.LOW && a2 == PinState.LOW && b2 == PinState.LOW) {
            return List.of(PinState.HIGH, PinState.LOW, PinState.LOW); // E1, E2, C2
        } else if (a1 == PinState.HIGH && b1 == PinState.LOW && a2 == PinState.LOW && b2 == PinState.LOW) {
            return List.of(PinState.LOW, PinState.HIGH, PinState.LOW); // E1, E2, C2
        } else if (a1 == PinState.LOW && b1 == PinState.HIGH && a2 == PinState.LOW && b2 == PinState.LOW) {
            return List.of(PinState.LOW, PinState.HIGH, PinState.LOW); // E1, E2, C2
        } else if (a1 == PinState.HIGH && b1 == PinState.HIGH && a2 == PinState.LOW && b2 == PinState.LOW) {
            return List.of(PinState.HIGH, PinState.HIGH, PinState.LOW); // E1, E2, C2
        } else if (a1 == PinState.LOW && b1 == PinState.LOW && a2 == PinState.HIGH && b2 == PinState.LOW) {
            return List.of(PinState.HIGH, PinState.HIGH, PinState.LOW); // E1, E2, C2
        } else if (a1 == PinState.HIGH && b1 == PinState.LOW && a2 == PinState.HIGH && b2 == PinState.LOW) {
            return List.of(PinState.LOW, PinState.LOW, PinState.HIGH); // E1, E2, C2
        } else if (a1 == PinState.LOW && b1 == PinState.HIGH && a2 == PinState.HIGH && b2 == PinState.LOW) {
            return List.of(PinState.LOW, PinState.LOW, PinState.HIGH); // E1, E2, C2
        } else if (a1 == PinState.HIGH && b1 == PinState.HIGH && a2 == PinState.HIGH && b2 == PinState.LOW) {
            return List.of(PinState.HIGH, PinState.LOW, PinState.HIGH); // E1, E2, C2
        } else if (a1 == PinState.LOW && b1 == PinState.LOW && a2 == PinState.HIGH && b2 == PinState.HIGH) {
            return List.of(PinState.HIGH, PinState.LOW, PinState.HIGH); // E1, E2, C2
        } else if (a1 == PinState.HIGH && b1 == PinState.LOW && a2 == PinState.HIGH && b2 == PinState.HIGH) {
            return List.of(PinState.LOW, PinState.HIGH, PinState.HIGH); // E1, E2, C2
        } else if (a1 == PinState.LOW && b1 == PinState.HIGH && a2 == PinState.HIGH && b2 == PinState.HIGH) {
            return List.of(PinState.LOW, PinState.HIGH, PinState.HIGH); // E1, E2, C2
        } else if (a1 == PinState.HIGH && b1 == PinState.HIGH && a2 == PinState.HIGH && b2 == PinState.HIGH) {
            return List.of(PinState.HIGH, PinState.HIGH, PinState.HIGH); // E1, E2, C2
        }
        return Collections.nCopies(3, PinState.UNKNOWN);
    }
}
