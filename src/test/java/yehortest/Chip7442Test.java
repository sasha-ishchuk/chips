package yehortest;

import component.chip.decoder.IC7442;
import edu.uj.po.simulation.interfaces.PinState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

class Chip7442Test {

    private IC7442 chip;

    @BeforeEach
    public void setUp() {
        chip = new IC7442();
    }

    public void simulateAndApplyNextState(){
        chip.simulate();
        chip.step();
    }

    @Test
    void testZero() {
        
        chip.getPin(15).setState(PinState.LOW);
        chip.getPin(14).setState(PinState.LOW);
        chip.getPin(13).setState(PinState.LOW);
        chip.getPin(12).setState(PinState.LOW);
        simulateAndApplyNextState();

        assertEquals(PinState.LOW, chip.getPin(1).getState());  // 0
        assertEquals(PinState.HIGH, chip.getPin(2).getState()); // 1
        assertEquals(PinState.HIGH, chip.getPin(3).getState()); // 2
        assertEquals(PinState.HIGH, chip.getPin(4).getState()); // 3
        assertEquals(PinState.HIGH, chip.getPin(5).getState()); // 4
        assertEquals(PinState.HIGH, chip.getPin(6).getState()); // 5
        assertEquals(PinState.HIGH, chip.getPin(7).getState()); // 6
        assertEquals(PinState.HIGH, chip.getPin(9).getState()); // 7
        assertEquals(PinState.HIGH, chip.getPin(10).getState()); // 8
        assertEquals(PinState.HIGH, chip.getPin(11).getState()); // 9
    }

    @Test
    void testSix() {
        chip.getPin(15).setState(PinState.LOW);
        chip.getPin(14).setState(PinState.HIGH);
        chip.getPin(13).setState(PinState.HIGH);
        chip.getPin(12).setState(PinState.LOW);
        simulateAndApplyNextState();

        assertEquals(PinState.HIGH, chip.getPin(1).getState());  // 0
        assertEquals(PinState.HIGH, chip.getPin(2).getState());  // 1
        assertEquals(PinState.HIGH, chip.getPin(3).getState());  // 2
        assertEquals(PinState.HIGH, chip.getPin(4).getState());  // 3
        assertEquals(PinState.HIGH, chip.getPin(5).getState());  // 4
        assertEquals(PinState.HIGH, chip.getPin(6).getState());  // 5
        assertEquals(PinState.LOW, chip.getPin(7).getState());   // 6
        assertEquals(PinState.HIGH, chip.getPin(9).getState());  // 7
        assertEquals(PinState.HIGH, chip.getPin(10).getState()); // 8
        assertEquals(PinState.HIGH, chip.getPin(11).getState()); // 9
    }
}