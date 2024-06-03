package component;

import component.pin.Pin;
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

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.updatePinStates(connectedPinsWithStates);
        }
    }

//    @Override
//    public void updatePinStates(List<ConnectedPinsWithStates> connectedPinsWithStates) {
//        for (ConnectedPinsWithStates connectedPin : connectedPinsWithStates) {
//            if (connectedPin.componentId2() == getId()) {
//                getPin(connectedPin.pinId2()).setState(connectedPin.state2());
//            }
//        }
//    }

    @Override
    public void updatePinStates(List<ConnectedPinsWithStates> connectedPinsWithStates) {
        List<Pin> currentPins = getPins();
        for (ConnectedPinsWithStates connectedPin : connectedPinsWithStates) {
            if (connectedPin.componentId2() == getId() || connectedPin.componentId1() == getId()) {
                getPin(connectedPin.pinId2()).setState(connectedPin.state2());
            }
        }
//        clearConnectedPinsWithStates();
        List<Pin> newPins = getPins();
        setStateChanged(isPinStateChanged(currentPins, newPins));
    }

    private boolean isPinStateChanged(List<Pin> pins, List<Pin> newPins) {
        for (int i = 0; i < pins.size(); i++) {
            if (pins.get(i).getState() != newPins.get(i).getState()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Pin> simulate() {
        updateConnectedPinsWithStates();
        notifyObservers();
        return getPins();
    }

    private void updateConnectedPinsWithStates() {
        List<ConnectedPinsWithStates> newConnectedPinsWithStates = new ArrayList<>();
        for (ConnectedPinsWithStates connectedPin : getConnectedPinsWithStates()) {
            PinState newState = getPin(connectedPin.pinId1()).getState();
            newConnectedPinsWithStates.add(new ConnectedPinsWithStates(
                    connectedPin.componentId1(), connectedPin.pinId1(), newState,
                    connectedPin.componentId2(), connectedPin.pinId2(), newState));
        }
        clearConnectedPinsWithStates();
        setConnectedPinsWithStates(newConnectedPinsWithStates);
    }

    public boolean hasStateChanged() {
        return stateChanged;
    }

    public void setStateChanged(boolean stateChanged) {
        this.stateChanged = stateChanged;
    }
}
