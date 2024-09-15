package component.chip.multiplexer;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.multiplexer.IC74152Creator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.multiplexer.IC74152LogicMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IC74152Test {
    private final ChipCreator creator = new IC74152Creator();
    private ChipComponent ic74152;

    @BeforeEach
    void setUp() {
        ic74152 = creator.createChip();
    }

    @Test
    void testGetPin() {
        assertNotNull(ic74152.getPin(1));
        assertEquals(PinState.UNKNOWN, ic74152.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic74152.getLogicMatrix() instanceof IC74152LogicMatrix);
    }

    @Test
    void testSimulateWithInput_returnNotD0() {
        // given
        ic74152.getPin(10).setState(PinState.LOW); // A
        ic74152.getPin(9).setState(PinState.LOW); // B
        ic74152.getPin(8).setState(PinState.LOW); // C

        ic74152.getPin(5).setState(PinState.LOW); // D0
        ic74152.getPin(4).setState(PinState.LOW); // D1
        ic74152.getPin(3).setState(PinState.HIGH); // D2
        ic74152.getPin(2).setState(PinState.LOW); // D3
        ic74152.getPin(1).setState(PinState.LOW); // D4
        ic74152.getPin(13).setState(PinState.HIGH); // D5
        ic74152.getPin(12).setState(PinState.HIGH); // D6
        ic74152.getPin(11).setState(PinState.LOW); // D7

        // when
        ic74152.simulate();
        ic74152.step();
        // then
        var outputPins = ic74152.getOutputPins();
        assertEquals(1, outputPins.size());
        assertEquals(6, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
    }

    @Test
    void testSimulateWithInput_returnNotD1() {
        // given
        ic74152.getPin(10).setState(PinState.HIGH); // A
        ic74152.getPin(9).setState(PinState.LOW); // B
        ic74152.getPin(8).setState(PinState.LOW); // C

        ic74152.getPin(5).setState(PinState.LOW); // D0
        ic74152.getPin(4).setState(PinState.LOW); // D1
        ic74152.getPin(3).setState(PinState.HIGH); // D2
        ic74152.getPin(2).setState(PinState.LOW); // D3
        ic74152.getPin(1).setState(PinState.LOW); // D4
        ic74152.getPin(13).setState(PinState.HIGH); // D5
        ic74152.getPin(12).setState(PinState.HIGH); // D6
        ic74152.getPin(11).setState(PinState.LOW); // D7

        // when
        ic74152.simulate();
        ic74152.step();
        // then
        var outputPins = ic74152.getOutputPins();
        assertEquals(1, outputPins.size());
        assertEquals(6, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
    }

    @Test
    void testSimulateWithInput_returnNotD2() {
        // given
        ic74152.getPin(10).setState(PinState.LOW); // A
        ic74152.getPin(9).setState(PinState.HIGH); // B
        ic74152.getPin(8).setState(PinState.LOW); // C

        ic74152.getPin(5).setState(PinState.LOW); // D0
        ic74152.getPin(4).setState(PinState.LOW); // D1
        ic74152.getPin(3).setState(PinState.HIGH); // D2
        ic74152.getPin(2).setState(PinState.LOW); // D3
        ic74152.getPin(1).setState(PinState.LOW); // D4
        ic74152.getPin(13).setState(PinState.HIGH); // D5
        ic74152.getPin(12).setState(PinState.HIGH); // D6
        ic74152.getPin(11).setState(PinState.LOW); // D7

        // when
        ic74152.simulate();
        ic74152.step();
        // then
        var outputPins = ic74152.getOutputPins();
        assertEquals(1, outputPins.size());
        assertEquals(6, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState());
    }

    @Test
    void testSimulateWithInput_returnNotD3() {
        // given
        ic74152.getPin(10).setState(PinState.HIGH); // A
        ic74152.getPin(9).setState(PinState.HIGH); // B
        ic74152.getPin(8).setState(PinState.LOW); // C

        ic74152.getPin(5).setState(PinState.LOW); // D0
        ic74152.getPin(4).setState(PinState.LOW); // D1
        ic74152.getPin(3).setState(PinState.HIGH); // D2
        ic74152.getPin(2).setState(PinState.LOW); // D3
        ic74152.getPin(1).setState(PinState.LOW); // D4
        ic74152.getPin(13).setState(PinState.HIGH); // D5
        ic74152.getPin(12).setState(PinState.HIGH); // D6
        ic74152.getPin(11).setState(PinState.LOW); // D7

        // when
        ic74152.simulate();
        ic74152.step();
        // then
        var outputPins = ic74152.getOutputPins();
        assertEquals(1, outputPins.size());
        assertEquals(6, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
    }

    @Test
    void testSimulateWithInput_returnNotD4() {
        // given
        ic74152.getPin(10).setState(PinState.LOW); // A
        ic74152.getPin(9).setState(PinState.LOW); // B
        ic74152.getPin(8).setState(PinState.HIGH); // C

        ic74152.getPin(5).setState(PinState.LOW); // D0
        ic74152.getPin(4).setState(PinState.LOW); // D1
        ic74152.getPin(3).setState(PinState.HIGH); // D2
        ic74152.getPin(2).setState(PinState.LOW); // D3
        ic74152.getPin(1).setState(PinState.LOW); // D4
        ic74152.getPin(13).setState(PinState.HIGH); // D5
        ic74152.getPin(12).setState(PinState.HIGH); // D6
        ic74152.getPin(11).setState(PinState.LOW); // D7

        // when
        ic74152.simulate();
        ic74152.step();
        // then
        var outputPins = ic74152.getOutputPins();
        assertEquals(1, outputPins.size());
        assertEquals(6, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
    }

    @Test
    void testSimulateWithInput_returnNotD5() {
        // given
        ic74152.getPin(10).setState(PinState.HIGH); // A
        ic74152.getPin(9).setState(PinState.LOW); // B
        ic74152.getPin(8).setState(PinState.HIGH); // C

        ic74152.getPin(5).setState(PinState.LOW); // D0
        ic74152.getPin(4).setState(PinState.LOW); // D1
        ic74152.getPin(3).setState(PinState.HIGH); // D2
        ic74152.getPin(2).setState(PinState.LOW); // D3
        ic74152.getPin(1).setState(PinState.LOW); // D4
        ic74152.getPin(13).setState(PinState.HIGH); // D5
        ic74152.getPin(12).setState(PinState.HIGH); // D6
        ic74152.getPin(11).setState(PinState.LOW); // D7

        // when
        ic74152.simulate();
        ic74152.step();
        // then
        var outputPins = ic74152.getOutputPins();
        assertEquals(1, outputPins.size());
        assertEquals(6, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState());
    }

    @Test
    void testSimulateWithInput_returnNotD6() {
        // given
        ic74152.getPin(10).setState(PinState.LOW); // A
        ic74152.getPin(9).setState(PinState.HIGH); // B
        ic74152.getPin(8).setState(PinState.HIGH); // C

        ic74152.getPin(5).setState(PinState.LOW); // D0
        ic74152.getPin(4).setState(PinState.LOW); // D1
        ic74152.getPin(3).setState(PinState.HIGH); // D2
        ic74152.getPin(2).setState(PinState.LOW); // D3
        ic74152.getPin(1).setState(PinState.LOW); // D4
        ic74152.getPin(13).setState(PinState.HIGH); // D5
        ic74152.getPin(12).setState(PinState.HIGH); // D6
        ic74152.getPin(11).setState(PinState.LOW); // D7

        // when
        ic74152.simulate();
        ic74152.step();
        // then
        var outputPins = ic74152.getOutputPins();
        assertEquals(1, outputPins.size());
        assertEquals(6, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState());
    }

    @Test
    void testSimulateWithInput_returnNotD7() {
        // given
        ic74152.getPin(10).setState(PinState.HIGH); // A
        ic74152.getPin(9).setState(PinState.HIGH); // B
        ic74152.getPin(8).setState(PinState.HIGH); // C

        ic74152.getPin(5).setState(PinState.LOW); // D0
        ic74152.getPin(4).setState(PinState.LOW); // D1
        ic74152.getPin(3).setState(PinState.HIGH); // D2
        ic74152.getPin(2).setState(PinState.LOW); // D3
        ic74152.getPin(1).setState(PinState.LOW); // D4
        ic74152.getPin(13).setState(PinState.HIGH); // D5
        ic74152.getPin(12).setState(PinState.HIGH); // D6
        ic74152.getPin(11).setState(PinState.LOW); // D7

        // when
        ic74152.simulate();
        ic74152.step();
        // then
        var outputPins = ic74152.getOutputPins();
        assertEquals(1, outputPins.size());
        assertEquals(6, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
    }

    @Test
    void testSimulateWithInput_whenOneOfABCUnknown_returnStateUnknown() {
        // given
        ic74152.getPin(10).setState(PinState.LOW); // A
        ic74152.getPin(9).setState(PinState.UNKNOWN); // B
        ic74152.getPin(8).setState(PinState.LOW); // C

        ic74152.getPin(5).setState(PinState.LOW); // D0
        ic74152.getPin(4).setState(PinState.LOW); // D1
        ic74152.getPin(3).setState(PinState.HIGH); // D2
        ic74152.getPin(2).setState(PinState.LOW); // D3
        ic74152.getPin(1).setState(PinState.LOW); // D4
        ic74152.getPin(13).setState(PinState.HIGH); // D5
        ic74152.getPin(12).setState(PinState.HIGH); // D6
        ic74152.getPin(11).setState(PinState.LOW); // D7

        // when
        ic74152.simulate();
        ic74152.step();
        // then
        var outputPins = ic74152.getOutputPins();
        assertEquals(1, outputPins.size());
        assertEquals(6, outputPins.get(0).getId());
        assertEquals(PinState.UNKNOWN, outputPins.get(0).getState());
    }

    @Test
    void testSimulateWithInput_whenOneOfDInputsUnknown_returnStateUnknown() {
        // given
        ic74152.getPin(10).setState(PinState.LOW); // A
        ic74152.getPin(9).setState(PinState.HIGH); // B
        ic74152.getPin(8).setState(PinState.LOW); // C

        ic74152.getPin(5).setState(PinState.LOW); // D0
        ic74152.getPin(4).setState(PinState.LOW); // D1
        ic74152.getPin(3).setState(PinState.UNKNOWN); // D2
        ic74152.getPin(2).setState(PinState.LOW); // D3
        ic74152.getPin(1).setState(PinState.LOW); // D4
        ic74152.getPin(13).setState(PinState.HIGH); // D5
        ic74152.getPin(12).setState(PinState.HIGH); // D6
        ic74152.getPin(11).setState(PinState.LOW); // D7

        // when
        ic74152.simulate();
        ic74152.step();
        // then
        var outputPins = ic74152.getOutputPins();
        assertEquals(1, outputPins.size());
        assertEquals(6, outputPins.get(0).getId());
        assertEquals(PinState.UNKNOWN, outputPins.get(0).getState());
    }

    @Test
    void testSimulateWithInput_whenAllInputsUnknown_returnStateUnknown() {
        // given/when
        ic74152.simulate();
        ic74152.step();
        // then
        var outputPins = ic74152.getOutputPins();
        assertEquals(1, outputPins.size());
        assertEquals(6, outputPins.get(0).getId());
        assertEquals(PinState.UNKNOWN, outputPins.get(0).getState());
    }
}
