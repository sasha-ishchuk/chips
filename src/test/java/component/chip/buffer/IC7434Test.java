package component.chip.buffer;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.buffer.IC7434Creator;
import org.example.component.pin.Pin;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.buffer.IC7434LogicMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IC7434Test {
    private final ChipCreator creator = new IC7434Creator();
    private ChipComponent ic7434;

    @BeforeEach
    void setUp() {
        ic7434 = creator.createChip();
    }

    @Test
    void testGetPin() {
        assertNotNull(ic7434.getPin(1));
        assertEquals(PinState.UNKNOWN, ic7434.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7434.getLogicMatrix() instanceof IC7434LogicMatrix);
    }

    @Test
    void testSimulateWithInput() {
        // given
        ic7434.getPin(1).setState(PinState.LOW);
        ic7434.getPin(3).setState(PinState.LOW);
        ic7434.getPin(5).setState(PinState.HIGH);
        ic7434.getPin(9).setState(PinState.LOW);
        ic7434.getPin(13).setState(PinState.HIGH);
        // when
        ic7434.simulate();
        ic7434.step();
        // then
        List<Pin> outputPins = new ArrayList<>(ic7434.getOutputPins());
        assertEquals(6, outputPins.size());
        assertEquals(2, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState());
        assertEquals(4, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState());
        assertEquals(6, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());
        assertEquals(8, outputPins.get(3).getId());
        assertEquals(PinState.LOW, outputPins.get(3).getState());
        assertEquals(10, outputPins.get(4).getId());
        assertEquals(PinState.UNKNOWN, outputPins.get(4).getState());
        assertEquals(12, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());
    }
}
