package org.example.logicmatrix.demultiplexer;

import org.example.logicmatrix.LogicMatrix;
import org.example.edu.uj.po.simulation.interfaces.PinState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IC74138LogicMatrix extends LogicMatrix {

    @Override
    public void checkInputsValid(List<PinState> input) {
        if (input.size() != 6) {
            throw new IllegalArgumentException("IC74138LogicMatrix requires 6 input pins");
        }
    }

    @Override
    public List<PinState> map(List<PinState> input) {
        PinState notG2 = getNotG2Input(input.get(3), input.get(4));
        PinState a = input.get(0);
        PinState b = input.get(1);
        PinState c = input.get(2);
        PinState g1 = input.get(5);
        if (notG2.equals(PinState.HIGH)) {
            return simulateWhenNotG2High();
        }
        if (g1.equals(PinState.LOW)) {
            return simulateWhenG1Low();
        }
        if (notG2.equals(PinState.LOW) && g1.equals(PinState.HIGH)) {
            return simulateWhenNotG2LowAndG1High(a, b, c);
        }
        return new ArrayList<>(Collections.nCopies(8, PinState.UNKNOWN));
    }

    private List<PinState> simulateWhenNotG2LowAndG1High(PinState a, PinState b, PinState c) {
        List<PinState> output = new ArrayList<>(Collections.nCopies(8, PinState.HIGH));
        if (a.equals(PinState.LOW) && b.equals(PinState.LOW) && c.equals(PinState.LOW)) {
            output.set(0, PinState.LOW);
        } else if (a.equals(PinState.HIGH) && b.equals(PinState.LOW) && c.equals(PinState.LOW)) {
            output.set(1, PinState.LOW);
        } else if (a.equals(PinState.LOW) && b.equals(PinState.HIGH) && c.equals(PinState.LOW)) {
            output.set(2, PinState.LOW);
        } else if (a.equals(PinState.HIGH) && b.equals(PinState.HIGH) && c.equals(PinState.LOW)) {
            output.set(3, PinState.LOW);
        } else if (a.equals(PinState.LOW) && b.equals(PinState.LOW) && c.equals(PinState.HIGH)) {
            output.set(4, PinState.LOW);
        } else if (a.equals(PinState.HIGH) && b.equals(PinState.LOW) && c.equals(PinState.HIGH)) {
            output.set(5, PinState.LOW);
        } else if (a.equals(PinState.LOW) && b.equals(PinState.HIGH) && c.equals(PinState.HIGH)) {
            output.set(6, PinState.LOW);
        } else if (a.equals(PinState.HIGH) && b.equals(PinState.HIGH) && c.equals(PinState.HIGH)) {
            output.set(7, PinState.LOW);
        }
        return output;
    }

    private List<PinState> simulateWhenNotG2High() {
        return Collections.nCopies(8, PinState.HIGH);
    }

    private List<PinState> simulateWhenG1Low() {
        return Collections.nCopies(8, PinState.HIGH);
    }

    private PinState getNotG2Input(PinState notG2A, PinState notG2B) {
        return simulateAnd2(notG2A, notG2B);
    }
}
