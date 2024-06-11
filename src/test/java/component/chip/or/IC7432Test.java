package component.chip.or;

import component.chip.or.IC7432Creator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import component.ChipComponent;
import component.chip.ChipCreator;
import component.pin.Pin;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.UnknownPin;
import logicmatrix.or.IC7432LogicMatrix;

import java.util.ArrayList;
import java.util.List;

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
    void testGetPin() throws UnknownPin {
        assertNotNull(ic7432.getPin(1));
        Assertions.assertEquals(PinState.UNKNOWN, ic7432.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7432.getLogicMatrix() instanceof IC7432LogicMatrix);
    }

    @Test
    void testSimulateWithInput() throws UnknownPin {
        // given
        ic7432.getPin(4).setState(PinState.LOW);
        ic7432.getPin(5).setState(PinState.LOW);
        ic7432.getPin(9).setState(PinState.HIGH);
        ic7432.getPin(10).setState(PinState.LOW);
        ic7432.getPin(12).setState(PinState.HIGH);
        ic7432.getPin(13).setState(PinState.HIGH);
        // when
        List<Pin> outputPins = new ArrayList<>();
        ic7432.simulate();
        // then
        assertEquals(outputPins.size(), 4);
        assertEquals(outputPins.get(0).getId(), 3);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.UNKNOWN);
        assertEquals(outputPins.get(1).getId(), 6);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.LOW);
        assertEquals(outputPins.get(2).getId(), 8);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH);
        assertEquals(outputPins.get(3).getId(), 11);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH);
    }
}
