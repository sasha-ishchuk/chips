//package yehortest;
//
//import component.chip.nand.IC7400;
//import edu.uj.po.simulation.interfaces.PinState;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class Chip7400Test {
//
//    private IC7400 chip;
//
//    @BeforeEach
//    public void setUp() {
//        chip = new IC7400();
//    }
//
//
//    public void simulateAndApplyNextState(){
//        chip.simulate();
//    }
//
//    @Test
//    void testAllInputsLow() {
//        chip.getPin(1).setState(PinState.LOW);  // 1A
//        chip.setInputState(2, PinState.LOW);  // 1B
//        chip.setInputState(4, PinState.LOW);  // 2A
//        chip.setInputState(5, PinState.LOW);  // 2B
//        chip.setInputState(9, PinState.LOW);  // 3A
//        chip.setInputState(10, PinState.LOW); // 3B
//        chip.setInputState(12, PinState.LOW); // 4A
//        chip.setInputState(13, PinState.LOW); // 4B
//
//        simulateAndApplyNextState();
//
//        assertEquals(PinState.HIGH, chip.getOutputState(3));  // 1Y
//        assertEquals(PinState.HIGH, chip.getOutputState(6));  // 2Y
//        assertEquals(PinState.HIGH, chip.getOutputState(8));  // 3Y
//        assertEquals(PinState.HIGH, chip.getOutputState(11)); // 4Y
//    }
//
//    @Test
//    void testAllInputsHigh() {
//        chip.setInputState(1, PinState.HIGH);  // 1A
//        chip.setInputState(2, PinState.HIGH);  // 1B
//        chip.setInputState(4, PinState.HIGH);  // 2A
//        chip.setInputState(5, PinState.HIGH);  // 2B
//        chip.setInputState(9, PinState.HIGH);  // 3A
//        chip.setInputState(10, PinState.HIGH); // 3B
//        chip.setInputState(12, PinState.HIGH); // 4A
//        chip.setInputState(13, PinState.HIGH); // 4B
//
//        simulateAndApplyNextState();
//
//        assertEquals(PinState.LOW, chip.getOutputState(3));  // 1Y
//        assertEquals(PinState.LOW, chip.getOutputState(6));  // 2Y
//        assertEquals(PinState.LOW, chip.getOutputState(8));  // 3Y
//        assertEquals(PinState.LOW, chip.getOutputState(11)); // 4Y
//    }
//
//    @Test
//    void testMixedInputs() {
//        chip.setInputState(1, PinState.LOW);   // 1A
//        chip.setInputState(2, PinState.HIGH);  // 1B
//        chip.setInputState(4, PinState.HIGH);  // 2A
//        chip.setInputState(5, PinState.LOW);   // 2B
//        chip.setInputState(9, PinState.LOW);   // 3A
//        chip.setInputState(10, PinState.HIGH); // 3B
//        chip.setInputState(12, PinState.HIGH); // 4A
//        chip.setInputState(13, PinState.LOW);  // 4B
//
//        simulateAndApplyNextState();
//
//        assertEquals(PinState.HIGH, chip.getOutputState(3));  // 1Y
//        assertEquals(PinState.HIGH, chip.getOutputState(6));  // 2Y
//        assertEquals(PinState.HIGH, chip.getOutputState(8));  // 3Y
//        assertEquals(PinState.HIGH, chip.getOutputState(11)); // 4Y
//    }
//
//    @Test
//    void testMixedInputsWithUnknownState() {
//        chip.setInputState(1, PinState.LOW);   // 1A
//        chip.setInputState(2, PinState.HIGH);  // 1B
//        chip.setInputState(4, PinState.HIGH);  // 2A
//        chip.setInputState(5, PinState.UNKNOWN);   // 2B
//        chip.setInputState(9, PinState.LOW);   // 3A
//        chip.setInputState(10, PinState.UNKNOWN); // 3B
//
//        simulateAndApplyNextState();
//
//        assertEquals(PinState.HIGH, chip.getOutputState(3));  // 1Y
//        assertEquals(PinState.UNKNOWN, chip.getOutputState(6));  // 2Y
//        assertEquals(PinState.UNKNOWN, chip.getOutputState(8));  // 3Y
//        assertEquals(PinState.UNKNOWN, chip.getOutputState(11)); // 4Y
//    }
//}