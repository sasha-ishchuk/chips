package component;

import component.pin.Pin;
import component.records.ConnectedPinsWithStates;

import java.util.List;
import java.util.Set;

public interface LogicComponent {

    int getId();
    void setId(int id);
    List<Pin> setDefaultPins();
    List<Pin> getPins();
    Pin getPin(int pinId);
    ComponentType getComponentType();
    void setComponentType(ComponentType componentType);
    void simulate();
    List<ConnectedPinsWithStates> getConnectedPinsWithStates();
    void setConnectedPinsWithStates(List<ConnectedPinsWithStates> connectedPinsWithStates);
    Set<Integer> getConnectedComponentsIds();
    void setConnectedComponentsIds(Set<Integer> connectedComponentsIds);
    void addConnectedComponentId(int connectedComponentId);
    boolean hasStateChanged();
    void resetStateChanged();
    void step();
}
