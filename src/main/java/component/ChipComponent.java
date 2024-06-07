package component;

import component.pin.Observer;
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

    private boolean stateChanged = false;

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

    public void addConnectedComponentId(int connectedComponentId) {
        connectedComponentsIds.add(connectedComponentId);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private List<Pin> pinsToUpdateInNextCycle = new ArrayList<>();

    public List<Pin> getPinsToUpdateInNextCycle() {
        return pinsToUpdateInNextCycle;
    }

    public void setPinsToUpdateInNextCycle(List<Pin> pinsToUpdateInNextCycle) {
        this.pinsToUpdateInNextCycle = pinsToUpdateInNextCycle;
    }

    public List<Pin> simulate() {
        List<PinState> inputStates = setInPins().stream()
                .map(Pin::getState)
                .toList();
        List<PinState> outputStates = getLogicMatrix().getResult(inputStates);
        List<Pin> outPins = setOutPins();
        for (int i = 0; i < outputStates.size(); i++) {
            Pin pin = outPins.get(i);
            pin.setState(outputStates.get(i));
//            pin.notifyObservers();
        }
        return outPins;
    }

    private List<Pin> setInPins() {
        return getPins().stream()
                .filter(pin -> pin.getType().equals(PinType.IN))
                .toList();
    }

    private List<Pin> setOutPins() {
        return getPins().stream()
                .filter(pin -> pin.getType().equals(PinType.OUT))
                .toList();
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

    public void setStateChanged(boolean stateChanged) {
        this.stateChanged = stateChanged;
    }

    public void resetStateChanged() {
        List<Pin> currentPins = getPins();
        for (Pin pin : currentPins) {
            if (pin != null) {
                pin.resetStateChanged();
            }
        }
    }
}
