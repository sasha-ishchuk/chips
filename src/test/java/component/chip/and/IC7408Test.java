package component.chip.and;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.and.IC7408Creator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.and.IC7408LogicMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IC7408Test {
    private final ChipCreator creator = new IC7408Creator();
    private ChipComponent ic7408;

    @BeforeEach
    void setUp() {
        ic7408 = creator.createChip();
    }

    @Test
    void testGetPin()  {
        assertNotNull(ic7408.getPin(1));
        assertEquals(PinState.UNKNOWN, ic7408.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7408.getLogicMatrix() instanceof IC7408LogicMatrix);
    }

    @Test
    void testSimulateWithInput() {
        // given
        ic7408.getPin(1).setState(PinState.HIGH);
        ic7408.getPin(2).setState(PinState.LOW);
        ic7408.getPin(4).setState(PinState.HIGH);
        ic7408.getPin(5).setState(PinState.HIGH);
        ic7408.getPin(9).setState(PinState.LOW);
        ic7408.getPin(10).setState(PinState.HIGH);
        // when
        ic7408.simulate();
        ic7408.step();
        // then
        var outputPins = ic7408.getOutputPins();
        assertEquals(4, outputPins.size());
        assertEquals(3, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState());
        assertEquals(6, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());
        assertEquals(8, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState());
        assertEquals(11, outputPins.get(3).getId());
        assertEquals(PinState.UNKNOWN, outputPins.get(3).getState());

        assertEquals(12, ic7408.getPins().size());
    }
}
