//package component.chip.nand;
//
//import component.chip.nand.IC7420Creator;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import component.ChipComponent;
//import component.chip.ChipCreator;
//import component.pin.Pin;
//import edu.uj.po.simulation.interfaces.PinState;
//import edu.uj.po.simulation.interfaces.UnknownPin;
//import logicmatrix.nand.IC7420LogicMatrix;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class IC7420Test {
//    ChipCreator creator = new IC7420Creator();
//    private ChipComponent ic7420;
//
//    @BeforeEach
//    void setUp() {
//        ic7420 = creator.createChip();
//    }
//
//    @Test
//    void testGetPin() throws UnknownPin {
//        assertNotNull(ic7420.getPin(1));
//        Assertions.assertEquals(PinState.UNKNOWN, ic7420.getPin(1).getState());
//    }
//
//    @Test
//    void testGetLogicMatrix() {
//        assertTrue(ic7420.getLogicMatrix() instanceof IC7420LogicMatrix);
//    }
//
//    @Test
//    void testSimulateWithInput() throws UnknownPin {
//        // given
//        ic7420.getPin(1).setState(PinState.HIGH);
//        ic7420.getPin(2).setState(PinState.LOW);
//        ic7420.getPin(4).setState(PinState.HIGH);
//        ic7420.getPin(5).setState(PinState.LOW);
//
//        ic7420.getPin(9).setState(PinState.HIGH);
//        ic7420.getPin(10).setState(PinState.HIGH);
//        ic7420.getPin(12).setState(PinState.HIGH);
//        ic7420.getPin(13).setState(PinState.HIGH);
//        // when
//        List<Pin> outputPins = new ArrayList<>();
//        ic7420.simulate();
//        // then
//        assertEquals(outputPins.size(), 2);
//        assertEquals(outputPins.get(0).getId(), 6);
//        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH);
//        assertEquals(outputPins.get(1).getId(), 8);
//        Assertions.assertEquals(outputPins.get(1).getState(), PinState.LOW);
//    }
//}
