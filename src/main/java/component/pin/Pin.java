package component.pin;

import component.ComponentType;
import component.records.PinConnection;
import edu.uj.po.simulation.interfaces.PinState;

import java.util.ArrayList;
import java.util.List;

public abstract class Pin implements Observer, Publisher {

    private ComponentType parentComponentType;

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

    public abstract List<PinConnection> getConnectionsToOtherPins();

    public abstract void setConnectionsToOtherPins(List<PinConnection> connectionsToOtherPins);

    public abstract void addConnectionToOtherPins(PinConnection connectionToOtherPins);

    public abstract boolean isStateChanged();

    public abstract void setStateChanged(boolean stateChanged);

    public abstract void resetStateChanged();

    public abstract void update(PinState state);

    public abstract void addObserver(Observer observer);
    public abstract void removeObserver(Observer observer);
    public abstract void notifyObservers();
    public abstract void applyNextStep();

    public boolean isOutputPin() {
        return getType().equals(PinType.OUT);
    }

    public boolean isInputPin() {
        return getType().equals(PinType.IN);
    }
    public abstract void setStateStep(PinState state);

    public void setParentComponentType(ComponentType type) {
        this.parentComponentType = type;
    }

    public ComponentType getParentComponentType() {
        return parentComponentType;
    }
}
