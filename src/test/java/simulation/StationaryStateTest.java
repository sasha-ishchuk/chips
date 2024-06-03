package simulation;

import org.example.Simulation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import component.LogicComponent;
import component.PinHeaderComponent;
import component.pin.PinType;
import component.pinheader.in.InPinHeaderCreator;
import component.pinheader.out.OutPinHeaderCreator;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.ShortCircuitException;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.interfaces.UnknownComponent;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.interfaces.UnknownStateException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StationaryStateTest {
    Simulation userInterface;

    @BeforeEach
    public void setUp() {
        userInterface = new Simulation();
    }

    @Test
    public void testInPinHeader() {
        PinHeaderComponent inPinHeader = new InPinHeaderCreator().createPinHeader(3);
        inPinHeader.getPin(1).setState(PinState.HIGH);
        inPinHeader.getPin(2).setState(PinState.LOW);
        inPinHeader.getPin(3).setState(PinState.HIGH);
        assertEquals(3, inPinHeader.getSize());
        assertEquals(PinType.OUT, inPinHeader.getPin(1).getType());
        assertEquals(PinType.OUT, inPinHeader.getPin(2).getType());
        assertEquals(PinType.OUT, inPinHeader.getPin(3).getType());
        Assertions.assertEquals(PinState.HIGH, inPinHeader.getPin(1).getState());
        Assertions.assertEquals(PinState.LOW, inPinHeader.getPin(2).getState());
        Assertions.assertEquals(PinState.HIGH, inPinHeader.getPin(3).getState());
    }

    @Test
    public void testOutPinHeader() {
        PinHeaderComponent outPinHeader = new OutPinHeaderCreator().createPinHeader(3);
        outPinHeader.getPin(1).setState(PinState.HIGH);
        outPinHeader.getPin(2).setState(PinState.LOW);
        outPinHeader.getPin(3).setState(PinState.HIGH);
        assertEquals(3, outPinHeader.getSize());
        assertEquals(PinType.IN, outPinHeader.getPin(1).getType());
        assertEquals(PinType.IN, outPinHeader.getPin(2).getType());
        assertEquals(PinType.IN, outPinHeader.getPin(3).getType());
        Assertions.assertEquals(PinState.HIGH, outPinHeader.getPin(1).getState());
        Assertions.assertEquals(PinState.LOW, outPinHeader.getPin(2).getState());
        Assertions.assertEquals(PinState.HIGH, outPinHeader.getPin(3).getState());
    }

    @Test
    public void testStationaryState_forNandAndNot() throws UnknownChip, UnknownPin, ShortCircuitException, UnknownComponent, UnknownStateException {
        int inPinHeaderId = userInterface.createInputPinHeader(3);
        Set<ComponentPinState> componentPinStates = Set.of(
                new ComponentPinState(inPinHeaderId, 1, PinState.HIGH), // we1
                new ComponentPinState(inPinHeaderId, 2, PinState.LOW), // we2
                new ComponentPinState(inPinHeaderId, 3, PinState.HIGH) // we4
        );
        int nand7400 = userInterface.createChip(7400);
        int not7404 = userInterface.createChip(7404);

        int outPinHeaderId = userInterface.createOutputPinHeader(4);

        userInterface.connect(inPinHeaderId, 1, nand7400, 13);
        userInterface.connect(inPinHeaderId, 2, nand7400, 12);
        userInterface.connect(inPinHeaderId, 3, nand7400, 4);
        userInterface.connect(nand7400, 11, not7404, 13);
        userInterface.connect(not7404, 12, not7404, 11);
        userInterface.connect(not7404, 12, nand7400, 5);

        userInterface.connect(nand7400, 11, outPinHeaderId, 1);
        userInterface.connect(not7404, 12, outPinHeaderId, 2);
        userInterface.connect(not7404, 10, outPinHeaderId, 3);
        userInterface.connect(nand7400, 6, outPinHeaderId, 4);

        userInterface.stationaryState(componentPinStates);
        /*
        -> ok

        1, 11: HIGH -> 3, 1: HIGH (wy1)

        2, 12: LOW -> 3, 2: LOW (wy2)

        2, 10: HIGH -> 3, 3: HIGH (wy3)

        1, 6: HIGH -> 3, 4: HIGH (wy4)
         */
    }

    @Test
    public void testStationaryState_forAndAndNot_1() throws UnknownChip, UnknownPin, ShortCircuitException, UnknownComponent, UnknownStateException {
        int inPinHeaderId = userInterface.createInputPinHeader(3);
        Set<ComponentPinState> componentPinStates = Set.of(
                new ComponentPinState(inPinHeaderId, 1, PinState.HIGH), // we1
                new ComponentPinState(inPinHeaderId, 2, PinState.LOW), // we2
                new ComponentPinState(inPinHeaderId, 3, PinState.HIGH) // we4
        );
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

        userInterface.stationaryState(componentPinStates);
        LogicComponent outPinHeader = userInterface.getComponentById(outPinHeaderId);
        Assertions.assertEquals(PinState.LOW, outPinHeader.getPin(1).getState());
        Assertions.assertEquals(PinState.HIGH, outPinHeader.getPin(2).getState());
        Assertions.assertEquals(PinState.LOW, outPinHeader.getPin(3).getState());
        Assertions.assertEquals(PinState.HIGH, outPinHeader.getPin(4).getState());
        /*
        -> ok (zgadza się z przykładem Oramusa)

        1, 11: LOW -> 3, 1: LOW (wy1)

        2, 12: HIGH -> 3, 2: HIGH (wy2)

        2, 10: LOW -> 3, 3: LOW (wy3)

        1, 6: HIGH -> 3, 4: HIGH (wy4)

         */
    }

    @Test
    public void testStationaryState_forAndAndNot_2() throws UnknownChip, UnknownPin, ShortCircuitException, UnknownComponent, UnknownStateException {
        int inPinHeaderId = userInterface.createInputPinHeader(3);
        Set<ComponentPinState> componentPinStates = Set.of(
                new ComponentPinState(inPinHeaderId, 1, PinState.HIGH), // we1
                new ComponentPinState(inPinHeaderId, 2, PinState.HIGH), // we2
                new ComponentPinState(inPinHeaderId, 3, PinState.HIGH) // we4
        );
        int nand7408 = userInterface.createChip(7408);
        int not7404 = userInterface.createChip(7404);

        int outPinHeaderId = userInterface.createOutputPinHeader(4);

        userInterface.connect(inPinHeaderId, 1, nand7408, 13);
        userInterface.connect(inPinHeaderId, 2, nand7408, 12);
        userInterface.connect(inPinHeaderId, 3, nand7408, 4);
        userInterface.connect(nand7408, 11, not7404, 13);
        userInterface.connect(not7404, 12, not7404, 11);
        userInterface.connect(not7404, 12, nand7408, 5);

        userInterface.connect(nand7408, 11, outPinHeaderId, 1);
        userInterface.connect(not7404, 12, outPinHeaderId, 2);
        userInterface.connect(not7404, 10, outPinHeaderId, 3);
        userInterface.connect(nand7408, 6, outPinHeaderId, 4);

        userInterface.stationaryState(componentPinStates);
        /*
        -> ok (zgadza się z przykładem Oramusa)

        1, 11: LOW -> 3, 1: LOW (wy1)

        2, 12: HIGH -> 3, 2: HIGH (wy2)

        2, 10: LOW -> 3, 3: LOW (wy3)

        1, 6: HIGH -> 3, 4: HIGH (wy4)

        __________IN PINS__________
        1, 4: HIGH
        1, 5: LOW
        1, 13: HIGH
        1, 12: HIGH
        __________OUT PINS__________
        3, 1: HIGH
        3, 2: LOW
        3, 3: HIGH
        3, 4: LOW

         */
    }

    @Test
    public void testStationaryState_forThreeAnd_oramusExampleForDeleteComponent() throws UnknownChip, UnknownPin, ShortCircuitException, UnknownComponent, UnknownStateException {
        // create components
        int inPinHeaderId = userInterface.createInputPinHeader(7);
        int and7408_1 = userInterface.createChip(7408);
        int and7408_2 = userInterface.createChip(7408);
        int and7408_3 = userInterface.createChip(7408);
        int outPinHeaderId = userInterface.createOutputPinHeader(2);

        userInterface.connect(inPinHeaderId, 1, and7408_1, 1); // 0
        userInterface.connect(inPinHeaderId, 2, and7408_1, 2); // 1
        userInterface.connect(inPinHeaderId, 3, and7408_2, 1); // 1
        userInterface.connect(inPinHeaderId, 4, and7408_2, 2); // 0
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
        System.out.println("OK");

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

    }
}