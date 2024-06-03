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

    public List<Pin> simulate() {
        List<PinState> inputStates = setInPins().stream()
                .map(Pin::getState)
                .toList();
        List<PinState> outputStates = getLogicMatrix().getResult(inputStates);
        List<Pin> outPins = setOutPins();
        for (int i = 0; i < outputStates.size(); i++) {
            outPins.get(i).setState(outputStates.get(i));
        }
        updateConnectedPinsWithStates();
//        notifyObservers();
        return outPins;
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
        return stateChanged;
    }

    public void setStateChanged(boolean stateChanged) {
        this.stateChanged = stateChanged;
    }
}
