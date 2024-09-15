package component.chip.nand;


import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.nand.IC7420Creator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.nand.IC7420LogicMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IC7420Test {
    private final ChipCreator creator = new IC7420Creator();
    private ChipComponent ic7420;

    @BeforeEach
    void setUp() {
        ic7420 = creator.createChip();
    }

    @Test
    void testGetPin(){
        assertNotNull(ic7420.getPin(1));
        assertEquals(PinState.UNKNOWN, ic7420.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7420.getLogicMatrix() instanceof IC7420LogicMatrix);
    }

    @Test
    void testSimulateWithInput() {
        // given
        ic7420.getPin(1).setState(PinState.HIGH);
        ic7420.getPin(2).setState(PinState.LOW);
        ic7420.getPin(4).setState(PinState.HIGH);
        ic7420.getPin(5).setState(PinState.LOW);

        ic7420.getPin(9).setState(PinState.HIGH);
        ic7420.getPin(10).setState(PinState.HIGH);
        ic7420.getPin(12).setState(PinState.HIGH);
        ic7420.getPin(13).setState(PinState.HIGH);
        // when
        ic7420.simulate();
        ic7420.step();
        // then
        var outputPins = ic7420.getOutputPins();
        assertEquals(2, outputPins.size());
        assertEquals(6, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
        assertEquals(8, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState());
    }
}
