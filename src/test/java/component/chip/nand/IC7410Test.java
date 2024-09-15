package component.chip.nand;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.nand.IC7410Creator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.nand.IC7410LogicMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IC7410Test {
    private final ChipCreator creator = new IC7410Creator();
    private ChipComponent ic7410;

    @BeforeEach
    void setUp() {
        ic7410 = creator.createChip();
    }

    @Test
    void testGetPin() {
        assertNotNull(ic7410.getPin(1));
        assertEquals(PinState.UNKNOWN, ic7410.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7410.getLogicMatrix() instanceof IC7410LogicMatrix);
    }

    @Test
    void testSimulateWithInput() {
        // given
        ic7410.getPin(3).setState(PinState.HIGH);
        ic7410.getPin(4).setState(PinState.HIGH);
        ic7410.getPin(5).setState(PinState.HIGH);

        ic7410.getPin(9).setState(PinState.LOW);
        ic7410.getPin(10).setState(PinState.HIGH);
        ic7410.getPin(11).setState(PinState.HIGH);

        ic7410.getPin(1).setState(PinState.HIGH);
        ic7410.getPin(2).setState(PinState.HIGH);
        ic7410.getPin(13).setState(PinState.HIGH);
        // when
        ic7410.simulate();
        ic7410.step();
        // then
        var outputPins = ic7410.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(6, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState());
        assertEquals(8, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());
        assertEquals(12, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState());
    }
}
