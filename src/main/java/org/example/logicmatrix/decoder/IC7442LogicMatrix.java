package org.example.logicmatrix.decoder;

import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.LogicMatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IC7442LogicMatrix extends LogicMatrix {

    @Override
    public void checkInputsValid(List<PinState> input) {
        if (input.size() != 4) {
            throw new IllegalArgumentException("IC7442LogicMatrix requires 4 input pins");
        }
    }

    @Override
    public List<PinState> map(List<PinState> input) {
        // 0-D, 1-C, 2-B, 3-A
        return simulateBcdDecoder(input.get(3), input.get(2), input.get(1), input.get(0));
    }

    private List<PinState> simulateBcdDecoder(PinState a, PinState b, PinState c, PinState d) {
        List<PinState> output = new ArrayList<>(Collections.nCopies(10, PinState.HIGH));

        int decimal = 0;

        if (a == PinState.HIGH) {
            decimal += 1;
        }
        if (b == PinState.HIGH) {
            decimal += 2;
        }
        if (c == PinState.HIGH) {
            decimal += 4;
        }
        if (d == PinState.HIGH) {
            decimal += 8;
        }

        if (decimal < 10) {
            output.set(decimal, PinState.LOW);
        }

        return output;
    }
}
