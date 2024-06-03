package org.example;

import component.ChipComponent;
import component.ComponentType;
import component.LogicComponent;
import component.PinHeaderComponent;
import component.chip.ChipCreator;
import component.chip.adder.IC7482Creator;
import component.chip.and.IC7408Creator;
import component.chip.and.IC7411Creator;
import component.chip.buffer.IC7434Creator;
import component.chip.decoder.IC7442Creator;
import component.chip.decoder.IC7444Creator;
import component.chip.delayelements.IC7431Creator;
import component.chip.demultiplexer.IC74138Creator;
import component.chip.multiplexer.IC74152Creator;
import component.chip.nand.IC7400Creator;
import component.chip.nand.IC7410Creator;
import component.chip.nand.IC7420Creator;
import component.chip.nor.IC7402Creator;
import component.chip.not.IC7404Creator;
import component.chip.or.IC7432Creator;
import component.pin.Pin;
import component.pin.PinType;
import component.pinheader.PinHeaderCreator;
import component.pinheader.in.InPinHeaderCreator;
import component.pinheader.out.OutPinHeaderCreator;

import component.records.ConnectedPinsWithStates;
import component.records.PinConnection;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.ShortCircuitException;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.interfaces.UnknownComponent;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.interfaces.UnknownStateException;
import edu.uj.po.simulation.interfaces.UserInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Simulation implements UserInterface {

    // TODO 1: ZAIMPLEMENTOWAĆ OBSŁUGĘ WYJĄTKÓW W KLASIE SIMULATION
    // TODO 2: ZAIMPLEMENTOWAĆ METODĘ OPTIMIZE W KLASIE SIMULATION

    int globalUniqueId = 0;
    int connectionId = 0;
    ChipCreator chipCreator;
    PinHeaderCreator pinHeaderCreator;

    Map<Integer, LogicComponent> components = new HashMap<>();
    List<Map<Integer, Integer>> connectedComponentIds = new ArrayList<>();
    Map<Integer, Map<PinConnection, PinConnection>> pinConnections = new HashMap<>();
    List<PinConnection> pinsInConnection = new ArrayList<>();
    List<Integer> componentOrder = new ArrayList<>();
    List<ConnectedPinsWithStates> connectedPinsWithStates = new ArrayList<>();

    @Override
    public int createChip(int code) throws UnknownChip {
        chipCreator = switch (code) {
            case 7400 -> new IC7400Creator();
            case 7410 -> new IC7410Creator();
            case 7420 -> new IC7420Creator();
            case 7402 -> new IC7402Creator();
            case 7404 -> new IC7404Creator();
            case 7408 -> new IC7408Creator();
            case 7411 -> new IC7411Creator();
            case 7432 -> new IC7432Creator();
            case 7431 -> new IC7431Creator();
            case 7434 -> new IC7434Creator();
            case 7442 -> new IC7442Creator();
            case 7444 -> new IC7444Creator();
            case 7482 -> new IC7482Creator();
            case 74138 -> new IC74138Creator();
            case 74152 -> new IC74152Creator();
            default -> throw new UnknownChip();
        };
        ChipComponent chip = chipCreator.createChip();
        int thisChipId = globalUniqueId++;
        chip.setId(thisChipId);
        chip.setComponentType(ComponentType.CHIP);
        components.put(thisChipId, chip);
        return thisChipId;
    }

    @Override
    public int createInputPinHeader(int size) {
        pinHeaderCreator = new InPinHeaderCreator();
        PinHeaderComponent pinHeader = pinHeaderCreator.createPinHeader(size);
        int thisPinHeaderId = globalUniqueId++;
        pinHeader.setId(thisPinHeaderId);
        pinHeader.setComponentType(ComponentType.IN_PIN_HEADER);
        components.put(thisPinHeaderId, pinHeader);
        return thisPinHeaderId;
    }

    @Override
    public int createOutputPinHeader(int size) {
        pinHeaderCreator = new OutPinHeaderCreator();
        PinHeaderComponent pinHeader = pinHeaderCreator.createPinHeader(size);
        int thisPinHeaderId = globalUniqueId++;
        pinHeader.setId(thisPinHeaderId);
        pinHeader.setComponentType(ComponentType.OUT_PIN_HEADER);
        components.put(thisPinHeaderId, pinHeader);
        return thisPinHeaderId;
    }

    @Override
    public void connect(int component1, int pin1, int component2, int pin2) throws UnknownComponent, UnknownPin, ShortCircuitException {
        if (!components.containsKey(component1)) {
            throw new UnknownComponent(component1);
        }
        if (!components.containsKey(component2)) {
            throw new UnknownComponent(component2);
        }
        LogicComponent c1 = components.get(component1);
        LogicComponent c2 = components.get(component2);

        Pin p1, p2;
        try {
            p1 = c1.getPin(pin1);
            p2 = c2.getPin(pin2);
        } catch (Exception e) {
            throw new UnknownPin(component1, pin1);
        }

        // check if there is a short circuit
        // we can't connect:
        // - two output pins in CHIPS
        // - two input pins in CHIPS
        // - IN_PIN_HEADER pins with IN_PIN_HEADER or OUT_PIN_HEADER pins
        // - OUT_PIN_HEADER pins with IN_PIN_HEADER or OUT_PIN_HEADER pins


        if ((p1.getType().equals(PinType.IN) && p1.isConnectedToOutput()) &&
                (p2.getType().equals(PinType.IN) && p2.isConnectedToOutput())) {
            throw new ShortCircuitException();
        }

        if (p1.getType().equals(PinType.OUT) && p2.getType().equals(PinType.OUT)) {
            throw new ShortCircuitException();
        }

        if (p1.getType().equals(PinType.OUT) && p2.isConnectedToOutput()) {
            throw new ShortCircuitException();
        }

        if (p2.getType().equals(PinType.OUT) && p1.isConnectedToOutput()) {
            throw new ShortCircuitException();
        }

        if (p1.getType().equals(PinType.OUT)) {
            p2.setConnectedToOutput(true);
        } else if (p1.getType().equals(PinType.IN)) {
            p2.setConnectedToInput(true);
        }

        if (p2.getType().equals(PinType.OUT)) {
            p1.setConnectedToOutput(true);
        } else if (p2.getType().equals(PinType.IN)) {
            p1.setConnectedToInput(true);
        }

        c1.getPin(pin1).addConnectionToOtherPins(new PinConnection(component2, pin2));
        c2.getPin(pin2).addConnectionToOtherPins(new PinConnection(component1, pin1));

        c1.addConnectedComponentId(component2);
        c2.addConnectedComponentId(component1);

        c1.addObserver(c2);
        c2.addObserver(c1);

        if (!containsComponentPair(component1, component2)) {
            connectedComponentIds.add(Map.of(component1, component2));
            componentOrder.add(component1);
        }

        connectedPinsWithStates.add(new ConnectedPinsWithStates(
                component1, pin1, p1.getState(),
                component2, pin2, p2.getState()
        ));

        pinConnections.put(connectionId++, Map.of(new PinConnection(component1, pin1), new PinConnection(component2, pin2)));
        if (!containsPin(new PinConnection(component1, pin1))) {
            pinsInConnection.add(new PinConnection(component1, pin1));
        }
        if (!containsPin(new PinConnection(component2, pin2))) {
            pinsInConnection.add(new PinConnection(component2, pin2));
        }
    }

    @Override
    public void stationaryState(Set<ComponentPinState> states) throws UnknownStateException {
        // Set new input states to Input Pins
        for (ComponentPinState state : states) {
            int componentId = state.componentId();
            int pinId = state.pinId();
            PinState pinState = state.state();
            LogicComponent component = components.get(componentId);
            component.getPin(pinId).setState(pinState);
        }

        simulateStationaryState();

        for (LogicComponent component : components.values()) {
            for (int i = 1; i < component.getPins().size() + 1; i++) {
                if ((component.getPin(i).isConnectedToInput() || component.getPin(i).isConnectedToOutput()) &&
                        component.getPin(i).getState().equals(PinState.UNKNOWN)) {
                    throw new UnknownStateException(new ComponentPinState(component.getId(), i, PinState.UNKNOWN));
                }
            }
        }



//        int currComponentId = getInPinHeaderId();
//        LogicComponent inPinHeader = components.get(currComponentId);
//        inPinHeader.setConnectedPinsWithStates(getConnectionsFromChip(currComponentId));
//        inPinHeader.simulate();
//
//        Set<Integer> connectedComponents = inPinHeader.getConnectedComponentsIds();
//        int prevId = currComponentId;
//        for (Integer integer : componentOrder) {
//            currComponentId = integer;
//            if (currComponentId != prevId) {
//                connectedComponents = simulateForComponents(connectedComponents);
//            }
//        }

        System.out.println("Stationary state set");
    }


    private void simulateStationaryState() {
        boolean stateChange;
        Set<LogicComponent> updatedComponents = new HashSet<>();
        do {
            stateChange = false;

            for (LogicComponent component : components.values()) {
//                component.setConnectedPinsWithStates(getConnectionsFromChip(component.getId()));
                component.simulate();
                component.notifyObservers();
                if (component.hasStateChanged()) {
                    stateChange = true;
                    updatedComponents.add(component);
                }
            }
        } while (!stateChange);
    }

    private void kk() {
        // tick 0 for new inputs
        int currComponentId = getInPinHeaderId();
        LogicComponent inPinHeader = components.get(currComponentId);
        inPinHeader.setConnectedPinsWithStates(getConnectionsFromChip(currComponentId));
        inPinHeader.simulate();

        // list of components connected to InPinHeader
        Set<Integer> connectedComponents = inPinHeader.getConnectedComponentsIds();
        while (! (connectedComponents.size() == 1 && connectedComponents.contains(getOutPinHeaderId()))) {
            connectedComponents = simulateForComponents(connectedComponents);
        }
    }

    private boolean areSetsEqual(Set<ComponentPinState> set1, Set<ComponentPinState> set2) {
        if(set1.size() != set2.size()) {
            return false;
        }

        for (ComponentPinState state1 : set1) {
            boolean matchFound = false;

            for (ComponentPinState state2 : set2) {
                if (state1.componentId() == state2.componentId() &&
                        state1.pinId() == state2.pinId() &&
                        state1.state().equals(state2.state())) {
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                return false;
            }
        }

        return true;
    }

    private Set<ComponentPinState> getStatesForSimulationStep() {
        Set<ComponentPinState> states = new HashSet<>();
        for (PinConnection pin : pinsInConnection) {
            states.add(new ComponentPinState(pin.componentId(), pin.pinId(), components.get(pin.componentId()).getPin(pin.pinId()).getState()));
        }
        return states;
    }
//-------------
    //        while (!checkAllStatesSet()) {
//            int prevId = -1;
//            for (int currentId : componentOrder) {
//                if (currentId != prevId) {
//                    LogicComponent inPinHeader = components.get(currentId);
//                    inPinHeader.setConnectedPinsWithStates(getConnectionsFromChip(currentId));
//                    components.get(currentId).simulate();
//                    prevId = componentOrder.get(currentId);
//                }
//            }
//        }

    //        while (!checkAllStatesSet()) {
//            connectedComponents = simulateForComponents(connectedComponents);
//        }

    //----------

    // po7.zip
//    @Override
//    public void stationaryState(Set<ComponentPinState> states) throws UnknownStateException {
//        for (ComponentPinState state : states) {
//            int componentId = state.componentId();
//            int pinId = state.pinId();
//            PinState pinState = state.state();
//            LogicComponent component = components.get(componentId);
//            component.getPin(pinId).setState(pinState);
//        }
//
//        int currComponentId = getInPinHeaderId();
//        LogicComponent inPinHeader = components.get(currComponentId);
//        inPinHeader.setConnectedPinsWithStates(getConnectionsFromChip(currComponentId));
//        inPinHeader.simulate();
//
//        Set<Integer> connectedComponents = inPinHeader.getConnectedComponentsIds();
//        int prevId = currComponentId;
//        for (Integer integer : componentOrder) {
//            currComponentId = integer;
//            if (currComponentId != prevId) {
//                connectedComponents = simulateForComponents(connectedComponents);
//            }
//        }
//        System.out.println("Stationary state set");
//    }

    @Override
    public Map<Integer, Set<ComponentPinState>> simulation(Set<ComponentPinState> states0, int ticks) throws UnknownStateException {
        Map<Integer, Set<ComponentPinState>> result = new HashMap<>();
        // set new input states to Input Pins
        for (ComponentPinState state : states0) {
            int componentId = state.componentId();
            int pinId = state.pinId();
            PinState pinState = state.state();
            LogicComponent component = components.get(componentId);
            component.getPin(pinId).setState(pinState);
        }

        // set connections for each component


        // tick 0 for new inputs
        int currComponentId = getInPinHeaderId();
        LogicComponent inPinHeader = components.get(currComponentId);
        inPinHeader.setConnectedPinsWithStates(getConnectionsFromChip(currComponentId));
        inPinHeader.simulate();
        result.put(0, getStatesForTick());

        // list of components connected to InPinHeader
        Set<Integer> connectedComponents = inPinHeader.getConnectedComponentsIds();
        for (int i = 1; i <= ticks; i++) {
            connectedComponents = simulateForComponents(connectedComponents);
            result.put(i, getStatesForTick());
        }
        return result;
    }

    private Set<Integer> simulateForComponents(Set<Integer> connectedComponents) {
        Set<Integer> newConnectedComponents = new HashSet<>();
        for (int componentId : connectedComponents) {
            LogicComponent component = components.get(componentId);
            component.setConnectedPinsWithStates(getConnectionsFromChip(componentId));
            component.simulate();
            newConnectedComponents.addAll(component.getConnectedComponentsIds());
        }
        for (int componentId : connectedComponents) {
            components.get(componentId).notifyObservers();
        }
        return newConnectedComponents;
    }

    private List<Integer> getComponentsConnectedToChip(int chipId) {
        List<Integer> connectedComponents = new ArrayList<>();
        for (Map<Integer, Integer> pair : connectedComponentIds) {
            if (pair.containsKey(chipId)) {
                connectedComponents.add(pair.get(chipId));
            }
        }
        return connectedComponents;
    }

    private int getInPinHeaderId() {
        for (int id : components.keySet()) {
            if (components.get(id).getComponentType().equals(ComponentType.IN_PIN_HEADER)) {
                return id;
            }
        }
        return -1;
    }

    private int getOutPinHeaderId() {
        for (int id : components.keySet()) {
            if (components.get(id).getComponentType().equals(ComponentType.OUT_PIN_HEADER)) {
                return id;
            }
        }
        return -1;
    }

    @Override
    public Set<Integer> optimize(Set<ComponentPinState> states0, int ticks) throws UnknownStateException {
//        Set<Integer> removableComponents = new HashSet<>();
//        Map<Integer, Set<ComponentPinState>> fullSimulationResult = simulation(states0, ticks);
//        for (int componentId : components.keySet()) {
//            if (!components.get(componentId).getComponentType().equals(ComponentType.IN_PIN_HEADER)
//                    && !components.get(componentId).getComponentType().equals(ComponentType.OUT_PIN_HEADER)) {
//                // remove component
//                Map<Integer, LogicComponent> newComponents = removeComponent(componentId);
//                // reconnect components without removed component (componentId)
//                // perform simulation without removed component
//                // check simulation results for each tick are the same as fullSimulationResult
//                // if yes, add componentId to removableComponents
//            }
//        }
//        return removableComponents;
        return null;
    }

    private Map<Integer, LogicComponent> removeComponent(int componentId) {
        Map<Integer, LogicComponent> newComponents = new HashMap<>(components);
        newComponents.remove(componentId);
        return newComponents;
    }

    private boolean checkAllStatesSet() {
        for (PinConnection pin : pinsInConnection) {
            if (components.get(pin.componentId()).getPin(pin.pinId()).getState().equals(PinState.UNKNOWN)) {
                return false;
            }
        }
        return true;
    }

    private Set<ComponentPinState> getStatesForTick() {
        Set<ComponentPinState> states = new HashSet<>();
        for (PinConnection pin : pinsInConnection) {
            if (components.get(pin.componentId()).getComponentType().equals(ComponentType.OUT_PIN_HEADER)) {
                states.add(new ComponentPinState(pin.componentId(), pin.pinId(), components.get(pin.componentId()).getPin(pin.pinId()).getState()));
            }
        }
        return states;
    }

    private List<ConnectedPinsWithStates> getConnectionsFromChip(int chipId) {
        List<ConnectedPinsWithStates> connections = new ArrayList<>();
        for (ConnectedPinsWithStates connection : connectedPinsWithStates) {
            if (connection.componentId1() == chipId) {
                connections.add(connection);
            }
        }
        return connections;
    }

    private boolean containsComponentPair(int component1, int component2) {
        for (Map<Integer, Integer> pair : connectedComponentIds) {
            if (pair.containsKey(component1) && pair.containsValue(component2)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsPin(PinConnection pinConnection) {
        for (PinConnection pin : pinsInConnection) {
            if (pin.componentId()== pinConnection.componentId() && pin.pinId() == pinConnection.pinId()){
                return true;
            }
        }
        return false;
    }

    public LogicComponent getComponentById(int id) {
        return components.get(id);
    }
}