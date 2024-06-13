package logicmatrix;

import edu.uj.po.simulation.interfaces.PinState;

import java.util.List;

public abstract class LogicMatrix {

    // template method
    public List<PinState> getResult(List<PinState> input) {
        checkInputsValid(input);
        return map(input);
    }

    // abstract methods to be implemented by subclasses
    public abstract void checkInputsValid(List<PinState> input);
    public abstract List<PinState> map(List<PinState> input);

    // helper method
    public boolean isAnyPinStateUnknown(List<PinState> input) {
        return input.stream().anyMatch(pinState -> pinState == PinState.UNKNOWN);
    }

    // methods to be used by subclasses
    public PinState simulateIdentity(PinState a) {
        return a;
    }

    public PinState simulateNot(PinState a) {
        if (a == PinState.UNKNOWN) {
            return PinState.UNKNOWN;
        }
        return a == PinState.HIGH ? PinState.LOW : PinState.HIGH;
    }

    public PinState simulateAnd2(PinState a, PinState b) {
        if (a == PinState.HIGH && b == PinState.HIGH) {
            return PinState.HIGH;
        }
        else if (a == PinState.LOW || b == PinState.LOW) {
            return PinState.LOW;
        }
        return PinState.UNKNOWN;
    }

    public PinState simulateNand2(PinState a, PinState b) {
        if (a == PinState.HIGH && b == PinState.HIGH) {
            return PinState.LOW;
        }
        else if (a == PinState.LOW || b == PinState.LOW) {
            return PinState.HIGH;
        }
        return PinState.UNKNOWN;
    }
}
