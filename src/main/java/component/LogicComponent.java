package component;

import component.pin.Pin;
import component.records.ConnectedPinsWithStates;

import java.util.List;
import java.util.Set;

public interface LogicComponent extends Publisher, Observer {

    ComponentType getComponentType();
    void setComponentType(ComponentType componentType);
    int getId();
    void setId(int id);
    List<Pin> setDefaultPins();
    List<Pin> getPins();
    Pin getPin(int pinId);
    List<Pin> simulate();

    List<ConnectedPinsWithStates> getConnectedPinsWithStates();
    void setConnectedPinsWithStates(List<ConnectedPinsWithStates> connectedPinsWithStates);
    void clearConnectedPinsWithStates();

    Set<Integer> getConnectedComponentsIds();
    void setConnectedComponentsIds(Set<Integer> connectedComponentsIds);
    void addConnectedComponentId(int connectedComponentId);

    boolean hasStateChanged();

    void resetStateChanged();
}
