package component.chip.nand;

import org.example.component.chip.nand.IC7400Creator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.pin.Pin;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.edu.uj.po.simulation.interfaces.UnknownPin;
import org.example.logicmatrix.nand.IC7400LogicMatrix;

import java.util.ArrayList;
import java.util.List;

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
    void testGetPin() throws UnknownPin {
        assertNotNull(ic7400.getPin(1));
        Assertions.assertEquals(PinState.UNKNOWN, ic7400.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7400.getLogicMatrix() instanceof IC7400LogicMatrix);
    }

    @Test
    void testSimulateWithInput() throws UnknownPin {
        // given
        ic7400.getPin(1).setState(PinState.HIGH);
        ic7400.getPin(2).setState(PinState.LOW);
        ic7400.getPin(9).setState(PinState.LOW);
        ic7400.getPin(10).setState(PinState.LOW);
        ic7400.getPin(12).setState(PinState.HIGH);
        ic7400.getPin(13).setState(PinState.HIGH);
        // when
        List<Pin> outputPins = new ArrayList<>();
        ic7400.simulate();
        // then
        assertEquals(outputPins.size(), 4);
        assertEquals(outputPins.get(0).getId(), 3);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH);
        assertEquals(outputPins.get(1).getId(), 6);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.UNKNOWN);
        assertEquals(outputPins.get(2).getId(), 8);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH);
        assertEquals(outputPins.get(3).getId(), 11);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.LOW);
    }
}
