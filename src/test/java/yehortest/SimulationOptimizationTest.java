package yehortest;

import org.junit.jupiter.api.BeforeEach;
import org.example.Simulation;

public class SimulationOptimizationTest {
    private Simulation simulation;
    private int pinHeaderInputId;
    private int pinHeaderOutputId;
    private int chip7408Id1;
    private int chip7408Id2;
    private int chip7408Id3;

    @BeforeEach
    public void setUp() throws Exception {
        simulation = new Simulation();

        pinHeaderInputId = simulation.createInputPinHeader(3);  // 3 inputs: We1, We2, We4
        pinHeaderOutputId = simulation.createOutputPinHeader(4); // 4 outputs: 1, 2, 3, 4

        chip7408Id1 = simulation.createChip(7408);
        chip7408Id2 = simulation.createChip(7408);
        chip7408Id3 = simulation.createChip(7408);

        // Konfiguracja połączeń według obrazka

        // Połączenia dla U01
        simulation.connect(pinHeaderInputId, 1, chip7408Id1, 1); // We1 to U01 pin 1
        simulation.connect(pinHeaderInputId, 2, chip7408Id1, 2); // We2 to U01 pin 2
        simulation.connect(chip7408Id1, 3, chip7408Id2, 1); // U01 pin 3 to U02 pin 1

        // Połączenia dla U02
        simulation.connect(chip7408Id2, 3, chip7408Id3, 1); // U02 pin 3 to U03 pin 1
        simulation.connect(pinHeaderOutputId, 1, chip7408Id2, 6); // U02 pin 6 to output 1

        // Połączenia dla U03
        simulation.connect(chip7408Id3, 6, pinHeaderOutputId, 2); // U03 pin 6 to output 2
    }

//    @Test
//    public void testOptimization() throws UnknownStateException {
//        Set<ComponentPinState> initialStates = new HashSet<>();
//        initialStates.add(new ComponentPinState(pinHeaderInputId, 1, PinState.LOW)); // We1 - LO
//        initialStates.add(new ComponentPinState(pinHeaderInputId, 2, PinState.HIGH));  // We2 - HI
//
//        simulation.stationaryState(initialStates);
//        Set<Integer> removableComponents = simulation.optimize(initialStates, 3);
//
//        // Sprawdzenie, czy niepotrzebne układy zostały usunięte
//        assertTrue(removableComponents.contains(chip7408Id1)); // U01 powinien być usunięty
//        assertFalse(removableComponents.contains(chip7408Id2)); // U02 nie powinien być usunięty
//        assertFalse(removableComponents.contains(chip7408Id3)); // U03 nie powinien być usunięty
//    }
}