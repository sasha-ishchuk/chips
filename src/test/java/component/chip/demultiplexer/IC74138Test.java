//package component.chip.demultiplexer;
//
//import component.chip.demultiplexer.IC74138Creator;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import component.ChipComponent;
//import component.chip.ChipCreator;
//import component.pin.Pin;
//import edu.uj.po.simulation.interfaces.PinState;
//import edu.uj.po.simulation.interfaces.UnknownPin;
//import logicmatrix.demultiplexer.IC74138LogicMatrix;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class IC74138Test {
//    ChipCreator creator = new IC74138Creator();
//    private ChipComponent ic74138;
//
//    @BeforeEach
//    void setUp() {
//        ic74138 = creator.createChip();
//    }
//
//    @Test
//    void testGetPin() throws UnknownPin {
//        assertNotNull(ic74138.getPin(1));
//        Assertions.assertEquals(PinState.UNKNOWN, ic74138.getPin(1).getState());
//    }
//
//    @Test
//    void testGetLogicMatrix() {
//        assertTrue(ic74138.getLogicMatrix() instanceof IC74138LogicMatrix);
//    }
//
//    @Test
//    void testSimulateWithInput_whenNotG2IsHigh() throws UnknownPin {
//        // given
//        ic74138.getPin(1).setState(PinState.HIGH); // A - could be HIGH or LOW
//        ic74138.getPin(2).setState(PinState.LOW); // B - could be HIGH or LOW
//        ic74138.getPin(3).setState(PinState.LOW); // C - could be HIGH or LOW
//        ic74138.getPin(6).setState(PinState.HIGH);  // G1 - could be HIGH or LOW
//
//        // it gives us notG2 = HIGH (else it gives us notG2 = LOW)
//        ic74138.getPin(4).setState(PinState.HIGH); // ~G2A
//        ic74138.getPin(5).setState(PinState.HIGH); // ~G2B
//        // when
//        List<Pin> outputPins = new ArrayList<>();
//        ic74138.simulate();
//        // then
//        assertEquals(outputPins.size(), 8);
//        for (Pin pin : outputPins) {
//            Assertions.assertEquals(pin.getState(), PinState.HIGH);
//        }
//    }
//
//    @Test
//    void testSimulateWithInput_whenG1IsLow() throws UnknownPin {
//        // given
//        ic74138.getPin(1).setState(PinState.HIGH); // A - could be HIGH or LOW
//        ic74138.getPin(2).setState(PinState.LOW); // B - could be HIGH or LOW
//        ic74138.getPin(3).setState(PinState.LOW); // C - could be HIGH or LOW
//        // when notG2A and notG2B are HIGH it'll give us notG2 = HIGH, else notG2 = LOW
//        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
//        ic74138.getPin(5).setState(PinState.HIGH); // ~G2B
//
//        ic74138.getPin(6).setState(PinState.LOW);  // G1
//        // when
//        List<Pin> outputPins = ic74138.simulate();
//        // then
//        assertEquals(outputPins.size(), 8);
//        for (Pin pin : outputPins) {
//            Assertions.assertEquals(pin.getState(), PinState.HIGH);
//        }
//    }
//
//    @Test
//    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateZero() throws UnknownPin {
//        // given
//        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
//        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
//        ic74138.getPin(6).setState(PinState.HIGH);  // G1
//
//        ic74138.getPin(1).setState(PinState.LOW); // A
//        ic74138.getPin(2).setState(PinState.LOW); // B
//        ic74138.getPin(3).setState(PinState.LOW); // C
//        // when
//        List<Pin> outputPins = ic74138.simulate();
//        // then
//        assertEquals(outputPins.size(), 8);
//        assertEquals(outputPins.get(0).getId(), 15);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.LOW); // ~Y0
//        assertEquals(outputPins.get(1).getId(), 14);
//        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // ~Y1
//        assertEquals(outputPins.get(2).getId(), 13);
//        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // ~Y2
//        assertEquals(outputPins.get(3).getId(), 12);
//        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // ~Y3
//        assertEquals(outputPins.get(4).getId(), 11);
//        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // ~Y4
//        assertEquals(outputPins.get(5).getId(), 10);
//        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // ~Y5
//        assertEquals(outputPins.get(6).getId(), 9);
//        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // ~Y6
//        assertEquals(outputPins.get(7).getId(), 7);
//        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // ~Y7
//    }
//
//    @Test
//    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateOne() throws UnknownPin {
//        // given
//        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
//        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
//        ic74138.getPin(6).setState(PinState.HIGH);  // G1
//
//        ic74138.getPin(1).setState(PinState.HIGH); // A
//        ic74138.getPin(2).setState(PinState.LOW); // B
//        ic74138.getPin(3).setState(PinState.LOW); // C
//        // when
//        List<Pin> outputPins = ic74138.simulate();
//        // then
//        assertEquals(outputPins.size(), 8);
//        assertEquals(outputPins.get(0).getId(), 15);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // ~Y0
//        assertEquals(outputPins.get(1).getId(), 14);
//        Assertions.assertEquals(outputPins.get(1).getState(), PinState.LOW); // ~Y1
//        assertEquals(outputPins.get(2).getId(), 13);
//        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // ~Y2
//        assertEquals(outputPins.get(3).getId(), 12);
//        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // ~Y3
//        assertEquals(outputPins.get(4).getId(), 11);
//        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // ~Y4
//        assertEquals(outputPins.get(5).getId(), 10);
//        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // ~Y5
//        assertEquals(outputPins.get(6).getId(), 9);
//        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // ~Y6
//        assertEquals(outputPins.get(7).getId(), 7);
//        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // ~Y7
//    }
//
//    @Test
//    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateTwo() throws UnknownPin {
//        // given
//        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
//        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
//        ic74138.getPin(6).setState(PinState.HIGH);  // G1
//
//        ic74138.getPin(1).setState(PinState.LOW); // A
//        ic74138.getPin(2).setState(PinState.HIGH); // B
//        ic74138.getPin(3).setState(PinState.LOW); // C
//        // when
//        List<Pin> outputPins = ic74138.simulate();
//        // then
//        assertEquals(outputPins.size(), 8);
//        assertEquals(outputPins.get(0).getId(), 15);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // ~Y0
//        assertEquals(outputPins.get(1).getId(), 14);
//        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // ~Y1
//        assertEquals(outputPins.get(2).getId(), 13);
//        Assertions.assertEquals(outputPins.get(2).getState(), PinState.LOW); // ~Y2
//        assertEquals(outputPins.get(3).getId(), 12);
//        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // ~Y3
//        assertEquals(outputPins.get(4).getId(), 11);
//        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // ~Y4
//        assertEquals(outputPins.get(5).getId(), 10);
//        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // ~Y5
//        assertEquals(outputPins.get(6).getId(), 9);
//        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // ~Y6
//        assertEquals(outputPins.get(7).getId(), 7);
//        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // ~Y7
//    }
//
//    @Test
//    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateThree() throws UnknownPin {
//        // given
//        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
//        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
//        ic74138.getPin(6).setState(PinState.HIGH);  // G1
//
//        ic74138.getPin(1).setState(PinState.HIGH); // A
//        ic74138.getPin(2).setState(PinState.HIGH); // B
//        ic74138.getPin(3).setState(PinState.LOW); // C
//        // when
//        List<Pin> outputPins = ic74138.simulate();
//        // then
//        assertEquals(outputPins.size(), 8);
//        assertEquals(outputPins.get(0).getId(), 15);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // ~Y0
//        assertEquals(outputPins.get(1).getId(), 14);
//        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // ~Y1
//        assertEquals(outputPins.get(2).getId(), 13);
//        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // ~Y2
//        assertEquals(outputPins.get(3).getId(), 12);
//        Assertions.assertEquals(outputPins.get(3).getState(), PinState.LOW); // ~Y3
//        assertEquals(outputPins.get(4).getId(), 11);
//        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // ~Y4
//        assertEquals(outputPins.get(5).getId(), 10);
//        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // ~Y5
//        assertEquals(outputPins.get(6).getId(), 9);
//        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // ~Y6
//        assertEquals(outputPins.get(7).getId(), 7);
//        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // ~Y7
//    }
//
//    @Test
//    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateFour() throws UnknownPin {
//        // given
//        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
//        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
//        ic74138.getPin(6).setState(PinState.HIGH);  // G1
//
//        ic74138.getPin(1).setState(PinState.LOW); // A
//        ic74138.getPin(2).setState(PinState.LOW); // B
//        ic74138.getPin(3).setState(PinState.HIGH); // C
//        // when
//        List<Pin> outputPins = ic74138.simulate();
//        // then
//        assertEquals(outputPins.size(), 8);
//        assertEquals(outputPins.get(0).getId(), 15);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // ~Y0
//        assertEquals(outputPins.get(1).getId(), 14);
//        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // ~Y1
//        assertEquals(outputPins.get(2).getId(), 13);
//        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // ~Y2
//        assertEquals(outputPins.get(3).getId(), 12);
//        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // ~Y3
//        assertEquals(outputPins.get(4).getId(), 11);
//        Assertions.assertEquals(outputPins.get(4).getState(), PinState.LOW); // ~Y4
//        assertEquals(outputPins.get(5).getId(), 10);
//        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // ~Y5
//        assertEquals(outputPins.get(6).getId(), 9);
//        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // ~Y6
//        assertEquals(outputPins.get(7).getId(), 7);
//        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // ~Y7
//    }
//
//    @Test
//    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateFive() throws UnknownPin {
//        // given
//        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
//        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
//        ic74138.getPin(6).setState(PinState.HIGH);  // G1
//
//        ic74138.getPin(1).setState(PinState.HIGH); // A
//        ic74138.getPin(2).setState(PinState.LOW); // B
//        ic74138.getPin(3).setState(PinState.HIGH); // C
//        // when
//        List<Pin> outputPins = ic74138.simulate();
//        // then
//        assertEquals(outputPins.size(), 8);
//        assertEquals(outputPins.get(0).getId(), 15);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // ~Y0
//        assertEquals(outputPins.get(1).getId(), 14);
//        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // ~Y1
//        assertEquals(outputPins.get(2).getId(), 13);
//        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // ~Y2
//        assertEquals(outputPins.get(3).getId(), 12);
//        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // ~Y3
//        assertEquals(outputPins.get(4).getId(), 11);
//        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // ~Y4
//        assertEquals(outputPins.get(5).getId(), 10);
//        Assertions.assertEquals(outputPins.get(5).getState(), PinState.LOW); // ~Y5
//        assertEquals(outputPins.get(6).getId(), 9);
//        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // ~Y6
//        assertEquals(outputPins.get(7).getId(), 7);
//        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // ~Y7
//    }
//
//    @Test
//    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateSix() throws UnknownPin {
//        // given
//        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
//        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
//        ic74138.getPin(6).setState(PinState.HIGH);  // G1
//
//        ic74138.getPin(1).setState(PinState.LOW); // A
//        ic74138.getPin(2).setState(PinState.HIGH); // B
//        ic74138.getPin(3).setState(PinState.HIGH); // C
//        // when
//        List<Pin> outputPins = ic74138.simulate();
//        // then
//        assertEquals(outputPins.size(), 8);
//        assertEquals(outputPins.get(0).getId(), 15);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // ~Y0
//        assertEquals(outputPins.get(1).getId(), 14);
//        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // ~Y1
//        assertEquals(outputPins.get(2).getId(), 13);
//        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // ~Y2
//        assertEquals(outputPins.get(3).getId(), 12);
//        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // ~Y3
//        assertEquals(outputPins.get(4).getId(), 11);
//        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // ~Y4
//        assertEquals(outputPins.get(5).getId(), 10);
//        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // ~Y5
//        assertEquals(outputPins.get(6).getId(), 9);
//        Assertions.assertEquals(outputPins.get(6).getState(), PinState.LOW); // ~Y6
//        assertEquals(outputPins.get(7).getId(), 7);
//        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // ~Y7
//    }
//
//    @Test
//    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateSeven() throws UnknownPin {
//        // given
//        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
//        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
//        ic74138.getPin(6).setState(PinState.HIGH);  // G1
//
//        ic74138.getPin(1).setState(PinState.HIGH); // A
//        ic74138.getPin(2).setState(PinState.HIGH); // B
//        ic74138.getPin(3).setState(PinState.HIGH); // C
//        // when
//        List<Pin> outputPins = ic74138.simulate();
//        // then
//        assertEquals(outputPins.size(), 8);
//        assertEquals(outputPins.get(0).getId(), 15);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // ~Y0
//        assertEquals(outputPins.get(1).getId(), 14);
//        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // ~Y1
//        assertEquals(outputPins.get(2).getId(), 13);
//        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // ~Y2
//        assertEquals(outputPins.get(3).getId(), 12);
//        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // ~Y3
//        assertEquals(outputPins.get(4).getId(), 11);
//        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // ~Y4
//        assertEquals(outputPins.get(5).getId(), 10);
//        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // ~Y5
//        assertEquals(outputPins.get(6).getId(), 9);
//        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // ~Y6
//        assertEquals(outputPins.get(7).getId(), 7);
//        Assertions.assertEquals(outputPins.get(7).getState(), PinState.LOW); // ~Y7
//    }
//
//    @Test
//    void testSimulateWithInput_whenNotG2IsUnknown_returnAllStatesUnknown() throws UnknownPin {
//        // given
//        ic74138.getPin(1).setState(PinState.HIGH); // A - could be HIGH or LOW
//        ic74138.getPin(2).setState(PinState.LOW); // B - could be HIGH or LOW
//        ic74138.getPin(3).setState(PinState.LOW); // C - could be HIGH or LOW
//        ic74138.getPin(6).setState(PinState.HIGH);  // G1 - could be HIGH or LOW
//
//        ic74138.getPin(4).setState(PinState.UNKNOWN); // ~G2A
//        ic74138.getPin(5).setState(PinState.HIGH); // ~G2B
//        // when
//        List<Pin> outputPins = ic74138.simulate();
//        // then
//        assertEquals(outputPins.size(), 8);
//        for (Pin pin : outputPins) {
//            Assertions.assertEquals(pin.getState(), PinState.UNKNOWN);
//        }
//    }
//
//    @Test
//    void testSimulateWithInput_whenG1IsUnknown_returnAllStatesUnknown() throws UnknownPin {
//        // given
//        ic74138.getPin(1).setState(PinState.HIGH); // A - could be HIGH or LOW
//        ic74138.getPin(2).setState(PinState.LOW); // B - could be HIGH or LOW
//        ic74138.getPin(3).setState(PinState.LOW); // C - could be HIGH or LOW
//        ic74138.getPin(6).setState(PinState.UNKNOWN);  // G1 - could be HIGH or LOW
//
//        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
//        ic74138.getPin(5).setState(PinState.HIGH); // ~G2B
//        // when
//        List<Pin> outputPins = ic74138.simulate();
//        // then
//        assertEquals(outputPins.size(), 8);
//        for (Pin pin : outputPins) {
//            Assertions.assertEquals(pin.getState(), PinState.UNKNOWN);
//        }
//    }
//
//    @Test
//    void testSimulateWithInput_whenOneOfABCUnknown_returnAllStatesUnknown() throws UnknownPin {
//        // given
//        ic74138.getPin(1).setState(PinState.UNKNOWN); // A - could be HIGH or LOW
//        ic74138.getPin(2).setState(PinState.LOW); // B - could be HIGH or LOW
//        ic74138.getPin(3).setState(PinState.LOW); // C - could be HIGH or LOW
//        ic74138.getPin(6).setState(PinState.HIGH);  // G1 - could be HIGH or LOW
//
//        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
//        ic74138.getPin(5).setState(PinState.HIGH); // ~G2B
//        // when
//        List<Pin> outputPins = ic74138.simulate();
//        // then
//        assertEquals(outputPins.size(), 8);
//        for (Pin pin : outputPins) {
//            Assertions.assertEquals(pin.getState(), PinState.HIGH);
//        }
//    }
//}
