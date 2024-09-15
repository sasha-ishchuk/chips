package component.chip.decoder;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.decoder.IC7442Creator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.decoder.IC7442LogicMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IC7442Test {
    private final ChipCreator creator = new IC7442Creator();
    private ChipComponent ic7442;

    @BeforeEach
    void setUp() {
        ic7442 = creator.createChip();
    }

    @Test
    void testGetPin() {
        assertNotNull(ic7442.getPin(1));
        assertEquals(PinState.UNKNOWN, ic7442.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic7442.getLogicMatrix() instanceof IC7442LogicMatrix);
    }

    @Test
    void testSimulateWithInput_forZero() {
        // given
        ic7442.getPin(15).setState(PinState.LOW); // A
        ic7442.getPin(14).setState(PinState.LOW); // B
        ic7442.getPin(13).setState(PinState.LOW); // C
        ic7442.getPin(12).setState(PinState.LOW); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState());
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());
    }

    @Test
    void testSimulateWithInput_forOne() {
        // given
        ic7442.getPin(15).setState(PinState.HIGH); // A
        ic7442.getPin(14).setState(PinState.LOW); // B
        ic7442.getPin(13).setState(PinState.LOW); // C
        ic7442.getPin(12).setState(PinState.LOW); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState());
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());
    }

    @Test
    void testSimulateWithInput_forTwo() {
        // given
        ic7442.getPin(15).setState(PinState.LOW); // A
        ic7442.getPin(14).setState(PinState.HIGH); // B
        ic7442.getPin(13).setState(PinState.LOW); // C
        ic7442.getPin(12).setState(PinState.LOW); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState());
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());
    }

    @Test
    void testSimulateWithInput_forThree() {
        // given
        ic7442.getPin(15).setState(PinState.HIGH); // A
        ic7442.getPin(14).setState(PinState.HIGH); // B
        ic7442.getPin(13).setState(PinState.LOW); // C
        ic7442.getPin(12).setState(PinState.LOW); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.LOW, outputPins.get(3).getState());
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());
    }

    @Test
    void testSimulateWithInput_forFour() {
        // given
        ic7442.getPin(15).setState(PinState.LOW); // A
        ic7442.getPin(14).setState(PinState.LOW); // B
        ic7442.getPin(13).setState(PinState.HIGH); // C
        ic7442.getPin(12).setState(PinState.LOW); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.LOW, outputPins.get(4).getState());
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());
    }

    @Test
    void testSimulateWithInput_forFive() {
        // given
        ic7442.getPin(15).setState(PinState.HIGH); // A
        ic7442.getPin(14).setState(PinState.LOW); // B
        ic7442.getPin(13).setState(PinState.HIGH); // C
        ic7442.getPin(12).setState(PinState.LOW); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.LOW, outputPins.get(5).getState());
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());
    }

    @Test
    void testSimulateWithInput_forSix() {
        // given
        ic7442.getPin(15).setState(PinState.LOW); // A
        ic7442.getPin(14).setState(PinState.HIGH); // B
        ic7442.getPin(13).setState(PinState.HIGH); // C
        ic7442.getPin(12).setState(PinState.LOW); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.LOW, outputPins.get(6).getState());
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());
    }

    @Test
    void testSimulateWithInput_forSeven() {
        // given
        ic7442.getPin(15).setState(PinState.HIGH); // A
        ic7442.getPin(14).setState(PinState.HIGH); // B
        ic7442.getPin(13).setState(PinState.HIGH); // C
        ic7442.getPin(12).setState(PinState.LOW); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.LOW, outputPins.get(7).getState());
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());
    }

    @Test
    void testSimulateWithInput_forEight() {
        // given
        ic7442.getPin(15).setState(PinState.LOW); // A
        ic7442.getPin(14).setState(PinState.LOW); // B
        ic7442.getPin(13).setState(PinState.LOW); // C
        ic7442.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.LOW, outputPins.get(8).getState());
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.HIGH, outputPins.get(9).getState());
    }

    @Test
    void testSimulateWithInput_forNine() {
        // given
        ic7442.getPin(15).setState(PinState.HIGH); // A
        ic7442.getPin(14).setState(PinState.LOW); // B
        ic7442.getPin(13).setState(PinState.LOW); // C
        ic7442.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertEquals(10, outputPins.size());
        assertEquals(1, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());
        assertEquals(2, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());
        assertEquals(3, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());
        assertEquals(4, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());
        assertEquals(5, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());
        assertEquals(6, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());
        assertEquals(7, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());
        assertEquals(9, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());
        assertEquals(10, outputPins.get(8).getId());
        assertEquals(PinState.HIGH, outputPins.get(8).getState());
        assertEquals(11, outputPins.get(9).getId());
        assertEquals(PinState.LOW, outputPins.get(9).getState());
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whichGivesTen() {
        // given
        ic7442.getPin(15).setState(PinState.LOW); // A
        ic7442.getPin(14).setState(PinState.HIGH); // B
        ic7442.getPin(13).setState(PinState.LOW); // C
        ic7442.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertThat(outputPins).hasSize(10);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whichGivesEleven() {
        // given
        ic7442.getPin(15).setState(PinState.HIGH); // A
        ic7442.getPin(14).setState(PinState.HIGH); // B
        ic7442.getPin(13).setState(PinState.LOW); // C
        ic7442.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertThat(outputPins).hasSize(10);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whichGivesTwelve() {
        // given
        ic7442.getPin(15).setState(PinState.LOW); // A
        ic7442.getPin(14).setState(PinState.LOW); // B
        ic7442.getPin(13).setState(PinState.HIGH); // C
        ic7442.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertThat(outputPins).hasSize(10);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whichGivesThirteen() {
        // given
        ic7442.getPin(15).setState(PinState.HIGH); // A
        ic7442.getPin(14).setState(PinState.LOW); // B
        ic7442.getPin(13).setState(PinState.HIGH); // C
        ic7442.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertThat(outputPins).hasSize(10);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whichGivesFourteen() {
        // given
        ic7442.getPin(15).setState(PinState.LOW); // A
        ic7442.getPin(14).setState(PinState.HIGH); // B
        ic7442.getPin(13).setState(PinState.HIGH); // C
        ic7442.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertThat(outputPins).hasSize(10);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }

    @Test
    void testSimulateWithInput_forInvalidInput_whichGivesFifteen() {
        // given
        ic7442.getPin(15).setState(PinState.HIGH); // A
        ic7442.getPin(14).setState(PinState.HIGH); // B
        ic7442.getPin(13).setState(PinState.HIGH); // C
        ic7442.getPin(12).setState(PinState.HIGH); // D
        // when
        ic7442.simulate();
        ic7442.step();
        // then
        var outputPins = ic7442.getOutputPins();
        assertThat(outputPins).hasSize(10);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }
}
