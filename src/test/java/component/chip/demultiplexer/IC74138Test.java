package component.chip.demultiplexer;

import org.example.component.ChipComponent;
import org.example.component.chip.ChipCreator;
import org.example.component.chip.demultiplexer.IC74138Creator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.demultiplexer.IC74138LogicMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IC74138Test {
    private final ChipCreator creator = new IC74138Creator();
    private ChipComponent ic74138;

    @BeforeEach
    void setUp() {
        ic74138 = creator.createChip();
    }

    @Test
    void testGetPin() {
        assertNotNull(ic74138.getPin(1));
        assertEquals(PinState.UNKNOWN, ic74138.getPin(1).getState());
    }

    @Test
    void testGetLogicMatrix() {
        assertTrue(ic74138.getLogicMatrix() instanceof IC74138LogicMatrix);
    }

    @Test
    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateZero() {
        // given
        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
        ic74138.getPin(6).setState(PinState.HIGH);  // G1

        ic74138.getPin(1).setState(PinState.LOW); // A
        ic74138.getPin(2).setState(PinState.LOW); // B
        ic74138.getPin(3).setState(PinState.LOW); // C
        // when
        ic74138.simulate();
        ic74138.step();
        // then
        var outputPins = ic74138.getOutputPins();
        assertEquals(8, outputPins.size());
        assertEquals(15, outputPins.get(0).getId());
        assertEquals(PinState.LOW, outputPins.get(0).getState());   // ~Y0
        assertEquals(14, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // ~Y1
        assertEquals(13, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // ~Y2
        assertEquals(12, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // ~Y3
        assertEquals(11, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // ~Y4
        assertEquals(10, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // ~Y5
        assertEquals(9, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // ~Y6
        assertEquals(7, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // ~Y7
    }

    @Test
    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateOne() {
        // given
        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
        ic74138.getPin(6).setState(PinState.HIGH);  // G1

        ic74138.getPin(1).setState(PinState.HIGH); // A
        ic74138.getPin(2).setState(PinState.LOW); // B
        ic74138.getPin(3).setState(PinState.LOW); // C
        // when
        ic74138.simulate();
        ic74138.step();
        // then
        var outputPins = ic74138.getOutputPins();
        assertEquals(8, outputPins.size());
        assertEquals(15, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // ~Y0
        assertEquals(14, outputPins.get(1).getId());
        assertEquals(PinState.LOW, outputPins.get(1).getState());  // ~Y1
        assertEquals(13, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // ~Y2
        assertEquals(12, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // ~Y3
        assertEquals(11, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // ~Y4
        assertEquals(10, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // ~Y5
        assertEquals(9, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // ~Y6
        assertEquals(7, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // ~Y7
    }

    @Test
    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateTwo() {
        // given
        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
        ic74138.getPin(6).setState(PinState.HIGH);  // G1

        ic74138.getPin(1).setState(PinState.LOW); // A
        ic74138.getPin(2).setState(PinState.HIGH); // B
        ic74138.getPin(3).setState(PinState.LOW); // C
        // when
        ic74138.simulate();
        ic74138.step();
        // then
        var outputPins = ic74138.getOutputPins();
        assertEquals(8, outputPins.size());
        assertEquals(15, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // ~Y0
        assertEquals(14, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // ~Y1
        assertEquals(13, outputPins.get(2).getId());
        assertEquals(PinState.LOW, outputPins.get(2).getState());  // ~Y2
        assertEquals(12, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // ~Y3
        assertEquals(11, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // ~Y4
        assertEquals(10, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // ~Y5
        assertEquals(9, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // ~Y6
        assertEquals(7, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // ~Y7
    }

    @Test
    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateThree() {
        // given
        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
        ic74138.getPin(6).setState(PinState.HIGH);  // G1

        ic74138.getPin(1).setState(PinState.HIGH); // A
        ic74138.getPin(2).setState(PinState.HIGH); // B
        ic74138.getPin(3).setState(PinState.LOW); // C
        // when
        ic74138.simulate();
        ic74138.step();
        // then
        var outputPins = ic74138.getOutputPins();
        assertEquals(8, outputPins.size());
        assertEquals(15, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // ~Y0
        assertEquals(14, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // ~Y1
        assertEquals(13, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // ~Y2
        assertEquals(12, outputPins.get(3).getId());
        assertEquals(PinState.LOW, outputPins.get(3).getState());  // ~Y3
        assertEquals(11, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // ~Y4
        assertEquals(10, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // ~Y5
        assertEquals(9, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // ~Y6
        assertEquals(7, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // ~Y7
    }

    @Test
    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateFour() {
        // given
        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
        ic74138.getPin(6).setState(PinState.HIGH);  // G1

        ic74138.getPin(1).setState(PinState.LOW); // A
        ic74138.getPin(2).setState(PinState.LOW); // B
        ic74138.getPin(3).setState(PinState.HIGH); // C
        // when
        ic74138.simulate();
        ic74138.step();
        // then
        var outputPins = ic74138.getOutputPins();
        assertEquals(8, outputPins.size());
        assertEquals(15, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // ~Y0
        assertEquals(14, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // ~Y1
        assertEquals(13, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // ~Y2
        assertEquals(12, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // ~Y3
        assertEquals(11, outputPins.get(4).getId());
        assertEquals(PinState.LOW, outputPins.get(4).getState());  // ~Y4
        assertEquals(10, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // ~Y5
        assertEquals(9, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // ~Y6
        assertEquals(7, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // ~Y7
    }

    @Test
    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateFive() {
        // given
        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
        ic74138.getPin(6).setState(PinState.HIGH);  // G1

        ic74138.getPin(1).setState(PinState.HIGH); // A
        ic74138.getPin(2).setState(PinState.LOW); // B
        ic74138.getPin(3).setState(PinState.HIGH); // C
        // when
        ic74138.simulate();
        ic74138.step();
        // then
        var outputPins = ic74138.getOutputPins();
        assertEquals(8, outputPins.size());
        assertEquals(15, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // ~Y0
        assertEquals(14, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // ~Y1
        assertEquals(13, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // ~Y2
        assertEquals(12, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // ~Y3
        assertEquals(11, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // ~Y4
        assertEquals(10, outputPins.get(5).getId());
        assertEquals(PinState.LOW, outputPins.get(5).getState());  // ~Y5
        assertEquals(9, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // ~Y6
        assertEquals(7, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // ~Y7
    }

    @Test
    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateSix() {
        // given
        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
        ic74138.getPin(6).setState(PinState.HIGH);  // G1

        ic74138.getPin(1).setState(PinState.LOW); // A
        ic74138.getPin(2).setState(PinState.HIGH); // B
        ic74138.getPin(3).setState(PinState.HIGH); // C
        // when
        ic74138.simulate();
        ic74138.step();
        // then
        var outputPins = ic74138.getOutputPins();
        assertEquals(8, outputPins.size());
        assertEquals(15, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // ~Y0
        assertEquals(14, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // ~Y1
        assertEquals(13, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // ~Y2
        assertEquals(12, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // ~Y3
        assertEquals(11, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // ~Y4
        assertEquals(10, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // ~Y5
        assertEquals(9, outputPins.get(6).getId());
        assertEquals(PinState.LOW, outputPins.get(6).getState());  // ~Y6
        assertEquals(7, outputPins.get(7).getId());
        assertEquals(PinState.HIGH, outputPins.get(7).getState());  // ~Y7
    }

    @Test
    void testSimulateWithInput_whenG1IsHighAndNotG2IsLow_givesLowStateSeven() {
        // given
        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
        ic74138.getPin(5).setState(PinState.LOW); // ~G2B
        ic74138.getPin(6).setState(PinState.HIGH);  // G1

        ic74138.getPin(1).setState(PinState.HIGH); // A
        ic74138.getPin(2).setState(PinState.HIGH); // B
        ic74138.getPin(3).setState(PinState.HIGH); // C
        // when
        ic74138.simulate();
        ic74138.step();
        // then
        var outputPins = ic74138.getOutputPins();
        assertEquals(8, outputPins.size());
        assertEquals(15, outputPins.get(0).getId());
        assertEquals(PinState.HIGH, outputPins.get(0).getState());   // ~Y0
        assertEquals(14, outputPins.get(1).getId());
        assertEquals(PinState.HIGH, outputPins.get(1).getState());  // ~Y1
        assertEquals(13, outputPins.get(2).getId());
        assertEquals(PinState.HIGH, outputPins.get(2).getState());  // ~Y2
        assertEquals(12, outputPins.get(3).getId());
        assertEquals(PinState.HIGH, outputPins.get(3).getState());  // ~Y3
        assertEquals(11, outputPins.get(4).getId());
        assertEquals(PinState.HIGH, outputPins.get(4).getState());  // ~Y4
        assertEquals(10, outputPins.get(5).getId());
        assertEquals(PinState.HIGH, outputPins.get(5).getState());  // ~Y5
        assertEquals(9, outputPins.get(6).getId());
        assertEquals(PinState.HIGH, outputPins.get(6).getState());  // ~Y6
        assertEquals(7, outputPins.get(7).getId());
        assertEquals(PinState.LOW, outputPins.get(7).getState());  // ~Y7
    }

    @Test
    void testSimulateWithInput_whenNotG2IsHigh_returnAllStatesHigh() {
        // given
        ic74138.getPin(1).setState(PinState.HIGH); // A - could be HIGH or LOW
        ic74138.getPin(2).setState(PinState.LOW); // B - could be HIGH or LOW
        ic74138.getPin(3).setState(PinState.LOW); // C - could be HIGH or LOW
        ic74138.getPin(6).setState(PinState.HIGH);  // G1 - could be HIGH or LOW

        // it gives us notG2 = HIGH (else it gives us notG2 = LOW)
        ic74138.getPin(4).setState(PinState.HIGH); // ~G2A
        ic74138.getPin(5).setState(PinState.HIGH); // ~G2B
        // when
        ic74138.simulate();
        ic74138.step();
        // then
        var outputPins = ic74138.getOutputPins();
        assertThat(outputPins).hasSize(8);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }

    @Test
    void testSimulateWithInput_whenG1IsLow_returnAllStatesHigh() {
        // given
        ic74138.getPin(1).setState(PinState.HIGH); // A - could be HIGH or LOW
        ic74138.getPin(2).setState(PinState.LOW); // B - could be HIGH or LOW
        ic74138.getPin(3).setState(PinState.LOW); // C - could be HIGH or LOW
        // when notG2A and notG2B are HIGH it'll give us notG2 = HIGH, else notG2 = LOW
        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
        ic74138.getPin(5).setState(PinState.HIGH); // ~G2B

        ic74138.getPin(6).setState(PinState.LOW);  // G1
        // when
        ic74138.simulate();
        ic74138.step();
        // then
        var outputPins = ic74138.getOutputPins();
        assertThat(outputPins).hasSize(8);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }

    @Test
    void testSimulateWithInput_whenNotG2IsUnknown_returnAllStatesUnknown() {
        // given
        ic74138.getPin(1).setState(PinState.HIGH); // A - could be HIGH or LOW
        ic74138.getPin(2).setState(PinState.LOW); // B - could be HIGH or LOW
        ic74138.getPin(3).setState(PinState.LOW); // C - could be HIGH or LOW
        ic74138.getPin(6).setState(PinState.HIGH);  // G1 - could be HIGH or LOW

        ic74138.getPin(4).setState(PinState.UNKNOWN); // ~G2A
        ic74138.getPin(5).setState(PinState.HIGH); // ~G2B
        // when
        ic74138.simulate();
        ic74138.step();
        // then
        var outputPins = ic74138.getOutputPins();
        assertThat(outputPins).hasSize(8);
        assertThat(outputPins).extracting("state").containsOnly(PinState.UNKNOWN);
    }

    @Test
    void testSimulateWithInput_whenG1IsUnknown_returnAllStatesUnknown() {
        // given
        ic74138.getPin(1).setState(PinState.HIGH); // A - could be HIGH or LOW
        ic74138.getPin(2).setState(PinState.LOW); // B - could be HIGH or LOW
        ic74138.getPin(3).setState(PinState.LOW); // C - could be HIGH or LOW
        ic74138.getPin(6).setState(PinState.UNKNOWN);  // G1 - could be HIGH or LOW

        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
        ic74138.getPin(5).setState(PinState.HIGH); // ~G2B
        // when
        ic74138.simulate();
        ic74138.step();
        // then
        var outputPins = ic74138.getOutputPins();
        assertThat(outputPins).hasSize(8);
        assertThat(outputPins).extracting("state").containsOnly(PinState.UNKNOWN);
    }

    @Test
    void testSimulateWithInput_whenOneOfABCUnknown_returnAllStatesHigh() {
        // given
        ic74138.getPin(1).setState(PinState.UNKNOWN); // A - could be HIGH or LOW
        ic74138.getPin(2).setState(PinState.LOW); // B - could be HIGH or LOW
        ic74138.getPin(3).setState(PinState.LOW); // C - could be HIGH or LOW
        ic74138.getPin(6).setState(PinState.HIGH);  // G1 - could be HIGH or LOW

        ic74138.getPin(4).setState(PinState.LOW); // ~G2A
        ic74138.getPin(5).setState(PinState.HIGH); // ~G2B
        // when
        ic74138.simulate();
        ic74138.step();
        // then
        var outputPins = ic74138.getOutputPins();
        assertThat(outputPins).hasSize(8);
        assertThat(outputPins).extracting("state").containsOnly(PinState.HIGH);
    }
}
