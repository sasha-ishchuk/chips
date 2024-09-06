package component.chip.nor;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.nor.IC7402Creator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.nor.IC7402LogicMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IC7402Test {
    ChipCreator creator = new IC7402Creator();
    private ChipComponent ic7402;

    @BeforeEach
    void setUp() {
        ic7402 = creator.createChip();
    }

    @Test
    void testGetPin() {
        assertNotNull(ic7402.getPin(1));
        assertEquals(PinState.UNKNOWN, ic7402.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7402.getLogicMatrix() instanceof IC7402LogicMatrix);
    }

    @Test
    void testSimulateWithInput() {
        // given
        ic7402.getPin(2).setState(PinState.LOW);
        ic7402.getPin(3).setState(PinState.LOW);
        ic7402.getPin(5).setState(PinState.LOW);
        ic7402.getPin(6).setState(PinState.LOW);
        ic7402.getPin(11).setState(PinState.HIGH);
        ic7402.getPin(12).setState(PinState.LOW);
        // when
        ic7402.simulate();
        ic7402.step();
        // then
        var outputPins = ic7402.getOutputPins();
        assertEquals(4, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
        assertEquals(4, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.UNKNOWN, outputPins.get(2).getState());
        assertEquals(13, outputPins.get(3).getId());
        assertEquals(PinState.LOW, outputPins.get(3).getState());
    }
}
