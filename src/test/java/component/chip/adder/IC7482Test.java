package component.chip.adder;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.adder.IC7482Creator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.adder.IC7482LogicMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IC7482Test {
    private final ChipCreator creator = new IC7482Creator();
    private ChipComponent ic7482;

    @BeforeEach
    void setUp() {
        ic7482 = creator.createChip();
    }

    @Test
    void testGetPin() {
        assertNotNull(ic7482.getPin(1));
        assertEquals(ic7482.getPin(1).getState(), PinState.UNKNOWN);
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7482.getLogicMatrix() instanceof IC7482LogicMatrix);
    }

    @Test
    void testSimulateWithInput_forLowC_case1() {
        // given
        ic7482.getPin(5).setState(PinState.LOW); // C0

        ic7482.getPin(2).setState(PinState.LOW); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.LOW); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState()); // C2

        assertEquals(PinState.LOW, ic7482.getPin(1).getState());
        assertEquals(PinState.LOW, ic7482.getPin(12).getState());
        assertEquals(PinState.LOW, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forLowC_case2() {
        // given
        ic7482.getPin(5).setState(PinState.LOW); // C0

        ic7482.getPin(2).setState(PinState.HIGH); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.LOW); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState()); // C2

        assertEquals(PinState.HIGH, ic7482.getPin(1).getState());
        assertEquals(PinState.LOW, ic7482.getPin(12).getState());
        assertEquals(PinState.LOW, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forLowC_case3() {
        // given
        ic7482.getPin(5).setState(PinState.LOW); // C0

        ic7482.getPin(2).setState(PinState.LOW); // A1
        ic7482.getPin(3).setState(PinState.HIGH); // B1
        ic7482.getPin(14).setState(PinState.LOW); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState()); // C2

        assertEquals(PinState.HIGH, ic7482.getPin(1).getState());
        assertEquals(PinState.LOW, ic7482.getPin(12).getState());
        assertEquals(PinState.LOW, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forLowC_case4() {
        // given
        ic7482.getPin(5).setState(PinState.LOW); // C0

        ic7482.getPin(2).setState(PinState.HIGH); // A1
        ic7482.getPin(3).setState(PinState.HIGH); // B1
        ic7482.getPin(14).setState(PinState.LOW); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(outputPins.size(), 3);
        assertEquals(outputPins.get(0).getId(), 1);
        assertEquals(outputPins.get(0).getState(), PinState.LOW); // E1
        assertEquals(outputPins.get(1).getId(), 12);
        assertEquals(outputPins.get(1).getState(), PinState.HIGH); // E2
        assertEquals(outputPins.get(2).getId(), 10);
        assertEquals(outputPins.get(2).getState(), PinState.LOW); // C2

        assertEquals(PinState.LOW, ic7482.getPin(1).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(12).getState());
        assertEquals(PinState.LOW, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forLowC_case5() {
        // given
        ic7482.getPin(5).setState(PinState.LOW); // C0

        ic7482.getPin(2).setState(PinState.LOW); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState()); // C2

        assertEquals(PinState.LOW, ic7482.getPin(1).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(12).getState());
        assertEquals(PinState.LOW, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forLowC_case6() {
        // given
        ic7482.getPin(5).setState(PinState.LOW); // C0

        ic7482.getPin(2).setState(PinState.HIGH); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState()); // C2

        assertEquals(PinState.HIGH, ic7482.getPin(1).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(12).getState());
        assertEquals(PinState.LOW, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forLowC_case7() {
        // given
        ic7482.getPin(5).setState(PinState.LOW); // C0

        ic7482.getPin(2).setState(PinState.LOW); // A1
        ic7482.getPin(3).setState(PinState.HIGH); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState()); // C2

        assertEquals(PinState.HIGH, ic7482.getPin(1).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(12).getState());
        assertEquals(PinState.LOW, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forLowC_case8() {
        // given
        ic7482.getPin(5).setState(PinState.LOW); // C0

        ic7482.getPin(2).setState(PinState.HIGH); // A1
        ic7482.getPin(3).setState(PinState.HIGH); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState()); // C2

        assertEquals(PinState.LOW, ic7482.getPin(1).getState());
        assertEquals(PinState.LOW, ic7482.getPin(12).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forLowC_case9() {
        // given
        ic7482.getPin(5).setState(PinState.LOW); // C0

        ic7482.getPin(2).setState(PinState.LOW); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.HIGH); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState()); // C2

        assertEquals(PinState.LOW, ic7482.getPin(1).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(12).getState());
        assertEquals(PinState.LOW, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forLowC_case10() {
        // given
        ic7482.getPin(5).setState(PinState.LOW); // C0

        ic7482.getPin(2).setState(PinState.HIGH); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.HIGH); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState()); // C2

        assertEquals(PinState.HIGH, ic7482.getPin(1).getState());
        assertEquals(PinState.LOW, ic7482.getPin(12).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forLowC_case11() {
        // given
        ic7482.getPin(5).setState(PinState.LOW); // C0

        ic7482.getPin(2).setState(PinState.LOW); // A1
        ic7482.getPin(3).setState(PinState.HIGH); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.HIGH); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState()); // C2

        assertEquals(PinState.HIGH, ic7482.getPin(1).getState());
        assertEquals(PinState.LOW, ic7482.getPin(12).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forLowC_case12() {
        // given
        ic7482.getPin(5).setState(PinState.LOW); // C0

        ic7482.getPin(2).setState(PinState.HIGH); // A1
        ic7482.getPin(3).setState(PinState.HIGH); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.HIGH); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState()); // C2

        assertEquals(PinState.LOW, ic7482.getPin(1).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(12).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forHighC_case1() {
        // given
        ic7482.getPin(5).setState(PinState.HIGH); // C0

        ic7482.getPin(2).setState(PinState.LOW); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.LOW); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState()); // C2

        assertEquals(PinState.HIGH, ic7482.getPin(1).getState());
        assertEquals(PinState.LOW, ic7482.getPin(12).getState());
        assertEquals(PinState.LOW, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forHighC_case2() {
        // given
        ic7482.getPin(5).setState(PinState.HIGH); // C0

        ic7482.getPin(2).setState(PinState.HIGH); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.LOW); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState()); // C2

        assertEquals(PinState.LOW, ic7482.getPin(1).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(12).getState());
        assertEquals(PinState.LOW, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forHighC_case3() {
        // given
        ic7482.getPin(5).setState(PinState.HIGH); // C0

        ic7482.getPin(2).setState(PinState.LOW); // A1
        ic7482.getPin(3).setState(PinState.HIGH); // B1
        ic7482.getPin(14).setState(PinState.LOW); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState()); // C2

        assertEquals(PinState.LOW, ic7482.getPin(1).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(12).getState());
        assertEquals(PinState.LOW, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forHighC_case4() {
        // given
        ic7482.getPin(5).setState(PinState.HIGH); // C0

        ic7482.getPin(2).setState(PinState.HIGH); // A1
        ic7482.getPin(3).setState(PinState.HIGH); // B1
        ic7482.getPin(14).setState(PinState.LOW); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState()); // C2

        assertEquals(PinState.HIGH, ic7482.getPin(1).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(12).getState());
        assertEquals(PinState.LOW, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forHighC_case5() {
        // given
        ic7482.getPin(5).setState(PinState.HIGH); // C0

        ic7482.getPin(2).setState(PinState.LOW); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState()); // C2

        assertEquals(PinState.HIGH, ic7482.getPin(1).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(12).getState());
        assertEquals(PinState.LOW, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forHighC_case6() {
        // given
        ic7482.getPin(5).setState(PinState.HIGH); // C0

        ic7482.getPin(2).setState(PinState.HIGH); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState()); // C2

        assertEquals(PinState.LOW, ic7482.getPin(1).getState());
        assertEquals(PinState.LOW, ic7482.getPin(12).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forHighC_case7() {
        // given
        ic7482.getPin(5).setState(PinState.HIGH); // C0

        ic7482.getPin(2).setState(PinState.LOW); // A1
        ic7482.getPin(3).setState(PinState.HIGH); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState()); // C2

        assertEquals(PinState.LOW, ic7482.getPin(1).getState());
        assertEquals(PinState.LOW, ic7482.getPin(12).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forHighC_case8() {
        // given
        ic7482.getPin(5).setState(PinState.HIGH); // C0

        ic7482.getPin(2).setState(PinState.HIGH); // A1
        ic7482.getPin(3).setState(PinState.HIGH); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState()); // C2

        assertEquals(PinState.HIGH, ic7482.getPin(1).getState());
        assertEquals(PinState.LOW, ic7482.getPin(12).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forHighC_case9() {
        // given
        ic7482.getPin(5).setState(PinState.HIGH); // C0

        ic7482.getPin(2).setState(PinState.LOW); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.HIGH); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState()); // C2

        assertEquals(PinState.HIGH, ic7482.getPin(1).getState());
        assertEquals(PinState.LOW, ic7482.getPin(12).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forHighC_case10() {
        // given
        ic7482.getPin(5).setState(PinState.HIGH); // C0

        ic7482.getPin(2).setState(PinState.HIGH); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.HIGH); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState()); // C2

        assertEquals(PinState.LOW, ic7482.getPin(1).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(12).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forHighC_case11() {
        // given
        ic7482.getPin(5).setState(PinState.HIGH); // C0

        ic7482.getPin(2).setState(PinState.LOW); // A1
        ic7482.getPin(3).setState(PinState.HIGH); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.HIGH); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState()); // C2

        assertEquals(PinState.LOW, ic7482.getPin(1).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(12).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forHighC_case12() {
        // given
        ic7482.getPin(5).setState(PinState.HIGH); // C0

        ic7482.getPin(2).setState(PinState.HIGH); // A1
        ic7482.getPin(3).setState(PinState.HIGH); // B1
        ic7482.getPin(14).setState(PinState.HIGH); // A2
        ic7482.getPin(13).setState(PinState.HIGH); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertEquals(3, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState()); // E1
        assertEquals(12, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState()); // E2
        assertEquals(10, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState()); // C2

        assertEquals(PinState.HIGH, ic7482.getPin(1).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(12).getState());
        assertEquals(PinState.HIGH, ic7482.getPin(10).getState());
    }

    @Test
    void testSimulateWithInput_forLowC_unknownInputsPresent() {
        // given
        ic7482.getPin(5).setState(PinState.LOW); // C0

        ic7482.getPin(2).setState(PinState.UNKNOWN); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.LOW); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertThat(outputPins).hasSize(3);
        assertThat(outputPins).extracting("state").containsOnly(PinState.UNKNOWN);
    }

    @Test
    void testSimulateWithInput_forHighC_unknownInputsPresent() {
        // given
        ic7482.getPin(5).setState(PinState.HIGH); // C0

        ic7482.getPin(2).setState(PinState.LOW); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.UNKNOWN); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertThat(outputPins).hasSize(3);
        assertThat(outputPins).extracting("state").containsOnly(PinState.UNKNOWN);
    }

    @Test
    void testSimulateWithInput_forUnknownC() {
        // given
        ic7482.getPin(5).setState(PinState.UNKNOWN); // C0

        ic7482.getPin(2).setState(PinState.LOW); // A1
        ic7482.getPin(3).setState(PinState.LOW); // B1
        ic7482.getPin(14).setState(PinState.LOW); // A2
        ic7482.getPin(13).setState(PinState.LOW); // B2
        // when
        ic7482.simulate();
        ic7482.step();
        // then
        var outputPins = ic7482.getOutputPins();
        assertThat(outputPins).hasSize(3);
        assertThat(outputPins).extracting("state").containsOnly(PinState.UNKNOWN);
    }
}
