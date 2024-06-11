package component.chip.nand;

import component.chip.nand.IC7410Creator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import component.ChipComponent;
import component.chip.ChipCreator;
import component.pin.Pin;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.UnknownPin;
import logicmatrix.nand.IC7410LogicMatrix;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IC7410Test {
    ChipCreator creator = new IC7410Creator();
    private ChipComponent ic7410;

    @BeforeEach
    void setUp() {
        ic7410 = creator.createChip();
    }

    @Test
    void testGetPin() throws UnknownPin {
        assertNotNull(ic7410.getPin(1));
        Assertions.assertEquals(PinState.UNKNOWN, ic7410.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7410.getLogicMatrix() instanceof IC7410LogicMatrix);
    }

    @Test
    void testSimulateWithInput() throws UnknownPin {
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
        List<Pin> outputPins = new ArrayList<>();
        ic7410.simulate();
        // then
        assertEquals(outputPins.size(), 3);
        assertEquals(outputPins.get(0).getId(), 6);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.LOW);
        assertEquals(outputPins.get(1).getId(), 8);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH);
        assertEquals(outputPins.get(2).getId(), 12);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.LOW);
    }
}
