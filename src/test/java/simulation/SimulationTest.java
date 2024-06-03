package simulation;

import edu.uj.po.simulation.interfaces.ComponentPinState;
import org.example.Simulation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import component.LogicComponent;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.ShortCircuitException;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.interfaces.UnknownComponent;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.interfaces.UnknownStateException;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationTest {
    Simulation userInterface;

    @BeforeEach
    public void setUp() {
        userInterface = new Simulation();
    }

    @Test
    public void testSimulation_forAndAndNot_oramusExample() throws UnknownChip, UnknownPin, ShortCircuitException, UnknownComponent, UnknownStateException {
        // create components
        int inPinHeaderId = userInterface.createInputPinHeader(3);
        int and7408 = userInterface.createChip(7408);
        int not7404 = userInterface.createChip(7404);
        int outPinHeaderId = userInterface.createOutputPinHeader(4);

        userInterface.connect(inPinHeaderId, 1, and7408, 13);
        userInterface.connect(inPinHeaderId, 2, and7408, 12);
        userInterface.connect(inPinHeaderId, 3, and7408, 4);
        userInterface.connect(and7408, 11, not7404, 13);
        userInterface.connect(not7404, 12, not7404, 11);
        userInterface.connect(not7404, 12, and7408, 5);

        userInterface.connect(and7408, 11, outPinHeaderId, 1);
        userInterface.connect(not7404, 12, outPinHeaderId, 2);
        userInterface.connect(not7404, 10, outPinHeaderId, 3);
        userInterface.connect(and7408, 6, outPinHeaderId, 4);

        Set<ComponentPinState> componentPinStates = Set.of(
                new ComponentPinState(inPinHeaderId, 1, PinState.HIGH), // we1
                new ComponentPinState(inPinHeaderId, 2, PinState.LOW), // we2
                new ComponentPinState(inPinHeaderId, 3, PinState.HIGH) // we4
        );
        userInterface.stationaryState(componentPinStates);

        Set<ComponentPinState> states0 = Set.of(
                new ComponentPinState(inPinHeaderId, 1, PinState.HIGH), // we1
                new ComponentPinState(inPinHeaderId, 2, PinState.HIGH), // we2
                new ComponentPinState(inPinHeaderId, 3, PinState.HIGH) // we4
        );

        // simulate
        Map<Integer, Set<ComponentPinState>> result = userInterface.simulation(states0, 3);
        assertEquals(4, result.size());
        assertTrue(result.containsKey(0)); // tick 0
        assertTrue(result.containsKey(1)); // tick 1
        assertTrue(result.containsKey(2)); // tick 2
        assertTrue(result.containsKey(3)); // tick 3

        Set<ComponentPinState> statesTick0 = result.get(0);
//        assertEquals(8, statesTick0.size());
//        assertTrue(statesTick0.contains(new ComponentPinState(and7408, 4, PinState.HIGH)));
//        assertTrue(statesTick0.contains(new ComponentPinState(and7408, 5, PinState.HIGH)));
//        assertTrue(statesTick0.contains(new ComponentPinState(and7408, 12, PinState.HIGH)));
//        assertTrue(statesTick0.contains(new ComponentPinState(and7408, 13, PinState.HIGH)));
        assertTrue(statesTick0.contains(new ComponentPinState(outPinHeaderId, 1, PinState.LOW)));
        assertTrue(statesTick0.contains(new ComponentPinState(outPinHeaderId, 2, PinState.HIGH)));
        assertTrue(statesTick0.contains(new ComponentPinState(outPinHeaderId, 3, PinState.LOW)));
        assertTrue(statesTick0.contains(new ComponentPinState(outPinHeaderId, 4, PinState.HIGH)));

        Set<ComponentPinState> statesTick1 = result.get(1);
//        assertEquals(8, statesTick1.size());
//        assertTrue(statesTick1.contains(new ComponentPinState(and7408, 4, PinState.HIGH)));
//        assertTrue(statesTick1.contains(new ComponentPinState(and7408, 5, PinState.HIGH)));
//        assertTrue(statesTick1.contains(new ComponentPinState(and7408, 12, PinState.HIGH)));
//        assertTrue(statesTick1.contains(new ComponentPinState(and7408, 13, PinState.HIGH)));
        assertTrue(statesTick1.contains(new ComponentPinState(outPinHeaderId, 1, PinState.HIGH)));
        assertTrue(statesTick1.contains(new ComponentPinState(outPinHeaderId, 2, PinState.HIGH)));
        assertTrue(statesTick1.contains(new ComponentPinState(outPinHeaderId, 3, PinState.LOW)));
        assertTrue(statesTick1.contains(new ComponentPinState(outPinHeaderId, 4, PinState.HIGH)));

        Set<ComponentPinState> statesTick2 = result.get(2);
//        assertEquals(8, statesTick2.size());
//        assertTrue(statesTick2.contains(new ComponentPinState(and7408, 4, PinState.HIGH)));
//        assertTrue(statesTick2.contains(new ComponentPinState(and7408, 5, PinState.LOW)));
//        assertTrue(statesTick2.contains(new ComponentPinState(and7408, 12, PinState.HIGH)));
//        assertTrue(statesTick2.contains(new ComponentPinState(and7408, 13, PinState.HIGH)));
        assertTrue(statesTick2.contains(new ComponentPinState(outPinHeaderId, 1, PinState.HIGH)));
        assertTrue(statesTick2.contains(new ComponentPinState(outPinHeaderId, 2, PinState.LOW)));
        assertTrue(statesTick2.contains(new ComponentPinState(outPinHeaderId, 3, PinState.LOW)));
        assertTrue(statesTick2.contains(new ComponentPinState(outPinHeaderId, 4, PinState.HIGH)));

        Set<ComponentPinState> statesTick3 = result.get(3);
//        assertEquals(8, statesTick3.size());
//        assertTrue(statesTick3.contains(new ComponentPinState(and7408, 4, PinState.HIGH)));
//        assertTrue(statesTick3.contains(new ComponentPinState(and7408, 5, PinState.LOW)));
//        assertTrue(statesTick3.contains(new ComponentPinState(and7408, 12, PinState.HIGH)));
//        assertTrue(statesTick3.contains(new ComponentPinState(and7408, 13, PinState.HIGH)));
        assertTrue(statesTick3.contains(new ComponentPinState(outPinHeaderId, 1, PinState.HIGH)));
        assertTrue(statesTick3.contains(new ComponentPinState(outPinHeaderId, 2, PinState.LOW)));
        assertTrue(statesTick3.contains(new ComponentPinState(outPinHeaderId, 3, PinState.HIGH)));
        assertTrue(statesTick3.contains(new ComponentPinState(outPinHeaderId, 4, PinState.LOW)));

        /*
                    0	Tick1	Tick2	Tick3
            We1 	HI 	  HI	  HI	 HI
            We2 	HI	  HI	  HI	 HI
            We3 	HI	  HI	  LO	 LO
            We4 	HI	  HI	  HI	 HI
            1   	LO	  HI	  HI	 HI
            2   	HI	  HI	  LO	 LO
            3   	LO	  LO	  LO	 HI
            4   	HI	  HI	  HI	 LO
        */
    }

    @Test
    public void testSimulation_forThreeAnd_oramusExampleForDeleteComponent() throws UnknownChip, UnknownPin, ShortCircuitException, UnknownComponent, UnknownStateException {
        // create components
        int inPinHeaderId = userInterface.createInputPinHeader(7);
        int and7408_1 = userInterface.createChip(7408);
        int and7408_2 = userInterface.createChip(7408);
        int and7408_3 = userInterface.createChip(7408);
        int outPinHeaderId = userInterface.createOutputPinHeader(2);

        userInterface.connect(inPinHeaderId, 1, and7408_1, 2); // 0
        userInterface.connect(inPinHeaderId, 2, and7408_1, 1); // 1
        userInterface.connect(inPinHeaderId, 3, and7408_2, 2); // 1
        userInterface.connect(inPinHeaderId, 4, and7408_2, 1); // 0
        userInterface.connect(inPinHeaderId, 5, and7408_2, 4); // 0
        userInterface.connect(inPinHeaderId, 6, and7408_2, 5); // 1
        userInterface.connect(inPinHeaderId, 7, and7408_3, 5); // 1

        userInterface.connect(and7408_1, 3, and7408_3, 13); // 0
        userInterface.connect(and7408_2, 3, and7408_3, 12); // 1
        userInterface.connect(and7408_2, 6, and7408_3, 4); // 1

        userInterface.connect(and7408_3, 6, outPinHeaderId, 1);
        userInterface.connect(and7408_3, 11, outPinHeaderId, 2);

        Set<ComponentPinState> componentPinStates = Set.of(
                new ComponentPinState(inPinHeaderId, 1, PinState.LOW), // we1
                new ComponentPinState(inPinHeaderId, 2, PinState.HIGH), // we2
                new ComponentPinState(inPinHeaderId, 3, PinState.HIGH), // we3
                new ComponentPinState(inPinHeaderId, 4, PinState.LOW),  // we4
                new ComponentPinState(inPinHeaderId, 5, PinState.LOW), // we5
                new ComponentPinState(inPinHeaderId, 6, PinState.HIGH), // we6
                new ComponentPinState(inPinHeaderId, 7, PinState.HIGH) // we7
        );

        userInterface.stationaryState(componentPinStates);

        LogicComponent outPinHeader = userInterface.getComponentById(outPinHeaderId);
        Assertions.assertEquals(PinState.LOW, outPinHeader.getPin(1).getState());
        Assertions.assertEquals(PinState.LOW, outPinHeader.getPin(2).getState());

        /*
          Stationary state
            We1 	LO
            We2 	HI
            We3 	HI
            We4 	LO
            We5 	LO
            We6   	HI
            We7   	HI

            1   	LO
            2   	LO

            We8     LO - U3 12
            We9     LO - U3 13
            We10    LO - U3 4
        */

        Set<ComponentPinState> componentPinStatesInitial = Set.of(
                new ComponentPinState(inPinHeaderId, 1, PinState.HIGH), // we1
                new ComponentPinState(inPinHeaderId, 2, PinState.HIGH), // we2
                new ComponentPinState(inPinHeaderId, 3, PinState.HIGH), // we3
                new ComponentPinState(inPinHeaderId, 4, PinState.HIGH),  // we4
                new ComponentPinState(inPinHeaderId, 5, PinState.LOW), // we5
                new ComponentPinState(inPinHeaderId, 6, PinState.HIGH), // we6
                new ComponentPinState(inPinHeaderId, 7, PinState.HIGH) // we7
        );

        // simulate
        Map<Integer, Set<ComponentPinState>> result = userInterface.simulation(componentPinStatesInitial, 2);
        assertEquals(3, result.size());
        assertTrue(result.containsKey(0)); // tick 0
        assertTrue(result.containsKey(1)); // tick 1
        assertTrue(result.containsKey(2)); // tick 2

        Set<ComponentPinState> statesTick0 = result.get(0);
        assertTrue(statesTick0.contains(new ComponentPinState(outPinHeaderId, 1, PinState.LOW)));
        assertTrue(statesTick0.contains(new ComponentPinState(outPinHeaderId, 2, PinState.LOW)));

        Set<ComponentPinState> statesTick1 = result.get(1);
        assertTrue(statesTick1.contains(new ComponentPinState(outPinHeaderId, 1, PinState.LOW)));
        assertTrue(statesTick1.contains(new ComponentPinState(outPinHeaderId, 2, PinState.LOW)));

        Set<ComponentPinState> statesTick2 = result.get(2);
        assertTrue(statesTick2.contains(new ComponentPinState(outPinHeaderId, 1, PinState.LOW)));
        assertTrue(statesTick2.contains(new ComponentPinState(outPinHeaderId, 2, PinState.HIGH)));

        /*
                    Tick0	Tick1   Tick2
            1   	LO	    LO	    LO
            2   	LO	    LO	    HI
        */
    }
}
