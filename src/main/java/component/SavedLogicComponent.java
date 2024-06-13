package component;

import component.pin.Observer;
import component.pin.Pin;
import component.records.ConnectedPinsWithStates;
import edu.uj.po.simulation.interfaces.PinState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SavedLogicComponent {

    private final Map<Integer, PinState> chipPins = new HashMap<>();
    private final Map<Integer, Set<Observer>> componentObservers = new HashMap<>();
    private final Set<Integer> connectionIds = new HashSet<>();
    private final ComponentType componentType;
    private final List<ConnectedPinsWithStates> componentConnectedPinsWithStates = new ArrayList<>();

    public SavedLogicComponent(LogicComponent component) {
        for (Pin pin : component.getPins()) {
            chipPins.put(pin.getId(), pin.getState());
            componentObservers.put(pin.getId(), pin.getObservers());
        }
        connectionIds.addAll(component.getConnectedComponentsIds());
        componentType = component.getComponentType();
        componentConnectedPinsWithStates.addAll(component.getConnectedPinsWithStates());
    }

    public void loadChip(LogicComponent component) {
        for (Map.Entry<Integer, PinState> entry : chipPins.entrySet()) {
            component.getPin(entry.getKey()).setState(entry.getValue());
        }
        for (Map.Entry<Integer, Set<Observer>> entry : componentObservers.entrySet()) {
            for (Observer observer : entry.getValue()) {
                component.getPin(entry.getKey()).addObserver(observer);
            }
        }
        component.setConnectedComponentsIds(connectionIds);
        component.setComponentType(componentType);
        component.setConnectedPinsWithStates(componentConnectedPinsWithStates);
    }
}
