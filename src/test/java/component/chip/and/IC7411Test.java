package component.chip.and;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.and.IC7411Creator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.and.IC7411LogicMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IC7411Test {
    private final ChipCreator creator = new IC7411Creator();
    private ChipComponent ic7411;

    @BeforeEach
    void setUp() {
        ic7411 = creator.createChip();
    }

    @Test
    void testGetPin() {
        assertNotNull(ic7411.getPin(1));
        assertEquals(PinState.UNKNOWN, ic7411.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7411.getLogicMatrix() instanceof IC7411LogicMatrix);
    }

    @Test
    void testSimulateWithInput() {
        // given
        ic7411.getPin(1).setState(PinState.HIGH);
        ic7411.getPin(2).setState(PinState.HIGH);
        ic7411.getPin(13).setState(PinState.HIGH);

        ic7411.getPin(3).setState(PinState.LOW);
        ic7411.getPin(4).setState(PinState.LOW);
        ic7411.getPin(5).setState(PinState.HIGH);

        ic7411.getPin(9).setState(PinState.LOW);
        ic7411.getPin(10).setState(PinState.HIGH);
        ic7411.getPin(11).setState(PinState.HIGH);
        // when
        ic7411.simulate();
        ic7411.step();
        // then
        var outputPins = ic7411.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(6, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState());
        assertEquals(8, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState());
        assertEquals(12, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());
    }
}
