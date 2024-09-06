package component.pinheader;

import org.example.Simulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.component.PinHeaderComponent;
import org.example.component.pin.PinType;
import org.example.component.pinheader.in.InPinHeaderCreator;
import org.example.component.pinheader.out.OutPinHeaderCreator;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.edu.uj.po.simulation.interfaces.UserInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PinHeaderTest {
    UserInterface userInterface;

    @BeforeEach
    public void setUp() {
        userInterface = new Simulation();
    }

    @Test
    public void testInPinHeader() {
        PinHeaderComponent inPinHeader = new InPinHeaderCreator().createPinHeader(3);
        inPinHeader.getPin(1).setState(PinState.HIGH);
        inPinHeader.getPin(2).setState(PinState.LOW);
        inPinHeader.getPin(3).setState(PinState.HIGH);
        assertEquals(3, inPinHeader.getSize());
        assertEquals(PinType.OUT, inPinHeader.getPin(1).getType());
        assertEquals(PinType.OUT, inPinHeader.getPin(2).getType());
        assertEquals(PinType.OUT, inPinHeader.getPin(3).getType());
        assertEquals(PinState.HIGH, inPinHeader.getPin(1).getState());
        assertEquals(PinState.LOW, inPinHeader.getPin(2).getState());
        assertEquals(PinState.HIGH, inPinHeader.getPin(3).getState());
    }

    @Test
    public void testOutPinHeader() {
        PinHeaderComponent outPinHeader = new OutPinHeaderCreator().createPinHeader(3);
        outPinHeader.getPin(1).setState(PinState.HIGH);
        outPinHeader.getPin(2).setState(PinState.LOW);
        outPinHeader.getPin(3).setState(PinState.HIGH);
        assertEquals(3, outPinHeader.getSize());
        assertEquals(PinType.IN, outPinHeader.getPin(1).getType());
        assertEquals(PinType.IN, outPinHeader.getPin(2).getType());
        assertEquals(PinType.IN, outPinHeader.getPin(3).getType());
        assertEquals(PinState.HIGH, outPinHeader.getPin(1).getState());
        assertEquals(PinState.LOW, outPinHeader.getPin(2).getState());
        assertEquals(PinState.HIGH, outPinHeader.getPin(3).getState());
    }
}
