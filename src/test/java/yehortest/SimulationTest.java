package yehortest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import component.LogicComponent;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.ShortCircuitException;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.interfaces.UnknownComponent;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.interfaces.UnknownStateException;
import org.example.Simulation;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationTest {
    private Simulation userInterface;
    private int pinHeaderInputId;
    private int pinHeaderOutputId;

    @BeforeEach
    public void setUp() throws Exception {
        userInterface = new Simulation();

        pinHeaderInputId = userInterface.createInputPinHeader(3);  // 3 inputs: We1, We2, We4
        int chip7408Id = userInterface.createChip(7408);
        int chip7404Id = userInterface.createChip(7404);
        pinHeaderOutputId = userInterface.createOutputPinHeader(4); // 4 outputs: 1, 2, 3, 4


        // We1 and We2 inputs to U01 (pins 13 and 12)
        userInterface.connect(pinHeaderInputId, 1, chip7408Id, 13); // We1 to pin 13
        userInterface.connect(pinHeaderInputId, 2, chip7408Id, 12); // We2 to pin 12

        // We4 is input to U01 (pin 4)
        userInterface.connect(pinHeaderInputId, 3, chip7408Id, 4); // We4 to pin 4

        // Pin 11 of U01 connected to pin 13 of U02
        userInterface.connect(chip7408Id, 11, chip7404Id, 13); // U01 pin 11 to U02 pin 13

        // We3 is connected to pin 12 of U02 (6Y) and U01 pin 5
        userInterface.connect(chip7404Id, 12, chip7408Id, 5); // U02 pin 12 (6Y) to U01 pin 5

        // Output of U01 to the output pins
        userInterface.connect(chip7408Id, 6, pinHeaderOutputId, 4); // U01 pin 6 to output pin 4

        // Wy 6y(Pin 12) to We 5A(Pin 11) ukladu drugiego i do Wy 2
        userInterface.connect(chip7404Id, 12, chip7404Id, 11); // U02 pin 12 (6Y) to U02 pin 11 (5A)
        userInterface.connect(chip7404Id, 12, pinHeaderOutputId, 2); // U02 pin 12 (6Y) to output pin 2

        // Wy 5Y(Pin 10) to Wy3
        userInterface.connect(chip7404Id, 10, pinHeaderOutputId, 3); // U02 pin 10 (5Y) to output pin 3

        // Wyjście Pin 11 do Wy1
        userInterface.connect(chip7408Id, 11, pinHeaderOutputId, 1); // U01 pin 11 to output pin 1
    }

    @Test
    public void testStationaryState() throws UnknownStateException, UnknownChip, UnknownPin, ShortCircuitException, UnknownComponent {
        Set<ComponentPinState> initialStates = new HashSet<>();
        initialStates.add(new ComponentPinState(pinHeaderInputId, 1, PinState.HIGH)); // We1 - HI
        initialStates.add(new ComponentPinState(pinHeaderInputId, 2, PinState.LOW));  // We2 - LO
        initialStates.add(new ComponentPinState(pinHeaderInputId, 3, PinState.HIGH)); // We4 - HI

        userInterface.stationaryState(initialStates);

        LogicComponent component = userInterface.getComponentById(pinHeaderOutputId);
        Assertions.assertEquals(PinState.LOW, component.getPin(1).getState());  // 1 - LO
        Assertions.assertEquals(PinState.HIGH, component.getPin(2).getState()); // 2 - HI
        Assertions.assertEquals(PinState.LOW, component.getPin(3).getState());  // 3 - LO
        Assertions.assertEquals(PinState.HIGH, component.getPin(4).getState()); // 4 - HI
    }

    @Test
    public void testSimulation() throws Exception {
        // Define the initial stationary state
        Set<ComponentPinState> stationaryStates = new HashSet<>();
        stationaryStates.add(new ComponentPinState(pinHeaderInputId, 1, PinState.HIGH)); // We1
        stationaryStates.add(new ComponentPinState(pinHeaderInputId, 2, PinState.LOW));  // We2
        stationaryStates.add(new ComponentPinState(pinHeaderInputId, 3, PinState.HIGH)); // We4

        userInterface.stationaryState(stationaryStates);

        LogicComponent component = userInterface.getComponentById(pinHeaderOutputId);
        Assertions.assertEquals(PinState.LOW, component.getPin(1).getState());  // 1 - LO
        Assertions.assertEquals(PinState.HIGH, component.getPin(2).getState()); // 2 - HI
        Assertions.assertEquals(PinState.LOW, component.getPin(3).getState());  // 3 - LO
        Assertions.assertEquals(PinState.HIGH, component.getPin(4).getState()); // 4 - HI

        // Define the initial state for the simulation
        Set<ComponentPinState> initialStates = new HashSet<>();
        initialStates.add(new ComponentPinState(pinHeaderInputId, 1, PinState.HIGH)); // We1
        initialStates.add(new ComponentPinState(pinHeaderInputId, 2, PinState.HIGH)); // We2
        initialStates.add(new ComponentPinState(pinHeaderInputId, 3, PinState.HIGH)); // We4

        // Perform the simulation for 3 ticks
        Map<Integer, Set<ComponentPinState>> simulationResults = userInterface.simulation(initialStates, 3);

        // Define expected states at each tick
        List<List<PinState>> expectedStates = List.of(
                List.of(PinState.LOW, PinState.HIGH, PinState.LOW, PinState.HIGH),  // 0
                List.of(PinState.HIGH, PinState.HIGH, PinState.LOW, PinState.HIGH),  // 1
                List.of(PinState.HIGH, PinState.LOW, PinState.LOW, PinState.HIGH),  // 2
                List.of(PinState.HIGH, PinState.LOW, PinState.HIGH, PinState.LOW)   // 3
        );

        // Validate the results at each tick
        for (int tick = 0; tick <= 3; tick++) {
            Set<ComponentPinState> statesAtTick = simulationResults.get(tick);
            for (ComponentPinState state : statesAtTick) {
                assertEquals(expectedStates.get(tick).get(state.pinId() - 1), state.state());
            }
        }
        /*
            1   	LO	  HI	  HI	 HI
            2   	HI	  HI	  LO	 LO
            3   	LO	  LO	  LO	 HI
            4   	HI	  HI	  HI	 LO
         */
    }

    @Test
    public void testOptimization() throws Exception {
        // Define the initial stationary state
        Set<ComponentPinState> stationaryStates = new HashSet<>();
        stationaryStates.add(new ComponentPinState(pinHeaderInputId, 1, PinState.HIGH)); // We1
        stationaryStates.add(new ComponentPinState(pinHeaderInputId, 2, PinState.LOW));  // We2
        stationaryStates.add(new ComponentPinState(pinHeaderInputId, 3, PinState.HIGH)); // We4

        userInterface.stationaryState(stationaryStates);

        LogicComponent component = userInterface.getComponentById(pinHeaderOutputId);
        Assertions.assertEquals(PinState.LOW, component.getPin(1).getState());  // 1 - LO
        Assertions.assertEquals(PinState.HIGH, component.getPin(2).getState()); // 2 - HI
        Assertions.assertEquals(PinState.LOW, component.getPin(3).getState());  // 3 - LO
        Assertions.assertEquals(PinState.HIGH, component.getPin(4).getState()); // 4 - HI

        // Define the initial state for the simulation
        Set<ComponentPinState> initialStates = new HashSet<>();
        initialStates.add(new ComponentPinState(pinHeaderInputId, 1, PinState.HIGH)); // We1
        initialStates.add(new ComponentPinState(pinHeaderInputId, 2, PinState.HIGH)); // We2
        initialStates.add(new ComponentPinState(pinHeaderInputId, 3, PinState.HIGH)); // We4

        // Perform the simulation for 3 ticks
        Map<Integer, Set<ComponentPinState>> simulationResults = userInterface.simulation(initialStates, 3);

        // Define expected states at each tick
        List<List<PinState>> expectedStates = List.of(
                List.of(PinState.LOW, PinState.HIGH, PinState.LOW, PinState.HIGH),  // 0
                List.of(PinState.HIGH, PinState.HIGH, PinState.LOW, PinState.HIGH),  // 1
                List.of(PinState.HIGH, PinState.LOW, PinState.LOW, PinState.HIGH),  // 2
                List.of(PinState.HIGH, PinState.LOW, PinState.HIGH, PinState.LOW)   // 3
        );

        // Validate the results at each tick
        for (int tick = 0; tick <= 3; tick++) {
            Set<ComponentPinState> statesAtTick = simulationResults.get(tick);
            for (ComponentPinState state : statesAtTick) {
                assertEquals(expectedStates.get(tick).get(state.pinId() - 1), state.state());
            }
        }
    }
}