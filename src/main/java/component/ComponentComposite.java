//package sasha.po.example.component;
//
//import sasha.po.example.component.pin.Pin;
//import sasha.po.example.component.pin.PinType;
//import sasha.po.example.interfaces.ComponentPinState;
//import sasha.po.example.interfaces.PinState;
//import sasha.po.example.interfaces.ShortCircuitException;
//import sasha.po.example.interfaces.UnknownStateException;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class ComponentComposite implements LogicComponent{
//
//    Map<Integer, LogicComponent> components = new HashMap<>();
//
//    Map<Integer, ChipComponent> chipComponents = new HashMap<>();
//    Map<Integer, PinHeaderComponent> inPinHeaderComponents = new HashMap<>();
//    Map<Integer, LogicComponent> outPinHeaderComponents = new HashMap<>();
//
//    public void addComponent(LogicComponent component) {
//        components.put(1, component);
//    }
//
//    public void removeComponent(LogicComponent component) {
//        components.remove(1);
//    }
//
//    @Override
//    public int getId() {
//        return 0;
//    }
//
//    @Override
//    public void setId(int id) {
//
//    }
//
//    @Override
//    public Pin getPin(int pinId) {
//        return null;
//    }
//
//    @Override
//    public List<Pin> simulate() {
//        for (Map.Entry<Integer, LogicComponent> entry : components.entrySet()) {
//            entry.getValue().simulate();
//        }
//        return null;
//    }
//
//    private Map<Integer, Pin> globalPinIds = new HashMap<>();
//    private Map<Integer, ConnectedPin> pinConnections = new HashMap<>();
//    private Map<Pin, Pin> connections = new HashMap<>();
//
//    int globalPinId = 0;
//
//    public void connect(LogicComponent component1, Pin pin1, LogicComponent component2, Pin pin2) throws ShortCircuitException {
//        if (pin1.getType().equals(PinType.OUT) && pin2.getType().equals(PinType.OUT)) {
//            throw new ShortCircuitException();
//        }
//        connections.put(pin1, pin2);
//    }
//
//    public void setStationaryState(Set<ComponentPinState> states) throws UnknownStateException {
//        for (ComponentPinState state : states) {
//            int componentId = state.componentId();
//            int pinId = state.pinId();
//            PinState pinState = state.state();
//            LogicComponent component = components.get(componentId);
//            component.getPin(pinId).setState(pinState);
//        }
//
//
//    }
//
//    public Map<Integer, Set<ComponentPinState>> performSimulation(Set<ComponentPinState> states0, int ticks) throws UnknownStateException {
//        Map<Integer, Set<ComponentPinState>> result = new HashMap<>();
//        for (int i = 0; i < ticks; i++) {
//            Set<ComponentPinState> states = performTick(states0);
//            result.put(i, states);
//            states0 = states;
//        }
//        return result;
//    }
//
//    private Set<ComponentPinState> performTick(Set<ComponentPinState> states) {
//        return null;
//    }
//
//    private int incrementGlobalPinId() {
//        return globalPinId++;
//    }
//
//    //implement the connect method for IC7400 and IC7420
//
//
//}
