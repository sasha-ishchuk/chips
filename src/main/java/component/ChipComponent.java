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

    private boolean stateChanged = false;

    private List<ConnectedPinsWithStates> connectedPinsWithStates = new ArrayList<>();

    private Set<Observer> observers = new HashSet<>();

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

    private List<Pin> pinsToUpdateInNextCycle = new ArrayList<>();

    public List<Pin> getPinsToUpdateInNextCycle() {
        return pinsToUpdateInNextCycle;
    }

    public void setPinsToUpdateInNextCycle(List<Pin> pinsToUpdateInNextCycle) {
        this.pinsToUpdateInNextCycle = pinsToUpdateInNextCycle;
    }

    @Override
    public void updatePinStates(List<ConnectedPinsWithStates> connectedPinsWithStates) {
        List<Pin> updateLater = new ArrayList<>();

        if (!getPinsToUpdateInNextCycle().isEmpty()) {
            for (Pin pin : getPinsToUpdateInNextCycle()) {
                getPin(pin.getId()).setState(pin.getState());
            }
            setPinsToUpdateInNextCycle(new ArrayList<>());
        }


        for (ConnectedPinsWithStates connectedPin : connectedPinsWithStates) {
            if (connectedPin.componentId1() == getId()) {
                Pin pin = getPin(connectedPin.pinId1());
                if (pin != null && !(pin.getState().equals(connectedPin.state1()))) {
                    if (pin.getType() == PinType.OUT) {
                        updateLater.add(pin);
                    } else {
                        pin.setState(connectedPin.state1());
                    }
                } else if (pin != null) {
                    pin.setStateChanged(false);
                }
            }
            if (connectedPin.componentId2() == getId()) {
                Pin pin = getPin(connectedPin.pinId2());
                if (pin != null && !(pin.getState().equals(connectedPin.state2()))) {
                    if (pin.getType() == PinType.OUT) {
                        updateLater.add(pin);
                    } else {
                        pin.setState(connectedPin.state2());
                    }
                } else if (pin != null) {
                    pin.setStateChanged(false);
                }
            }
        }
        setPinsToUpdateInNextCycle(updateLater);
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
            pin.setStateChanged(false);
        }
        updateConnectedPinsWithStates();
//        notifyObservers();
        return outPins;
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
        for (int i = 0; i < currentPins.size(); i++) {
            Pin pin = currentPins.get(i);
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
        for (int i = 0; i < currentPins.size(); i++) {
            Pin pin = currentPins.get(i);
            if (pin != null) {
                pin.resetStateChanged();
            }
        }
    }
}
