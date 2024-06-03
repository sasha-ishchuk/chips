package logicmatrix.multiplexer;

import logicmatrix.LogicMatrix;
import edu.uj.po.simulation.interfaces.PinState;

import java.util.List;

public class IC74152LogicMatrix extends LogicMatrix {

    @Override
    public List<PinState> map(List<PinState> input) {
        if (!isAllPinStatesKnown(input)) {
            return List.of(PinState.UNKNOWN);
        }
        PinState a = input.get(0);
        PinState b = input.get(1);
        PinState c = input.get(2);
        PinState d0 = input.get(3);
        PinState d1 = input.get(4);
        PinState d2 = input.get(5);
        PinState d3 = input.get(6);
        PinState d4 = input.get(7);
        PinState d5 = input.get(8);
        PinState d6 = input.get(9);
        PinState d7 = input.get(10);

        if (a.equals(PinState.LOW) && b.equals(PinState.LOW) && c.equals(PinState.LOW)) {
            return List.of(simulateNot(d0));
        } else if (a.equals(PinState.HIGH) && b.equals(PinState.LOW) && c.equals(PinState.LOW)) {
            return List.of(simulateNot(d1));
        } else if (a.equals(PinState.LOW) && b.equals(PinState.HIGH) && c.equals(PinState.LOW)) {
            return List.of(simulateNot(d2));
        } else if (a.equals(PinState.HIGH) && b.equals(PinState.HIGH) && c.equals(PinState.LOW)) {
            return List.of(simulateNot(d3));
        } else if (a.equals(PinState.LOW) && b.equals(PinState.LOW) && c.equals(PinState.HIGH)) {
            return List.of(simulateNot(d4));
        } else if (a.equals(PinState.HIGH) && b.equals(PinState.LOW) && c.equals(PinState.HIGH)) {
            return List.of(simulateNot(d5));
        } else if (a.equals(PinState.LOW) && b.equals(PinState.HIGH) && c.equals(PinState.HIGH)) {
            return List.of(simulateNot(d6));
        } else if (a.equals(PinState.HIGH) && b.equals(PinState.HIGH) && c.equals(PinState.HIGH)) {
            return List.of(simulateNot(d7));
        }
        return null;
    }

    private PinState simulateNot(PinState a) {
        return a == PinState.HIGH ? PinState.LOW : PinState.HIGH;
    }
}
