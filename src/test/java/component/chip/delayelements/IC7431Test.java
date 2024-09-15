package component.chip.delayelements;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.delayelements.IC7431Creator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.delayelements.IC7431LogicMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IC7431Test {
    private final ChipCreator creator = new IC7431Creator();
    private ChipComponent ic7431;

    @BeforeEach
    void setUp() {
        ic7431 = creator.createChip();
    }

    @Test
    void testGetPin() {
        assertNotNull(ic7431.getPin(1));
        assertEquals(PinState.UNKNOWN, ic7431.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7431.getLogicMatrix() instanceof IC7431LogicMatrix);
    }

    @Test
    void testSimulateWithInput() {
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
        ic7431.simulate();
        ic7431.step();
        // then
        var outputPins = ic7431.getOutputPins();
        assertEquals(6, outputPins.size());
        assertEquals(2, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState());
        assertEquals(4, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState());
        assertEquals(7, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState());
        assertEquals(9, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());
        assertEquals(12, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());
        assertEquals(14, outputPins.get(5).getId());
        assertEquals(PinState.LOW, outputPins.get(5).getState());
    }
}
