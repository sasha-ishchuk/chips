package component;

import component.pin.Observer;
import component.pin.Pin;
import component.pin.PinType;
import component.records.ConnectedPinsWithStates;
import edu.uj.po.simulation.interfaces.PinState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class PinHeaderComponent implements LogicComponent {

    List<ConnectedPinsWithStates> connectedPinsWithStates = new ArrayList<>();
    Set<Observer> observers = new HashSet<>();

    Set<Integer> connectedComponentsIds = new HashSet<>();

    private boolean stateChanged = false;

    public abstract void setSize(int size);
    public abstract void setId(int id);

    public abstract int getId();
    public abstract int getSize();

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

    private List<Pin> pinsToUpdateInNextCycle = new ArrayList<>();

    public List<Pin> getPinsToUpdateInNextCycle() {
        return pinsToUpdateInNextCycle;
    }

    public void setPinsToUpdateInNextCycle(List<Pin> pinsToUpdateInNextCycle) {
        this.pinsToUpdateInNextCycle = pinsToUpdateInNextCycle;
    }

    @Override
    public List<Pin> simulate() {
        for (Pin pin : getPins()) {
            pin.notifyObservers();
        }
        return getPins();
    }

    public void step() {
        for (Pin pin : getPins()) {
            if (pin != null) {
                pin.applyNextStep();
            }
        }
    }

    public boolean hasStateChanged() {
        for (int i = 1; i <= getSize(); i++) {
            Pin pin = getPin(i);
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
        for (int i = 1; i <= getSize(); i++) {
            Pin pin = getPin(i);
            if (pin != null) {
                pin.resetStateChanged();
            }
        }
    }
}
