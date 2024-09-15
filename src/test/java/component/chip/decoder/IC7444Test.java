package component.chip.decoder;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.decoder.IC7444Creator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.decoder.IC7444LogicMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IC7444Test {
    private final ChipCreator creator = new IC7444Creator();
    private ChipComponent ic7444;

    @BeforeEach
    void setUp() {
        ic7444 = creator.createChip();
    }

    @Test
    void testGetPin() {
        assertNotNull(ic7444.getPin(1));
        assertEquals(PinState.UNKNOWN, ic7444.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7444.getLogicMatrix() instanceof IC7444LogicMatrix);
    }

    @Test
    void testSimulateWithInput_forZero() {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState());   // 0
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // 1
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // 2
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // 3
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // 4
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // 5
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // 6
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // 7
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());  // 8
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());  // 9
    }

    @Test
    void testSimulateWithInput_forOne() {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // 0
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState());  // 1
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // 2
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // 3
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // 4
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // 5
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // 6
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // 7
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());  // 8
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());  // 9
    }

    @Test
    void testSimulateWithInput_forTwo() {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // 0
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // 1
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState());  // 2
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // 3
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // 4
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // 5
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // 6
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // 7
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());  // 8
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());  // 9
    }

    @Test
    void testSimulateWithInput_forThree() {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // 0
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // 1
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // 2
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.LOW, outputPins.get(3).getState());  // 3
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // 4
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // 5
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // 6
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // 7
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());  // 8
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());  // 9
    }

    @Test
    void testSimulateWithInput_forFour() {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // 0
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // 1
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // 2
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // 3
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.LOW, outputPins.get(4).getState());  // 4
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // 5
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // 6
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // 7
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());  // 8
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());  // 9
    }

    @Test
    void testSimulateWithInput_forFive() {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // 0
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // 1
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // 2
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // 3
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // 4
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.LOW, outputPins.get(5).getState());  // 5
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // 6
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // 7
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());  // 8
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());  // 9
    }

    @Test
    void testSimulateWithInput_forSix() {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // 0
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // 1
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // 2
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // 3
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // 4
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // 5
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.LOW, outputPins.get(6).getState());  // 6
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // 7
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());  // 8
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());  // 9
    }

    @Test
    void testSimulateWithInput_forSeven() {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // 0
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // 1
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // 2
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // 3
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // 4
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // 5
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // 6
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.LOW, outputPins.get(7).getState());  // 7
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());  // 8
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());  // 9
    }

    @Test
    void testSimulateWithInput_forEight() {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.HIGH); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // 0
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // 1
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // 2
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // 3
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // 4
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // 5
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // 6
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // 7
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.LOW, outputPins.get(8).getState());  // 8
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());  // 9
    }

    @Test
    void testSimulateWithInput_forNine() {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // 0
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // 1
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // 2
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // 3
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // 4
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // 5
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // 6
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // 7
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());  // 8
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.LOW, outputPins.get(9).getState());  // 9
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whenACDHigh() {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertThat(outputPins).hasSize(10);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whenADHigh() {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertThat(outputPins).hasSize(10);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whenOnlyAHigh() {
        // given
        ic7444.getPin(15).setState(PinState.HIGH); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertThat(outputPins).hasSize(10);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whenAllInputsLow() {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.LOW); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertThat(outputPins).hasSize(10);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whenOnlyDHigh() {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertThat(outputPins).hasSize(10);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whenCDHigh() {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.LOW); // B
        ic7444.getPin(13).setState(PinState.HIGH); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertThat(outputPins).hasSize(10);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }

    @Test
    void testSimulateWithInput_forInvalidInput_unknownStates() {
        // given
        ic7444.getPin(15).setState(PinState.LOW); // A
        ic7444.getPin(14).setState(PinState.UNKNOWN); // B
        ic7444.getPin(13).setState(PinState.LOW); // C
        ic7444.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7444.simulate();
        ic7444.step();
        // then
        var outputPins = ic7444.getOutputPins();
        assertThat(outputPins).hasSize(10);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }
}
