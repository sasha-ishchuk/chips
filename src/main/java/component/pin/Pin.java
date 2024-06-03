package component.pin;

import component.records.PinConnection;
import edu.uj.po.simulation.interfaces.PinState;

import java.util.ArrayList;
import java.util.List;

public class Pin {
    private int id;
    private PinState state;
    private PinType type;

    private boolean connectedToInput = false;
    private boolean connectedToOutput = false;

    private List<PinConnection> connectionsToOtherPins = new ArrayList<>();

    public Pin(int id, PinState state, PinType type) {
        this.id = id;
        this.state = state;
        this.type = type;
    }

    public Pin(int id, PinType type) {
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
}
