package org.example;

import component.ChipComponent;
import component.SavedLogicComponent;
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
import component.pin.Observer;
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
import utils.SimulationUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static utils.SimulationUtils.findMaxPossibleComponentsToRemove;
import static utils.SimulationUtils.isSimulationResultsEqual;

public class Simulation implements UserInterface {

    int globalUniqueId = 0;
    int connectionId = 0;
    ChipCreator chipCreator;
    PinHeaderCreator pinHeaderCreator;

    Map<Integer, LogicComponent> components = new HashMap<>();
    Map<Integer, SavedLogicComponent> savedComponents = new HashMap<>();
//    Map<Integer, Map<PinConnection, PinConnection>> pinConnections = new HashMap<>();
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
        chip.setCode(code);
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
        // check connected components exist
        if (!components.containsKey(component1)) {
            throw new UnknownComponent(component1);
        }
        if (!components.containsKey(component2)) {
            throw new UnknownComponent(component2);
        }
        LogicComponent c1 = components.get(component1);
        LogicComponent c2 = components.get(component2);

        // check connected pins exist
        Pin p1, p2;
        try {
            p1 = c1.getPin(pin1);
            p2 = c2.getPin(pin2);
        } catch (Exception e) {
            throw new UnknownPin(component1, pin1);
        }

        // check connections
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

        // set connected to input/output
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

//        connectedPinsWithStates.add(new ConnectedPinsWithStates(
//                component1, pin1, p1.getState(),
//                component2, pin2, p2.getState()
//        ));
//
//        pinConnections.put(connectionId++, Map.of(new PinConnection(component1, pin1), new PinConnection(component2, pin2)));

