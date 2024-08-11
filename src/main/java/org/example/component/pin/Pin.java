package org.example.component.pin;

import org.example.component.records.PinConnection;
import org.example.edu.uj.po.simulation.interfaces.PinState;

import java.util.List;
import java.util.Set;

/*
    Pin class is an abstract class that represents a pin in a component.
    Pin is Observer and Publisher at the same time.
    Publisher:  Pin has list of observers, can add, remove and notify them about changes.
                The observers are publishers at the same time.
    Observer:   Pin observes changes in other pins and reacts to them.
                It has update method that is called when observed pin changes its state.
 */
public abstract class Pin implements Observer, Publisher {

    private int parentComponentId;

    public abstract int getId();

    public abstract void setId(int id);

    public abstract PinState getState();

    public abstract void setState(PinState state);

    public abstract PinType getType();

    public abstract void setType(PinType type);

    public abstract boolean isConnectedToInput();

    public abstract void setConnectedToInput(boolean connectedToInput);
    public abstract boolean isConnectedToOutput();

    public abstract void setConnectedToOutput(boolean connectedToOutput);

    public boolean isConnected() {
        return isConnectedToInput() || isConnectedToOutput();
    }

    public abstract List<PinConnection> getConnectionsToOtherPins();

    public abstract void setConnectionsToOtherPins(List<PinConnection> connectionsToOtherPins);

    public abstract void addConnectionToOtherPins(PinConnection connectionToOtherPins);

    public abstract boolean isStateChanged();

    public abstract void setStateChanged(boolean stateChanged);

    public abstract void resetStateChanged();

    public abstract void update(PinState state);
    public abstract Set<Observer> getObservers();

    public abstract void addObserver(Observer observer);
    public abstract void removeObserver(Observer observer);
    public abstract void notifyObservers();
    public abstract void performStep();
    public abstract void setStateStep(PinState state);

    public boolean isOutputPin() {
        return getType().equals(PinType.OUT);
    }

    public boolean isInputPin() {
        return getType().equals(PinType.IN);
    }

    public int getParentComponentId() {
        return parentComponentId;
    }

    public void setParentComponentId(int parentComponentId) {
        this.parentComponentId = parentComponentId;
    }
}
