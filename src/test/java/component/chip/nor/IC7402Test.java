package component.chip.nor;

import component.chip.nor.IC7402Creator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import component.ChipComponent;
import component.chip.ChipCreator;
import component.pin.Pin;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.UnknownPin;
import logicmatrix.nor.IC7402LogicMatrix;

import java.util.List;

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
    void testGetPin() throws UnknownPin {
        assertNotNull(ic7402.getPin(1));
        Assertions.assertEquals(PinState.UNKNOWN, ic7402.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7402.getLogicMatrix() instanceof IC7402LogicMatrix);
    }

    @Test
    void testSimulateWithInput() throws UnknownPin {
        // given
        ic7402.getPin(2).setState(PinState.LOW);
        ic7402.getPin(3).setState(PinState.LOW);
        ic7402.getPin(5).setState(PinState.LOW);
        ic7402.getPin(6).setState(PinState.LOW);
        ic7402.getPin(11).setState(PinState.HIGH);
        ic7402.getPin(12).setState(PinState.LOW);
        // when
        List<Pin> outputPins = ic7402.simulate();
        // then
        assertEquals(outputPins.size(), 4);
        assertEquals(outputPins.get(0).getId(), 1);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH);
        assertEquals(outputPins.get(1).getId(), 4);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH);
        assertEquals(outputPins.get(2).getId(), 10);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.UNKNOWN);
        assertEquals(outputPins.get(3).getId(), 13);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.LOW);
    }
}
