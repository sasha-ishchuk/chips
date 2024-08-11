//package component.chip.buffer;
//
//import component.chip.buffer.IC7434Creator;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import component.ChipComponent;
//import component.chip.ChipCreator;
//import component.pin.Pin;
//import edu.uj.po.simulation.interfaces.PinState;
//import edu.uj.po.simulation.interfaces.UnknownPin;
//import logicmatrix.buffer.IC7434LogicMatrix;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class IC7434Test {
//    ChipCreator creator = new IC7434Creator();
//    private ChipComponent ic7434;
//
//    @BeforeEach
//    void setUp() {
//        ic7434 = creator.createChip();
//    }
//
//    @Test
//    void testGetPin() throws UnknownPin {
//        assertNotNull(ic7434.getPin(1));
//        Assertions.assertEquals(PinState.UNKNOWN, ic7434.getPin(1).getState());
//    }
//
//    @Test
//    void testGetLogicMatrix() {
//        assertTrue(ic7434.getLogicMatrix() instanceof IC7434LogicMatrix);
//    }
//
//    @Test
//    void testSimulateWithInput() throws UnknownPin {
//        // given
//        ic7434.getPin(1).setState(PinState.LOW);
//        ic7434.getPin(3).setState(PinState.LOW);
//        ic7434.getPin(5).setState(PinState.HIGH);
//        ic7434.getPin(9).setState(PinState.LOW);
//        ic7434.getPin(13).setState(PinState.HIGH);
//        // when
//        List<Pin> outputPins = new ArrayList<>();
//        ic7434.simulate();
//        // then
//        assertEquals(outputPins.size(), 6);
//        assertEquals(outputPins.get(0).getId(), 2);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.LOW);
//        assertEquals(outputPins.get(1).getId(), 4);
//        Assertions.assertEquals(outputPins.get(1).getState(), PinState.LOW);
//        assertEquals(outputPins.get(2).getId(), 6);
//        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH);
//        assertEquals(outputPins.get(3).getId(), 8);
//        Assertions.assertEquals(outputPins.get(3).getState(), PinState.LOW);
//        assertEquals(outputPins.get(4).getId(), 10);
//        Assertions.assertEquals(outputPins.get(4).getState(), PinState.UNKNOWN);
//        assertEquals(outputPins.get(5).getId(), 12);
//        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH);
//    }
//}
