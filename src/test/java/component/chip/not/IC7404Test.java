//package component.chip.not;
//
//import component.chip.not.IC7404Creator;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import component.ChipComponent;
//import component.chip.ChipCreator;
//import component.pin.Pin;
//import edu.uj.po.simulation.interfaces.PinState;
//import edu.uj.po.simulation.interfaces.UnknownPin;
//import logicmatrix.not.IC7404LogicMatrix;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class IC7404Test {
//    ChipCreator creator = new IC7404Creator();
//    private ChipComponent ic7404;
//
//    @BeforeEach
//    void setUp() {
//        ic7404 = creator.createChip();
//    }
//
//    @Test
//    void testGetPin() throws UnknownPin {
//        assertNotNull(ic7404.getPin(1));
//        Assertions.assertEquals(PinState.UNKNOWN, ic7404.getPin(1).getState());
//    }
//
//    @Test
//    void testGetLogicMatrix() {
//        assertTrue(ic7404.getLogicMatrix() instanceof IC7404LogicMatrix);
//    }
//
//    @Test
//    void testSimulateWithInput() throws UnknownPin {
//        // given
//        ic7404.getPin(1).setState(PinState.LOW);
//        ic7404.getPin(3).setState(PinState.LOW);
//        ic7404.getPin(5).setState(PinState.HIGH);
//        ic7404.getPin(9).setState(PinState.LOW);
//        ic7404.getPin(11).setState(PinState.HIGH);
//        // when
//        List<Pin> outputPins = new ArrayList<>();
//        ic7404.simulate();
//        // then
//        assertEquals(outputPins.size(), 6);
//        assertEquals(outputPins.get(0).getId(), 2);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH);
//        assertEquals(outputPins.get(1).getId(), 4);
//        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH);
//        assertEquals(outputPins.get(2).getId(), 6);
//        Assertions.assertEquals(outputPins.get(2).getState(), PinState.LOW);
//        assertEquals(outputPins.get(3).getId(), 8);
//        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH);
//        assertEquals(outputPins.get(4).getId(), 10);
//        Assertions.assertEquals(outputPins.get(4).getState(), PinState.LOW);
//        assertEquals(outputPins.get(5).getId(), 12);
//        Assertions.assertEquals(outputPins.get(5).getState(), PinState.UNKNOWN);
//    }
//}
