package logicmatrix.decoder;

import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.LogicMatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IC7442LogicMatrix extends LogicMatrix {
    @Override
    public List<PinState> map(List<PinState> input) {
        // 0-D, 1-C, 2-B, 3-A
        return yehor(input.get(3), input.get(2), input.get(1), input.get(0));
    }

    private List<PinState> yehor(PinState a, PinState b, PinState c, PinState d) {
        List<PinState> outputs = new ArrayList<>(List.of(PinState.HIGH, PinState.HIGH, PinState.HIGH, PinState.HIGH,
                PinState.HIGH, PinState.HIGH, PinState.HIGH, PinState.HIGH, PinState.HIGH, PinState.HIGH));

        int decimalValue = 0;
        if (a == PinState.HIGH) decimalValue += 1;
        if (b == PinState.HIGH) decimalValue += 2;
        if (c == PinState.HIGH) decimalValue += 4;
        if (d == PinState.HIGH) decimalValue += 8;

        if (decimalValue < 10) {
            outputs.set(decimalValue, PinState.LOW);
        }

        return outputs;
    }

    private List<PinState> simulateBcdDecoder(PinState a, PinState b, PinState c, PinState d) {
        // Create a list of HIGH PinStates
        List<PinState> outputs = new ArrayList<>(Collections.nCopies(10, PinState.HIGH));

        // Determine the decimal value based on the specific combination of 4 inputs
        int decimalValue = 0;

        if (a == PinState.HIGH && b == PinState.LOW && c == PinState.LOW && d == PinState.LOW) {
            decimalValue = 1;
        } else if (a == PinState.LOW && b == PinState.HIGH && c == PinState.LOW && d == PinState.LOW) {
            decimalValue = 2;
        } else if (a == PinState.HIGH && b == PinState.HIGH && c == PinState.LOW && d == PinState.LOW) {
            decimalValue = 3;
        } else if (a == PinState.LOW && b == PinState.LOW && c == PinState.HIGH && d == PinState.LOW) {
            decimalValue = 4;
        } else if (a == PinState.HIGH && b == PinState.LOW && c == PinState.HIGH && d == PinState.LOW) {
            decimalValue = 5;
        } else if (a == PinState.LOW && b == PinState.HIGH && c == PinState.HIGH && d == PinState.LOW) {
            decimalValue = 6;
        } else if (a == PinState.HIGH && b == PinState.HIGH && c == PinState.HIGH && d == PinState.LOW) {
            decimalValue = 7;
        } else if (a == PinState.LOW && b == PinState.LOW && c == PinState.LOW && d == PinState.HIGH) {
            decimalValue = 8;
        } else if (a == PinState.HIGH && b == PinState.LOW && c == PinState.LOW && d == PinState.HIGH) {
            decimalValue = 9;
        }

        // If the calculated value is within the range, set the corresponding output to LOW
        if (decimalValue < outputs.size()) {
            outputs.set(decimalValue, PinState.LOW);
        }

        return outputs;
    }
}
