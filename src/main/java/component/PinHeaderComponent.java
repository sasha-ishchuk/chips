package component;

import component.pin.Pin;
import component.records.ConnectedPinsWithStates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class PinHeaderComponent implements LogicComponent {

    List<ConnectedPinsWithStates> connectedPinsWithStates = new ArrayList<>();

    Set<Integer> connectedComponentsIds = new HashSet<>();

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

    public Set<Integer> getConnectedComponentsIds() {
        return connectedComponentsIds;
    }

    public void setConnectedComponentsIds(Set<Integer> connectedComponentsIds) {
        this.connectedComponentsIds = connectedComponentsIds;
    }

    public void addConnectedComponentId(int connectedComponentId) {
        connectedComponentsIds.add(connectedComponentId);
    }

    @Override
    public void simulate() {
        // do nothing
    }

    public void step() {
        for (Pin pin : getPins()) {
            if (pin != null) {
                pin.performStep();
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

    public void resetStateChanged() {
        for (int i = 1; i <= getSize(); i++) {
            Pin pin = getPin(i);
            if (pin != null) {
                pin.resetStateChanged();
            }
        }
    }
}
