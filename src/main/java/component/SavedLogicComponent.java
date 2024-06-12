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
            this.chipPins.put(pin.getId(), pin.getState());
            this.componentObservers.put(pin.getId(), pin.getObservers());
        }
        this.connectionIds.addAll(component.getConnectedComponentsIds());
        this.componentType = component.getComponentType();
        this.componentConnectedPinsWithStates.addAll(component.getConnectedPinsWithStates());
    }

    public void loadChip(LogicComponent component) {
        for (Map.Entry<Integer, PinState> entry : this.chipPins.entrySet()) {
            component.getPin(entry.getKey()).setState(entry.getValue());
        }
        for (Map.Entry<Integer, Set<Observer>> entry : this.componentObservers.entrySet()) {
            for (Observer observer : entry.getValue()) {
                component.getPin(entry.getKey()).addObserver(observer);
            }
        }
        component.setConnectedComponentsIds(this.connectionIds);
        component.setComponentType(this.componentType);
        component.setConnectedPinsWithStates(this.componentConnectedPinsWithStates);
    }
}
