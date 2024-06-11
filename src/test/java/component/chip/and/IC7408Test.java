package component.chip.and;

import component.chip.and.IC7408Creator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import component.ChipComponent;
import component.chip.ChipCreator;
import component.pin.Pin;
import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.and.IC7408LogicMatrix;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IC7408Test {
    ChipCreator creator = new IC7408Creator();
    private ChipComponent ic7408;

    @BeforeEach
    void setUp() {
        ic7408 = creator.createChip();
    }

    @Test
    void testGetPin()  {
        assertNotNull(ic7408.getPin(1));
        Assertions.assertEquals(PinState.UNKNOWN, ic7408.getPin(1).getState());
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
        List<Pin> outputPins = new ArrayList<>();
        ic7408.simulate();
        // then
        assertEquals(outputPins.size(), 4);
        assertEquals(outputPins.get(0).getId(), 3);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.LOW);
        assertEquals(outputPins.get(1).getId(), 6);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH);
        assertEquals(outputPins.get(2).getId(), 8);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.LOW);
        assertEquals(outputPins.get(3).getId(), 11);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.UNKNOWN);

        List<Pin> pins = ic7408.getPins();
        assertEquals(12, pins.size());

        ic7408.getPins();
    }
}
