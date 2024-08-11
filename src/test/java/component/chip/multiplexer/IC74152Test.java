//package component.chip.multiplexer;
//
//import component.ChipComponent;
//import component.chip.ChipCreator;
//import component.pin.Pin;
//import edu.uj.po.simulation.interfaces.PinState;
//import edu.uj.po.simulation.interfaces.UnknownPin;
//import logicmatrix.multiplexer.IC74152LogicMatrix;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class IC74152Test {
//    ChipCreator creator = new IC74152Creator();
//    private ChipComponent ic74152;
//
//    @BeforeEach
//    void setUp() {
//        ic74152 = creator.createChip();
//    }
//
//    @Test
//    void testGetPin() throws UnknownPin {
//        assertNotNull(ic74152.getPin(1));
//        Assertions.assertEquals(PinState.UNKNOWN, ic74152.getPin(1).getState());
//    }
//
//    @Test
//    void testGetLogicMatrix() {
//        assertTrue(ic74152.getLogicMatrix() instanceof IC74152LogicMatrix);
//    }
//
//    @Test
//    void testSimulateWithInput_returnNotD0_yehorsTest() throws UnknownPin {
//        // given
//        ic74152.getPin(10).setState(PinState.HIGH); // A
//        ic74152.getPin(9).setState(PinState.LOW); // B
//        ic74152.getPin(8).setState(PinState.LOW); // C
//
//        ic74152.getPin(5).setState(PinState.LOW); // D0
//        ic74152.getPin(4).setState(PinState.HIGH); // D1
//        ic74152.getPin(3).setState(PinState.LOW); // D2
//        ic74152.getPin(2).setState(PinState.LOW); // D3
//        ic74152.getPin(1).setState(PinState.LOW); // D4
//        ic74152.getPin(13).setState(PinState.LOW); // D5
//        ic74152.getPin(12).setState(PinState.LOW); // D6
//        ic74152.getPin(11).setState(PinState.LOW); // D7
//
//        // when
//        List<Pin> outputPins = new ArrayList<>();
//        ic74152.simulate();
//        // then
//        assertEquals(outputPins.size(), 1);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.LOW);
//    }
//
//    @Test
//    void testSimulateWithInput_returnNotD0() throws UnknownPin {
//        // given
//        ic74152.getPin(10).setState(PinState.LOW); // A
//        ic74152.getPin(9).setState(PinState.LOW); // B
//        ic74152.getPin(8).setState(PinState.LOW); // C
//
//        ic74152.getPin(5).setState(PinState.LOW); // D0
//        ic74152.getPin(4).setState(PinState.LOW); // D1
//        ic74152.getPin(3).setState(PinState.HIGH); // D2
//        ic74152.getPin(2).setState(PinState.LOW); // D3
//        ic74152.getPin(1).setState(PinState.LOW); // D4
//        ic74152.getPin(13).setState(PinState.HIGH); // D5
//        ic74152.getPin(12).setState(PinState.HIGH); // D6
//        ic74152.getPin(11).setState(PinState.LOW); // D7
//
//        // when
//        List<Pin> outputPins = ic74152.simulate();
//        // then
//        assertEquals(outputPins.size(), 1);
//        assertEquals(outputPins.get(0).getState(), PinState.HIGH);
//    }
//
//    @Test
//    void testSimulateWithInput_returnNotD1() throws UnknownPin {
//        // given
//        ic74152.getPin(10).setState(PinState.HIGH); // A
//        ic74152.getPin(9).setState(PinState.LOW); // B
//        ic74152.getPin(8).setState(PinState.LOW); // C
//
//        ic74152.getPin(5).setState(PinState.LOW); // D0
//        ic74152.getPin(4).setState(PinState.LOW); // D1
//        ic74152.getPin(3).setState(PinState.HIGH); // D2
//        ic74152.getPin(2).setState(PinState.LOW); // D3
//        ic74152.getPin(1).setState(PinState.LOW); // D4
//        ic74152.getPin(13).setState(PinState.HIGH); // D5
//        ic74152.getPin(12).setState(PinState.HIGH); // D6
//        ic74152.getPin(11).setState(PinState.LOW); // D7
//
//        // when
//        List<Pin> outputPins = ic74152.simulate();
//        // then
//        assertEquals(outputPins.size(), 1);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH);
//    }
//
//    @Test
//    void testSimulateWithInput_returnNotD2() throws UnknownPin {
//        // given
//        ic74152.getPin(10).setState(PinState.LOW); // A
//        ic74152.getPin(9).setState(PinState.HIGH); // B
//        ic74152.getPin(8).setState(PinState.LOW); // C
//
//        ic74152.getPin(5).setState(PinState.LOW); // D0
//        ic74152.getPin(4).setState(PinState.LOW); // D1
//        ic74152.getPin(3).setState(PinState.HIGH); // D2
//        ic74152.getPin(2).setState(PinState.LOW); // D3
//        ic74152.getPin(1).setState(PinState.LOW); // D4
//        ic74152.getPin(13).setState(PinState.HIGH); // D5
//        ic74152.getPin(12).setState(PinState.HIGH); // D6
//        ic74152.getPin(11).setState(PinState.LOW); // D7
//
//        // when
//        List<Pin> outputPins = ic74152.simulate();
//        // then
//        assertEquals(outputPins.size(), 1);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.LOW);
//    }
//
//    @Test
//    void testSimulateWithInput_returnNotD3() throws UnknownPin {
//        // given
//        ic74152.getPin(10).setState(PinState.HIGH); // A
//        ic74152.getPin(9).setState(PinState.HIGH); // B
//        ic74152.getPin(8).setState(PinState.LOW); // C
//
//        ic74152.getPin(5).setState(PinState.LOW); // D0
//        ic74152.getPin(4).setState(PinState.LOW); // D1
//        ic74152.getPin(3).setState(PinState.HIGH); // D2
//        ic74152.getPin(2).setState(PinState.LOW); // D3
//        ic74152.getPin(1).setState(PinState.LOW); // D4
//        ic74152.getPin(13).setState(PinState.HIGH); // D5
//        ic74152.getPin(12).setState(PinState.HIGH); // D6
//        ic74152.getPin(11).setState(PinState.LOW); // D7
//
//        // when
//        List<Pin> outputPins = ic74152.simulate();
//        // then
//        assertEquals(outputPins.size(), 1);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH);
//    }
//
//    @Test
//    void testSimulateWithInput_returnNotD4() throws UnknownPin {
//        // given
//        ic74152.getPin(10).setState(PinState.LOW); // A
//        ic74152.getPin(9).setState(PinState.LOW); // B
//        ic74152.getPin(8).setState(PinState.HIGH); // C
//
//        ic74152.getPin(5).setState(PinState.LOW); // D0
//        ic74152.getPin(4).setState(PinState.LOW); // D1
//        ic74152.getPin(3).setState(PinState.HIGH); // D2
//        ic74152.getPin(2).setState(PinState.LOW); // D3
//        ic74152.getPin(1).setState(PinState.LOW); // D4
//        ic74152.getPin(13).setState(PinState.HIGH); // D5
//        ic74152.getPin(12).setState(PinState.HIGH); // D6
//        ic74152.getPin(11).setState(PinState.LOW); // D7
//
//        // when
//        List<Pin> outputPins = ic74152.simulate();
//        // then
//        assertEquals(outputPins.size(), 1);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH);
//    }
//
//    @Test
//    void testSimulateWithInput_returnNotD5() throws UnknownPin {
//        // given
//        ic74152.getPin(10).setState(PinState.HIGH); // A
//        ic74152.getPin(9).setState(PinState.LOW); // B
//        ic74152.getPin(8).setState(PinState.HIGH); // C
//
//        ic74152.getPin(5).setState(PinState.LOW); // D0
//        ic74152.getPin(4).setState(PinState.LOW); // D1
//        ic74152.getPin(3).setState(PinState.HIGH); // D2
//        ic74152.getPin(2).setState(PinState.LOW); // D3
//        ic74152.getPin(1).setState(PinState.LOW); // D4
//        ic74152.getPin(13).setState(PinState.HIGH); // D5
//        ic74152.getPin(12).setState(PinState.HIGH); // D6
//        ic74152.getPin(11).setState(PinState.LOW); // D7
//
//        // when
//        List<Pin> outputPins = ic74152.simulate();
//        // then
//        assertEquals(outputPins.size(), 1);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.LOW);
//    }
//
//    @Test
//    void testSimulateWithInput_returnNotD6() throws UnknownPin {
//        // given
//        ic74152.getPin(10).setState(PinState.LOW); // A
//        ic74152.getPin(9).setState(PinState.HIGH); // B
//        ic74152.getPin(8).setState(PinState.HIGH); // C
//
//        ic74152.getPin(5).setState(PinState.LOW); // D0
//        ic74152.getPin(4).setState(PinState.LOW); // D1
//        ic74152.getPin(3).setState(PinState.HIGH); // D2
//        ic74152.getPin(2).setState(PinState.LOW); // D3
//        ic74152.getPin(1).setState(PinState.LOW); // D4
//        ic74152.getPin(13).setState(PinState.HIGH); // D5
//        ic74152.getPin(12).setState(PinState.HIGH); // D6
//        ic74152.getPin(11).setState(PinState.LOW); // D7
//
//        // when
//        List<Pin> outputPins = ic74152.simulate();
//        // then
//        assertEquals(outputPins.size(), 1);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.LOW);
//    }
//
//    @Test
//    void testSimulateWithInput_returnNotD7() throws UnknownPin {
//        // given
//        ic74152.getPin(10).setState(PinState.HIGH); // A
//        ic74152.getPin(9).setState(PinState.HIGH); // B
//        ic74152.getPin(8).setState(PinState.HIGH); // C
//
//        ic74152.getPin(5).setState(PinState.LOW); // D0
//        ic74152.getPin(4).setState(PinState.LOW); // D1
//        ic74152.getPin(3).setState(PinState.HIGH); // D2
//        ic74152.getPin(2).setState(PinState.LOW); // D3
//        ic74152.getPin(1).setState(PinState.LOW); // D4
//        ic74152.getPin(13).setState(PinState.HIGH); // D5
//        ic74152.getPin(12).setState(PinState.HIGH); // D6
//        ic74152.getPin(11).setState(PinState.LOW); // D7
//
//        // when
//        List<Pin> outputPins = ic74152.simulate();
//        // then
//        assertEquals(outputPins.size(), 1);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH);
//    }
//
//    @Test
//    void testSimulateWithInput_whenOneOfABCUnknown_returnStateUnknown() throws UnknownPin {
//        // given
//        ic74152.getPin(10).setState(PinState.LOW); // A
//        ic74152.getPin(9).setState(PinState.UNKNOWN); // B
//        ic74152.getPin(8).setState(PinState.LOW); // C
//
//        ic74152.getPin(5).setState(PinState.LOW); // D0
//        ic74152.getPin(4).setState(PinState.LOW); // D1
//        ic74152.getPin(3).setState(PinState.HIGH); // D2
//        ic74152.getPin(2).setState(PinState.LOW); // D3
//        ic74152.getPin(1).setState(PinState.LOW); // D4
//        ic74152.getPin(13).setState(PinState.HIGH); // D5
//        ic74152.getPin(12).setState(PinState.HIGH); // D6
//        ic74152.getPin(11).setState(PinState.LOW); // D7
//
//        // when
//        List<Pin> outputPins = ic74152.simulate();
//        // then
//        assertEquals(outputPins.size(), 1);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.UNKNOWN);
//    }
//
//    // TODO: fix this test later (when one of D inputs is UNKNOWN and it'll not be used in the output
//    //  -> then the output shouldn't be UNKNOWN -> this input doesn't affect the output)
//    @Test
//    void testSimulateWithInput_whenOneOfDInputsUnknown_returnStateUnknown() throws UnknownPin {
//        // given
//        ic74152.getPin(10).setState(PinState.LOW); // A
//        ic74152.getPin(9).setState(PinState.HIGH); // B
//        ic74152.getPin(8).setState(PinState.LOW); // C
//
//        ic74152.getPin(5).setState(PinState.LOW); // D0
//        ic74152.getPin(4).setState(PinState.LOW); // D1
//        ic74152.getPin(3).setState(PinState.UNKNOWN); // D2
//        ic74152.getPin(2).setState(PinState.LOW); // D3
//        ic74152.getPin(1).setState(PinState.LOW); // D4
//        ic74152.getPin(13).setState(PinState.HIGH); // D5
//        ic74152.getPin(12).setState(PinState.HIGH); // D6
//        ic74152.getPin(11).setState(PinState.LOW); // D7
//
//        // when
//        List<Pin> outputPins = ic74152.simulate();
//        // then
//        assertEquals(outputPins.size(), 1);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.UNKNOWN);
//    }
//
//    @Test
//    void testSimulateWithInput_whenAllInputsUnknown_returnStateUnknown() {
//        // given/when
//        List<Pin> outputPins = ic74152.simulate();
//        // then
//        assertEquals(outputPins.size(), 1);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.UNKNOWN);
//    }
//}
