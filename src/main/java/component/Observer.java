package component;

import component.records.ConnectedPinsWithStates;

import java.util.List;

public interface Observer {
    void updatePinStates(List<ConnectedPinsWithStates> connectedPinsWithStates);
}
