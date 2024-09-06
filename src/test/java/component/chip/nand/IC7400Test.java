package component.chip.nand;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.nand.IC7400Creator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.nand.IC7400LogicMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IC7400Test {
    ChipCreator creator = new IC7400Creator();
    private ChipComponent ic7400;

    @BeforeEach
    void setUp() {
        ic7400 = creator.createChip();
    }

    @Test
    void testGetPin() {
        assertNotNull(ic7400.getPin(1));
        assertEquals(PinState.UNKNOWN, ic7400.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7400.getLogicMatrix() instanceof IC7400LogicMatrix);
    }

    @Test
    void testSimulateWithInput() {
        // given
        ic7400.getPin(1).setState(PinState.HIGH);
        ic7400.getPin(2).setState(PinState.LOW);
        ic7400.getPin(9).setState(PinState.LOW);
        ic7400.getPin(10).setState(PinState.LOW);
        ic7400.getPin(12).setState(PinState.HIGH);
        ic7400.getPin(13).setState(PinState.HIGH);
        // when
        ic7400.simulate();
        ic7400.step();
        // then
        var outputPins = ic7400.getOutputPins();
        assertEquals(4, outputPins.size());
        assertEquals(3, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
        assertEquals(6, outputPins.get(1).getId());
        assertEquals(PinState.UNKNOWN, outputPins.get(1).getState());
        assertEquals(8, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());
        assertEquals(11, outputPins.get(3).getId());
        assertEquals(PinState.LOW, outputPins.get(3).getState());
    }
}
