package component;

import component.pin.Pin;
import component.records.ConnectedPinsWithStates;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.ShortCircuitException;
import edu.uj.po.simulation.interfaces.UnknownStateException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LogicComponentComposite implements LogicComponent{

    Map<Integer, LogicComponent> components = new HashMap<>();

    public void addComponent(LogicComponent component) {
        components.put(1, component);
    }

    public void removeComponent(LogicComponent component) {
        components.remove(1);
    }

    @Override
    public ComponentType getComponentType() {
        return null;
    }

    @Override
    public void setComponentType(ComponentType componentType) {

    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public List<Pin> setDefaultPins() {
        return null;
    }

    @Override
    public List<Pin> getPins() {
        return null;
    }

    @Override
    public Pin getPin(int pinId) {
        return null;
    }

    @Override
    public void simulate() {
        for (Map.Entry<Integer, LogicComponent> entry : components.entrySet()) {
            entry.getValue().simulate();
        }
    }

    @Override
    public List<ConnectedPinsWithStates> getConnectedPinsWithStates() {
        return null;
    }

    @Override
    public void setConnectedPinsWithStates(List<ConnectedPinsWithStates> connectedPinsWithStates) {

    }

    @Override
    public void clearConnectedPinsWithStates() {

    }

    @Override
    public Set<Integer> getConnectedComponentsIds() {
        return null;
    }

    @Override
    public void setConnectedComponentsIds(Set<Integer> connectedComponentsIds) {

    }

    @Override
    public void addConnectedComponentId(int connectedComponentId) {

    }

    @Override
    public boolean hasStateChanged() {
        return false;
    }

    @Override
    public void resetStateChanged() {

    }

    @Override
    public void step() {

    }

    private Map<Integer, Pin> globalPinIds = new HashMap<>();
    private Map<Pin, Pin> connections = new HashMap<>();

    int globalPinId = 0;

    public void connect(LogicComponent component1, Pin pin1, LogicComponent component2, Pin pin2) throws ShortCircuitException {
        //
    }

    public void setStationaryState(Set<ComponentPinState> states) throws UnknownStateException {
        for (ComponentPinState state : states) {
            int componentId = state.componentId();
            int pinId = state.pinId();
            PinState pinState = state.state();
            LogicComponent component = components.get(componentId);
            component.getPin(pinId).setState(pinState);
        }


    }

    public Map<Integer, Set<ComponentPinState>> performSimulation(Set<ComponentPinState> states0, int ticks) throws UnknownStateException {
        Map<Integer, Set<ComponentPinState>> result = new HashMap<>();
        for (int i = 0; i < ticks; i++) {
            Set<ComponentPinState> states = performTick(states0);
            result.put(i, states);
            states0 = states;
        }
        return result;
    }

    private Set<ComponentPinState> performTick(Set<ComponentPinState> states) {
        return null;
    }

    private int incrementGlobalPinId() {
        return globalPinId++;
    }

    public Set<ComponentPinState> simulation(Set<ComponentPinState> states0, int ticks) throws UnknownStateException {
        return null;
    }

    public Set<ComponentPinState> optimization(Set<ComponentPinState> states0, int ticks) throws UnknownStateException{
        return null;
    }

    public Set<ComponentPinState> stationaryState() throws UnknownStateException {
        return null;
    }
}
