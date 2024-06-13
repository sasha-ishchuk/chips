package logicmatrix;

import edu.uj.po.simulation.interfaces.PinState;

import java.util.List;

public abstract class LogicMatrix {

    //template method
    public List<PinState> getResult(List<PinState> input) {
        checkInputsValid(input);
        return map(input);
    }

    public abstract void checkInputsValid(List<PinState> input);
    public abstract List<PinState> map(List<PinState> input);

    public boolean isAnyPinStateUnknown(List<PinState> input) {
        return input.stream().anyMatch(pinState -> pinState == PinState.UNKNOWN);
    }
}
