package component.chip.decoder;

import component.chip.decoder.IC7444Creator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import component.ChipComponent;
import component.chip.ChipCreator;
import component.pin.Pin;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.UnknownPin;
import logicmatrix.decoder.IC7444LogicMatrix;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IC7444Test {
    ChipCreator creator = new IC7444Creator();
    private ChipComponent ic7444;

    @BeforeEach
    void setUp() {
        ic7444 = creator.createChip();
    }

    @Test
    void testGetPin() throws UnknownPin {
        assertNotNull(ic7444.getPin(1));
        Assertions.assertEquals(PinState.UNKNOWN, ic7444.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7444.getLogicMatrix() instanceof IC7444LogicMatrix);
    }

    @Test
    void testSimulateWithInput_forZero() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        assertEquals(outputPins.get(0).getId(), 1);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.LOW); // 0
        assertEquals(outputPins.get(1).getId(), 2);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // 1
        assertEquals(outputPins.get(2).getId(), 3);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // 2
        assertEquals(outputPins.get(3).getId(), 4);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // 3
        assertEquals(outputPins.get(4).getId(), 5);
        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // 4
        assertEquals(outputPins.get(5).getId(), 6);
        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // 5
        assertEquals(outputPins.get(6).getId(), 7);
        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // 6
        assertEquals(outputPins.get(7).getId(), 9);
        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // 7
        assertEquals(outputPins.get(8).getId(), 10);
        Assertions.assertEquals(outputPins.get(8).getState(), PinState.HIGH); // 8
        assertEquals(outputPins.get(9).getId(), 11);
        Assertions.assertEquals(outputPins.get(9).getState(), PinState.HIGH); // 9
    }

    @Test
    void testSimulateWithInput_forOne() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        assertEquals(outputPins.get(0).getId(), 1);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // 0
        assertEquals(outputPins.get(1).getId(), 2);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.LOW); // 1
        assertEquals(outputPins.get(2).getId(), 3);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // 2
        assertEquals(outputPins.get(3).getId(), 4);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // 3
        assertEquals(outputPins.get(4).getId(), 5);
        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // 4
        assertEquals(outputPins.get(5).getId(), 6);
        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // 5
        assertEquals(outputPins.get(6).getId(), 7);
        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // 6
        assertEquals(outputPins.get(7).getId(), 9);
        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // 7
        assertEquals(outputPins.get(8).getId(), 10);
        Assertions.assertEquals(outputPins.get(8).getState(), PinState.HIGH); // 8
        assertEquals(outputPins.get(9).getId(), 11);
        Assertions.assertEquals(outputPins.get(9).getState(), PinState.HIGH); // 9
    }

    @Test
    void testSimulateWithInput_forTwo() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        assertEquals(outputPins.get(0).getId(), 1);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // 0
        assertEquals(outputPins.get(1).getId(), 2);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // 1
        assertEquals(outputPins.get(2).getId(), 3);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.LOW); // 2
        assertEquals(outputPins.get(3).getId(), 4);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // 3
        assertEquals(outputPins.get(4).getId(), 5);
        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // 4
        assertEquals(outputPins.get(5).getId(), 6);
        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // 5
        assertEquals(outputPins.get(6).getId(), 7);
        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // 6
        assertEquals(outputPins.get(7).getId(), 9);
        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // 7
        assertEquals(outputPins.get(8).getId(), 10);
        Assertions.assertEquals(outputPins.get(8).getState(), PinState.HIGH); // 8
        assertEquals(outputPins.get(9).getId(), 11);
        Assertions.assertEquals(outputPins.get(9).getState(), PinState.HIGH); // 9
    }

    @Test
    void testSimulateWithInput_forThree() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        assertEquals(outputPins.get(0).getId(), 1);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // 0
        assertEquals(outputPins.get(1).getId(), 2);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // 1
        assertEquals(outputPins.get(2).getId(), 3);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // 2
        assertEquals(outputPins.get(3).getId(), 4);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.LOW); // 3
        assertEquals(outputPins.get(4).getId(), 5);
        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // 4
        assertEquals(outputPins.get(5).getId(), 6);
        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // 5
        assertEquals(outputPins.get(6).getId(), 7);
        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // 6
        assertEquals(outputPins.get(7).getId(), 9);
        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // 7
        assertEquals(outputPins.get(8).getId(), 10);
        Assertions.assertEquals(outputPins.get(8).getState(), PinState.HIGH); // 8
        assertEquals(outputPins.get(9).getId(), 11);
        Assertions.assertEquals(outputPins.get(9).getState(), PinState.HIGH); // 9
    }

    @Test
    void testSimulateWithInput_forFour() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        assertEquals(outputPins.get(0).getId(), 1);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // 0
        assertEquals(outputPins.get(1).getId(), 2);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // 1
        assertEquals(outputPins.get(2).getId(), 3);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // 2
        assertEquals(outputPins.get(3).getId(), 4);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // 3
        assertEquals(outputPins.get(4).getId(), 5);
        Assertions.assertEquals(outputPins.get(4).getState(), PinState.LOW); // 4
        assertEquals(outputPins.get(5).getId(), 6);
        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // 5
        assertEquals(outputPins.get(6).getId(), 7);
        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // 6
        assertEquals(outputPins.get(7).getId(), 9);
        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // 7
        assertEquals(outputPins.get(8).getId(), 10);
        Assertions.assertEquals(outputPins.get(8).getState(), PinState.HIGH); // 8
        assertEquals(outputPins.get(9).getId(), 11);
        Assertions.assertEquals(outputPins.get(9).getState(), PinState.HIGH); // 9
    }

    @Test
    void testSimulateWithInput_forFive() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        assertEquals(outputPins.get(0).getId(), 1);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // 0
        assertEquals(outputPins.get(1).getId(), 2);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // 1
        assertEquals(outputPins.get(2).getId(), 3);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // 2
        assertEquals(outputPins.get(3).getId(), 4);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // 3
        assertEquals(outputPins.get(4).getId(), 5);
        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // 4
        assertEquals(outputPins.get(5).getId(), 6);
        Assertions.assertEquals(outputPins.get(5).getState(), PinState.LOW); // 5
        assertEquals(outputPins.get(6).getId(), 7);
        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // 6
        assertEquals(outputPins.get(7).getId(), 9);
        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // 7
        assertEquals(outputPins.get(8).getId(), 10);
        Assertions.assertEquals(outputPins.get(8).getState(), PinState.HIGH); // 8
        assertEquals(outputPins.get(9).getId(), 11);
        Assertions.assertEquals(outputPins.get(9).getState(), PinState.HIGH); // 9
    }

    @Test
    void testSimulateWithInput_forSix() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        assertEquals(outputPins.get(0).getId(), 1);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // 0
        assertEquals(outputPins.get(1).getId(), 2);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // 1
        assertEquals(outputPins.get(2).getId(), 3);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // 2
        assertEquals(outputPins.get(3).getId(), 4);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // 3
        assertEquals(outputPins.get(4).getId(), 5);
        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // 4
        assertEquals(outputPins.get(5).getId(), 6);
        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // 5
        assertEquals(outputPins.get(6).getId(), 7);
        Assertions.assertEquals(outputPins.get(6).getState(), PinState.LOW); // 6
        assertEquals(outputPins.get(7).getId(), 9);
        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // 7
        assertEquals(outputPins.get(8).getId(), 10);
        Assertions.assertEquals(outputPins.get(8).getState(), PinState.HIGH); // 8
        assertEquals(outputPins.get(9).getId(), 11);
        Assertions.assertEquals(outputPins.get(9).getState(), PinState.HIGH); // 9
    }

    @Test
    void testSimulateWithInput_forSeven() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        assertEquals(outputPins.get(0).getId(), 1);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // 0
        assertEquals(outputPins.get(1).getId(), 2);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // 1
        assertEquals(outputPins.get(2).getId(), 3);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // 2
        assertEquals(outputPins.get(3).getId(), 4);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // 3
        assertEquals(outputPins.get(4).getId(), 5);
        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // 4
        assertEquals(outputPins.get(5).getId(), 6);
        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // 5
        assertEquals(outputPins.get(6).getId(), 7);
        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // 6
        assertEquals(outputPins.get(7).getId(), 9);
        Assertions.assertEquals(outputPins.get(7).getState(), PinState.LOW); // 7
        assertEquals(outputPins.get(8).getId(), 10);
        Assertions.assertEquals(outputPins.get(8).getState(), PinState.HIGH); // 8
        assertEquals(outputPins.get(9).getId(), 11);
        Assertions.assertEquals(outputPins.get(9).getState(), PinState.HIGH); // 9
    }

    @Test
    void testSimulateWithInput_forEight() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        assertEquals(outputPins.get(0).getId(), 1);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // 0
        assertEquals(outputPins.get(1).getId(), 2);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // 1
        assertEquals(outputPins.get(2).getId(), 3);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // 2
        assertEquals(outputPins.get(3).getId(), 4);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // 3
        assertEquals(outputPins.get(4).getId(), 5);
        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // 4
        assertEquals(outputPins.get(5).getId(), 6);
        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // 5
        assertEquals(outputPins.get(6).getId(), 7);
        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // 6
        assertEquals(outputPins.get(7).getId(), 9);
        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // 7
        assertEquals(outputPins.get(8).getId(), 10);
        Assertions.assertEquals(outputPins.get(8).getState(), PinState.LOW); // 8
        assertEquals(outputPins.get(9).getId(), 11);
        Assertions.assertEquals(outputPins.get(9).getState(), PinState.HIGH); // 9
    }

    @Test
    void testSimulateWithInput_forNine() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        assertEquals(outputPins.get(0).getId(), 1);
        Assertions.assertEquals(outputPins.get(0).getState(), PinState.HIGH); // 0
        assertEquals(outputPins.get(1).getId(), 2);
        Assertions.assertEquals(outputPins.get(1).getState(), PinState.HIGH); // 1
        assertEquals(outputPins.get(2).getId(), 3);
        Assertions.assertEquals(outputPins.get(2).getState(), PinState.HIGH); // 2
        assertEquals(outputPins.get(3).getId(), 4);
        Assertions.assertEquals(outputPins.get(3).getState(), PinState.HIGH); // 3
        assertEquals(outputPins.get(4).getId(), 5);
        Assertions.assertEquals(outputPins.get(4).getState(), PinState.HIGH); // 4
        assertEquals(outputPins.get(5).getId(), 6);
        Assertions.assertEquals(outputPins.get(5).getState(), PinState.HIGH); // 5
        assertEquals(outputPins.get(6).getId(), 7);
        Assertions.assertEquals(outputPins.get(6).getState(), PinState.HIGH); // 6
        assertEquals(outputPins.get(7).getId(), 9);
        Assertions.assertEquals(outputPins.get(7).getState(), PinState.HIGH); // 7
        assertEquals(outputPins.get(8).getId(), 10);
        Assertions.assertEquals(outputPins.get(8).getState(), PinState.HIGH); // 8
        assertEquals(outputPins.get(9).getId(), 11);
        Assertions.assertEquals(outputPins.get(9).getState(), PinState.LOW); // 9
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whenACDHigh() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        for (Pin pin : outputPins) {
            Assertions.assertEquals(pin.getState(), PinState.HIGH);
        }
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whenADHigh() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        for (Pin pin : outputPins) {
            Assertions.assertEquals(pin.getState(), PinState.HIGH);
        }
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whenOnlyAHigh() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        for (Pin pin : outputPins) {
            Assertions.assertEquals(pin.getState(), PinState.HIGH);
        }
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whenAllInputsLow() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        for (Pin pin : outputPins) {
            Assertions.assertEquals(pin.getState(), PinState.HIGH);
        }
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whenOnlyDHigh() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        for (Pin pin : outputPins) {
            Assertions.assertEquals(pin.getState(), PinState.HIGH);
        }
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whenCDHigh() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        for (Pin pin : outputPins) {
            Assertions.assertEquals(pin.getState(), PinState.HIGH);
        }
    }

    @Test
    void testSimulateWithInput_forInvalidInput_unknownStates() throws UnknownPin {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.UNKNOWN); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        List<Pin> outputPins = ic7444.simulate();
        // then
        assertEquals(outputPins.size(), 10);
        for (Pin pin : outputPins) {
            Assertions.assertEquals(pin.getState(), PinState.HIGH);
        }
    }
}
