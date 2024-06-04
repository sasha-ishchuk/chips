package logicmatrix;

import edu.uj.po.simulation.interfaces.PinState;

import java.util.List;

public abstract class LogicMatrix {

    //template method
    public List<PinState> getResult(List<PinState> input) {
        return map(input);
    }

    public abstract List<PinState> map(List<PinState> input);

    public boolean isAllPinStatesKnown(List<PinState> input) {
        return input.stream().noneMatch(pinState -> pinState == PinState.UNKNOWN);
    }

    public boolean isAnyPinStateUnknown(List<PinState> input) {
        return input.stream().anyMatch(pinState -> pinState == PinState.UNKNOWN);
    }
}
