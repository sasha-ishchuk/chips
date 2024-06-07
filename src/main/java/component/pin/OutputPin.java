package component.pin;

import component.records.PinConnection;
import edu.uj.po.simulation.interfaces.PinState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OutputPin extends Pin implements Publisher {
    private int id;
    private PinState state;
    private PinState stateStep;
    private PinType type;

    Set<Observer> observers = new HashSet<>();

    private boolean connectedToInput = false;
    private boolean connectedToOutput = false;

    private boolean stateChanged = false;

    private List<PinConnection> connectionsToOtherPins = new ArrayList<>();

    public OutputPin(int id, PinState state, PinType type) {
        this.id = id;
        this.state = state;
        this.type = type;
    }

    public OutputPin(int id, PinType type) {
        this.id = id;
        this.state = PinState.UNKNOWN;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PinState getState() {
        return state;
    }

    public void setState(PinState state) {
        this.state = state;
        this.stateChanged = true;
    }

    public void setStateStep(PinState state) {
        this.stateStep = state;
        if (this.type.equals(PinType.IN)) {
            applyNextStep();
        }
    }

    public void applyNextStep() {
        if (stateStep != null && stateStep != state) {
            setState(stateStep);
            notifyObservers();
        } else {
            this.stateChanged = false;
        }
        this.stateStep = null;
    }

    public PinType getType() {
        return type;
    }

    public void setType(PinType type) {
        this.type = type;
    }

    public boolean isConnectedToInput() {
        return connectedToInput;
    }

    public void setConnectedToInput(boolean connectedToInput) {
        this.connectedToInput = connectedToInput;
    }

    public boolean isConnectedToOutput() {
        return connectedToOutput;
    }

    public void setConnectedToOutput(boolean connectedToOutput) {
        this.connectedToOutput = connectedToOutput;
    }

    public List<PinConnection> getConnectionsToOtherPins() {
        return connectionsToOtherPins;
    }

    public void setConnectionsToOtherPins(List<PinConnection> connectionsToOtherPins) {
        this.connectionsToOtherPins = connectionsToOtherPins;
    }

    public void addConnectionToOtherPins(PinConnection connectionToOtherPins) {
        this.connectionsToOtherPins.add(connectionToOtherPins);
    }

    public boolean isStateChanged() {
        return stateChanged;
    }

    public void setStateChanged(boolean stateChanged) {
        this.stateChanged = stateChanged;
    }

    public void resetStateChanged() {
        this.stateChanged = false;
    }

    @Override
    public void update(PinState state) {
        setStateStep(state);
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
            observer.update(this.getState());
        }
    }
}
