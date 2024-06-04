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
        for (ConnectedPinsWithStates connectedPin : connectedPinsWithStates) {
            if (connectedPin.componentId1() == getId()) {
                Pin pin = getPin(connectedPin.pinId1());
                if (pin != null && !(pin.getState().equals(connectedPin.state1()))) {
                    pin.setState(connectedPin.state1());
                } else if (pin != null) {
                    pin.setStateChanged(false);
                }
            }
            if (connectedPin.componentId2() == getId()) {
                Pin pin = getPin(connectedPin.pinId2());
                if (pin != null && !(pin.getState().equals(connectedPin.state2()))) {
                    pin.setState(connectedPin.state2());
                } else if (pin != null) {
                    pin.setStateChanged(false);
                }
            }
        }
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
            if (connectedPin.componentId1() == getId()) {
                PinState newState = getPin(connectedPin.pinId1()).getState();
                newConnectedPinsWithStates.add(new ConnectedPinsWithStates(
                        connectedPin.componentId1(), connectedPin.pinId1(), newState,
                        connectedPin.componentId2(), connectedPin.pinId2(), newState));
            } else if (connectedPin.componentId2() == getId()) {
                PinState newState = getPin(connectedPin.pinId2()).getState();
                newConnectedPinsWithStates.add(new ConnectedPinsWithStates(
                        connectedPin.componentId1(), connectedPin.pinId1(), newState,
                        connectedPin.componentId2(), connectedPin.pinId2(), newState));
            }
        }
        clearConnectedPinsWithStates();
        setConnectedPinsWithStates(newConnectedPinsWithStates);
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