        p1.addObserver(p2);
        p2.addObserver(p1);
    }

    @Override
    public void stationaryState(Set<ComponentPinState> states) throws UnknownStateException {
        if (!states.isEmpty()){
            setInitialStates(states);
        }
        simulateStationaryState();
        for (LogicComponent component : components.values()) {
            for (int i = 0; i < component.getPins().size(); i++) {
                Pin currentPin = component.getPins().get(i);
                if (currentPin != null && currentPin.isConnected()) {
                    List<PinConnection> connections = currentPin.getConnectionsToOtherPins();
                    for (PinConnection connection : connections) {
                        if (components.get(connection.componentId()).getPin(connection.pinId()).getState().equals(PinState.UNKNOWN)) {
                            throw new UnknownStateException(new ComponentPinState(connection.componentId(), connection.pinId(), PinState.UNKNOWN));
                        }
                    }
                    if (currentPin.getState().equals(PinState.UNKNOWN)) {
                        throw new UnknownStateException(new ComponentPinState(component.getId(), currentPin.getId(), PinState.UNKNOWN));
                    }
                }
            }
        }
        // save circuit stationary state
        for (LogicComponent component : components.values()) {
            savedComponents.put(component.getId(), new SavedLogicComponent(component));
        }
    }

    @Override
    public Map<Integer, Set<ComponentPinState>> simulation(Set<ComponentPinState> states0, int ticks) throws UnknownStateException {
        Map<Integer, Set<ComponentPinState>> result = new HashMap<>();
        result.put(0, getStatesForTick());

        // set new states for InputPinHeader
        setInitialStates(states0);

        // set new inputs for components connected to InputPinHeader
        int currComponentId = getInPinHeaderId();
        LogicComponent inPinHeader = components.get(currComponentId);
        inPinHeader.setConnectedPinsWithStates(getConnectionsFromChip(currComponentId));
        inPinHeader.simulate();
        inPinHeader.step();

        // list of components connected to InPinHeader
        Set<Integer> connectedComponents = inPinHeader.getConnectedComponentsIds();
        for (int i = 1; i <= ticks; i++) {
            connectedComponents = simulateForConnectedComponents(connectedComponents);
            result.put(i, getStatesForTick());
        }

//        for (int i = 1; i <= ticks; i++) {
//            for (LogicComponent component : components.values()) {
//                if (component != null) {
//                    component.simulate();
//                }
//            }
//            for (LogicComponent component : components.values()) {
//                if (component != null) {
//                    component.step();
//                }
//            }
//            result.put(i, getStatesForTick());
//        }
        return result;
    }

    @Override
    public Set<Integer> optimize(Set<ComponentPinState> states0, int ticks) throws UnknownStateException {
//        // call stationaryState
//        stationaryState(Collections.emptySet());

        // get simulation results for original circuit
        Map<Integer, Set<ComponentPinState>> originSimulationResult = new HashMap<>(simulation(states0, ticks));

        // get all chip ids
        List<Integer> chipIds = new ArrayList<>();
        for (int id : components.keySet()) {
            if (components.get(id).getComponentType().equals(ComponentType.CHIP)) {
                chipIds.add(id);
            }
        }

        // generate combinations
        List<List<Integer>> combinations = SimulationUtils.generateComponentCombinations(chipIds);

        // define list of components to remove
        List<Set<Integer>> componentsToRemove = new ArrayList<>();

        for (List<Integer> combination : combinations) {
            // load original circuit
            for (LogicComponent component : components.values()) {
                SavedLogicComponent loadedComponent = savedComponents.get(component.getId());
                if (loadedComponent != null) {
                    loadedComponent.loadChip(component);
                }
            }

            setInitialStates(states0);
            List<LogicComponent> removedComponents = new ArrayList<>();
            for (int id : combination) {
                removedComponents.add(deleteChip(id));
            }

            Map<Integer, Set<ComponentPinState>> simulationResult =
                    new HashMap<>(simulationWithoutRemovedChips(states0, ticks, combination));

            if (isSimulationResultsEqual(originSimulationResult, simulationResult)) {
                componentsToRemove.add(new HashSet<>(combination));
            }
            for (LogicComponent component : removedComponents) {
                components.put(component.getId(), component);
            }
        }

        return new HashSet<>(findMaxPossibleComponentsToRemove(componentsToRemove));
    }

    private LogicComponent deleteChip(int chipId) {
        LogicComponent component = components.remove(chipId);
        for (Pin pinToRemove : component.getPins()) {
            if (pinToRemove != null && pinToRemove.getType().equals(PinType.OUT)) {
                if (pinToRemove.isConnected()) {
                    List<PinConnection> connections = pinToRemove.getConnectionsToOtherPins();
                    for (PinConnection connection : connections) {
                        LogicComponent connectedComponent = components.get(connection.componentId());
                        if (connectedComponent != null) {
                            Pin connectedPin = connectedComponent.getPin(connection.pinId());
                            if (connectedPin != null) {
                                // remove target pin from list of observers
                                connectedPin.removeObserver(pinToRemove);
                                connectedPin.setState(PinState.UNKNOWN);
                            }
                        }
                    }
                }
                for (Observer observer : pinToRemove.getObservers()) {
                    // remove target pin's observers
                    ((Pin) observer).removeObserver(pinToRemove);
//                    ((Pin) observer).setState(PinState.UNKNOWN);
                }
                pinToRemove.setState(PinState.UNKNOWN);
            }
        }
        return component;
    }

    public Map<Integer, Set<ComponentPinState>> simulationWithoutRemovedChips(Set<ComponentPinState> states0,
                                                                              int ticks,
                                                                              List<Integer> removedChips){
        Map<Integer, Set<ComponentPinState>> result = new HashMap<>();
        result.put(0, getStatesForTick());
        for (int i = 1; i <= ticks; i++) {
            for (Map.Entry<Integer, LogicComponent> entry : components.entrySet()) {
                if (!removedChips.contains(entry.getKey())
                        && entry.getValue().getComponentType().equals(ComponentType.CHIP)) {
                    entry.getValue().simulate();
                }
            }
            for (Map.Entry<Integer, LogicComponent> entry : components.entrySet()) {
                if (!removedChips.contains(entry.getKey())
                        && entry.getValue().getComponentType().equals(ComponentType.CHIP)) {
                    entry.getValue().step();
                }
            }
            result.put(i, getStatesForTick());
        }
        return result;
    }

    private void setInitialStates(Set<ComponentPinState> states) {
        for (ComponentPinState state : states) {
            int componentId = state.componentId();
            int pinId = state.pinId();
            PinState pinState = state.state();
            LogicComponent component = components.get(componentId);
            Pin pin = component.getPin(pinId);
            pin.update(pinState);
            pin.performStep();
        }
    }

    private Set<Integer> simulateForConnectedComponents(Set<Integer> connectedComponents) {
        Set<Integer> newConnectedComponents = new HashSet<>();
        for (int componentId : connectedComponents) {
            LogicComponent component = components.get(componentId);
            if (component != null) {
                component.simulate();
                newConnectedComponents.addAll(component.getConnectedComponentsIds());
            }
        }
        for (int componentId : connectedComponents) {
            LogicComponent component = components.get(componentId);
            if (component != null) {
                component.step();
            }
        }
        return newConnectedComponents;
    }

    private List<ConnectedPinsWithStates> getConnectionsFromChip(int chipId) {
        List<ConnectedPinsWithStates> connections = new ArrayList<>();
        for (ConnectedPinsWithStates connection : connectedPinsWithStates) {
            if (connection.componentId1() == chipId) {
                connections.add(connection);
            } else if (connection.componentId2() == chipId) {
                connections.add(connection);
            }
        }
        return connections;
    }

    private void simulateStationaryState() {
        Set<LogicComponent> updatedComponents = new HashSet<>();
        boolean stateChange = true;
        while (stateChange) {
            stateChange = false;
            for (LogicComponent component : components.values()) {
                component.simulate();
                component.step();
                if (component.hasStateChanged()) {
                    stateChange = true;
                    updatedComponents.add(component);
                }
            }
            for (LogicComponent component : updatedComponents) {
                component.resetStateChanged();
            }
            updatedComponents.clear();
        }
    }

    private Set<ComponentPinState> getStatesForTick() {
        Set<ComponentPinState> states = new HashSet<>();
        LogicComponent outPinHeader = components.get(getOutPinHeaderId());
        for (Pin pin : outPinHeader.getPins()) {
            states.add(new ComponentPinState(outPinHeader.getId(), pin.getId(), pin.getState()));
        }
        return states;
    }

    public LogicComponent getComponentById(int id) {
        return components.get(id);
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
}