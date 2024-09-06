package component.chip.or;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.or.IC7432Creator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.or.IC7432LogicMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IC7432Test {
    ChipCreator creator = new IC7432Creator();
    private ChipComponent ic7432;

    @BeforeEach
    void setUp() {
        ic7432 = creator.createChip();
    }

    @Test
    void testGetPin() {
        assertNotNull(ic7432.getPin(1));
        Assertions.assertEquals(PinState.UNKNOWN, ic7432.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7432.getLogicMatrix() instanceof IC7432LogicMatrix);
    }

    @Test
    void testSimulateWithInput() {
        // given
        ic7432.getPin(4).setState(PinState.LOW);
        ic7432.getPin(5).setState(PinState.LOW);
        ic7432.getPin(9).setState(PinState.HIGH);
        ic7432.getPin(10).setState(PinState.LOW);
        ic7432.getPin(12).setState(PinState.HIGH);
        ic7432.getPin(13).setState(PinState.HIGH);
        // when
        ic7432.simulate();
        ic7432.step();
        // then
        var outputPins = ic7432.getOutputPins();
        assertEquals(4, outputPins.size());
        assertEquals(3, outputPins.get(0).getId());
        assertEquals(PinState.UNKNOWN, outputPins.get(0).getState());
        assertEquals(6, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState());
        assertEquals(8, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());
        assertEquals(11, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());
    }
}
