package component.chip.delayelements;

import component.chip.delayelements.IC7431Creator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import component.ChipComponent;
import component.chip.ChipCreator;
import component.pin.Pin;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.UnknownPin;
import logicmatrix.delayelements.IC7431LogicMatrix;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IC7431Test {
    ChipCreator creator = new IC7431Creator();
    private ChipComponent ic7431;

    @BeforeEach
    void setUp() {
        ic7431 = creator.createChip();
    }

    @Test
    void testGetPin() throws UnknownPin {
        assertNotNull(ic7431.getPin(1));
        Assertions.assertEquals(PinState.UNKNOWN, ic7431.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7431.getLogicMatrix() instanceof IC7431LogicMatrix);
    }

    @Test
    void testSimulateWithInput() throws UnknownPin {
        // given
        ic7431.getPin(1).setState(PinState.HIGH);
        ic7431.getPin(3).setState(PinState.LOW);
        ic7431.getPin(5).setState(PinState.HIGH);
        ic7431.getPin(6).setState(PinState.HIGH);
        ic7431.getPin(10).setState(PinState.LOW);
        ic7431.getPin(11).setState(PinState.HIGH);
        ic7431.getPin(13).setState(PinState.HIGH);
        ic7431.getPin(15).setState(PinState.HIGH);
        // when
        List<Pin> outputPins = ic7431.simulate();
        // then
        assertEquals(outputPins.size(), 6);
        assertEquals(outputPins.get(0).getId(), 2);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.LOW);
        assertEquals(outputPins.get(1).getId(), 4);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.LOW);
        assertEquals(outputPins.get(2).getId(), 7);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.LOW);
        assertEquals(outputPins.get(3).getId(), 9);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH);
        assertEquals(outputPins.get(4).getId(), 12);
        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH);
        assertEquals(outputPins.get(5).getId(), 14);
        Assertions.assertEquals(outputPins.get(5).getState(), PinState.LOW);
    }
}
