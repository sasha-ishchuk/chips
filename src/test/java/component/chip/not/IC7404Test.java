package component.chip.not;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.not.IC7404Creator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.not.IC7404LogicMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IC7404Test {
    private final ChipCreator creator = new IC7404Creator();
    private ChipComponent ic7404;

    @BeforeEach
    void setUp() {
        ic7404 = creator.createChip();
    }

    @Test
    void testGetPin() {
        assertNotNull(ic7404.getPin(1));
        assertEquals(PinState.UNKNOWN, ic7404.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7404.getLogicMatrix() instanceof IC7404LogicMatrix);
    }

    @Test
    void testSimulateWithInput() {
        // given
        ic7404.getPin(1).setState(PinState.LOW);
        ic7404.getPin(3).setState(PinState.LOW);
        ic7404.getPin(5).setState(PinState.HIGH);
        ic7404.getPin(9).setState(PinState.LOW);
        ic7404.getPin(11).setState(PinState.HIGH);
        // when
        ic7404.simulate();
        ic7404.step();
        // then
        var outputPins = ic7404.getOutputPins();
        assertEquals(6, outputPins.size());
        assertEquals(2, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
        assertEquals(4, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());
        assertEquals(6, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState());
        assertEquals(8, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());
        assertEquals(10, outputPins.get(4).getId());
        assertEquals(PinState.LOW, outputPins.get(4).getState());
        assertEquals(12, outputPins.get(5).getId());
        assertEquals(PinState.UNKNOWN, outputPins.get(5).getState());
    }
}
