package yehortest;

import component.chip.nand.IC7400;
import edu.uj.po.simulation.interfaces.PinState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

class Chip7400Test {

    private IC7400 chip;

    @BeforeEach
    public void setUp() {
        chip = new IC7400();
    }

    public void simulate(){
        chip.simulate();
    }

    @Test
    void testAllInputsLow() {
        chip.getPin(1).setState(PinState.LOW);  // 1A
        chip.getPin(2).setState(PinState.LOW);  // 1B
        chip.getPin(4).setState(PinState.LOW);  // 2A
        chip.getPin(5).setState(PinState.LOW);  // 2B
        chip.getPin(9).setState(PinState.LOW);  // 3A
        chip.getPin(10).setState(PinState.LOW); // 3B
        chip.getPin(12).setState(PinState.LOW); // 4A
        chip.getPin(13).setState(PinState.LOW); // 4B

        simulate();

        assertEquals(PinState.HIGH, chip.getPin(3).getState());  // 1Y
        assertEquals(PinState.HIGH, chip.getPin(6).getState());  // 2Y
        assertEquals(PinState.HIGH, chip.getPin(8).getState());  // 3Y
        assertEquals(PinState.HIGH, chip.getPin(11).getState()); // 4Y
    }

    @Test
    void testAllInputsHigh() {
        chip.getPin(1).setState(PinState.HIGH);  // 1A
        chip.getPin(2).setState(PinState.HIGH);  // 1B
        chip.getPin(4).setState(PinState.HIGH);  // 2A
        chip.getPin(5).setState(PinState.HIGH);  // 2B
        chip.getPin(9).setState(PinState.HIGH);  // 3A
        chip.getPin(10).setState(PinState.HIGH); // 3B
        chip.getPin(12).setState(PinState.HIGH); // 4A
        chip.getPin(13).setState(PinState.HIGH); // 4B
        simulate();

        assertEquals(PinState.LOW, chip.getPin(3).getState());  // 1Y
        assertEquals(PinState.LOW, chip.getPin(6).getState());  // 2Y
        assertEquals(PinState.LOW, chip.getPin(8).getState());  // 3Y
        assertEquals(PinState.LOW, chip.getPin(11).getState()); // 4Y
    }

    @Test
    void testMixedInputs() {
        chip.getPin(1).setState(PinState.LOW);  // 1A
        chip.getPin(2).setState(PinState.HIGH);  // 1B
        chip.getPin(4).setState(PinState.HIGH);  // 2A
        chip.getPin(5).setState(PinState.LOW);  // 2B
        chip.getPin(9).setState(PinState.LOW);  // 3A
        chip.getPin(10).setState(PinState.HIGH); // 3B
        chip.getPin(12).setState(PinState.HIGH); // 4A
        chip.getPin(13).setState(PinState.LOW); // 4B

        simulate();

        assertEquals(PinState.HIGH, chip.getPin(3).getState());  // 1Y
        assertEquals(PinState.HIGH, chip.getPin(6).getState());  // 2Y
        assertEquals(PinState.HIGH, chip.getPin(8).getState());  // 3Y
        assertEquals(PinState.HIGH, chip.getPin(11).getState()); // 4Y
    }

    @Test
    void testMixedInputsWithUnknownState() {
        chip.getPin(1).setState(PinState.LOW);  // 1A
        chip.getPin(2).setState(PinState.HIGH);  // 1B
        chip.getPin(4).setState(PinState.HIGH);  // 2A
        chip.getPin(5).setState(PinState.UNKNOWN);  // 2B
        chip.getPin(9).setState(PinState.LOW);  // 3A
        chip.getPin(10).setState(PinState.UNKNOWN); // 3B

        simulate();

        assertEquals(PinState.HIGH, chip.getPin(3).getState());  // 1Y
        assertEquals(PinState.UNKNOWN, chip.getPin(6).getState());  // 2Y
        assertEquals(PinState.UNKNOWN, chip.getPin(8).getState());  // 3Y
        assertEquals(PinState.UNKNOWN, chip.getPin(11).getState()); // 4Y
    }
}