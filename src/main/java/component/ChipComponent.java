package component;

import component.pin.Pin;
import component.pin.PinType;
import component.records.ConnectedPinsWithStates;
import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.LogicMatrix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


// it was an interface before
public abstract class ChipComponent implements LogicComponent {

    private int id;
    private int code;
    private List<ConnectedPinsWithStates> connectedPinsWithStates = new ArrayList<>();
    private Set<Integer> connectedComponentsIds = new HashSet<>();

    public abstract LogicMatrix getLogicMatrix();

    public abstract List<Pin> getPins();

    public List<ConnectedPinsWithStates> getConnectedPinsWithStates() {
        return connectedPinsWithStates;
    }

    public void setConnectedPinsWithStates(List<ConnectedPinsWithStates> connectedPinsWithStates) {
        this.connectedPinsWithStates = connectedPinsWithStates;
    }

    public void clearConnectedPinsWithStates() {
        connectedPinsWithStates.clear();
    }

    public Set<Integer> getConnectedComponentsIds() {
        return connectedComponentsIds;
    }

    public void setConnectedComponentsIds(Set<Integer> connectedComponentsIds) {
        this.connectedComponentsIds = connectedComponentsIds;
    }

    public void removeConnectedComponentId(int id) {
        connectedComponentsIds.remove(id);
    }

    public void addConnectedComponentId(int connectedComponentId) {
        connectedComponentsIds.add(connectedComponentId);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void step() {
        for (Pin pin : getPins()) {
            if (pin != null) {
                pin.performStep();
            }
        }
    }

    public List<PinState> getInPins() {
        List<PinState> ins = new ArrayList<>();
        for (int i = 0; i < getPins().size(); i++) {
            if (getPins().get(i).getType() == PinType.IN) {
                ins.add(getPins().get(i).getState());
            }
        }
        return ins;
    }

    private void setOutPins(List<PinState> outs) {
        int index = 0;
        for (int i = 0; i < getPins().size(); i++) {
            if (getPins().get(i).getType() == PinType.OUT) {
                getPins().get(i).update(outs.get(index));
                index += 1;
            }
        }
    }

    @Override
    public void simulate() {
        List<PinState> ins = getInPins();
        List<PinState> outs = getLogicMatrix().map(ins);
        setOutPins(outs);
    }

    public boolean hasStateChanged() {
        List<Pin> currentPins = getPins();
        for (Pin pin : currentPins) {
            if (pin != null && pin.isStateChanged()) {
                return true;
            }
        }
        return false;
    }

    public void resetStateChanged() {
        List<Pin> currentPins = getPins();
        for (Pin pin : currentPins) {
            if (pin != null) {
                pin.resetStateChanged();
            }
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
