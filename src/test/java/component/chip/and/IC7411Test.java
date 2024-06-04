package component.chip.and;

import component.chip.and.IC7411Creator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import component.ChipComponent;
import component.chip.ChipCreator;
import component.pin.Pin;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.UnknownPin;
import logicmatrix.and.IC7411LogicMatrix;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IC7411Test {
    ChipCreator creator = new IC7411Creator();
    private ChipComponent ic7411;

    @BeforeEach
    void setUp() {
        ic7411 = creator.createChip();
    }

    @Test
    void testGetPin() throws UnknownPin {
        assertNotNull(ic7411.getPin(1));
        Assertions.assertEquals(PinState.UNKNOWN, ic7411.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7411.getLogicMatrix() instanceof IC7411LogicMatrix);
    }

    @Test
    void testSimulateWithInput() throws UnknownPin {
        // given
        ic7411.getPin(1).setState(PinState.HIGH);
        ic7411.getPin(2).setState(PinState.HIGH);
        ic7411.getPin(13).setState(PinState.HIGH);

        ic7411.getPin(3).setState(PinState.LOW);
        ic7411.getPin(4).setState(PinState.LOW);
        ic7411.getPin(5).setState(PinState.HIGH);

        ic7411.getPin(9).setState(PinState.LOW);
        ic7411.getPin(10).setState(PinState.HIGH);
        ic7411.getPin(11).setState(PinState.HIGH);
        // when
        List<Pin> outputPins = ic7411.simulate();
        // then
        assertEquals(outputPins.size(), 3);
        assertEquals(outputPins.get(0).getId(), 6);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.LOW);
        assertEquals(outputPins.get(1).getId(), 8);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.LOW);
        assertEquals(outputPins.get(2).getId(), 12);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH);
    }
}
