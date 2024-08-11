package org.example.logicmatrix.multiplexer;

import org.example.logicmatrix.LogicMatrix;
import org.example.edu.uj.po.simulation.interfaces.PinState;

import java.util.ArrayList;
import java.util.List;

public class IC74152LogicMatrix extends LogicMatrix {

    @Override
    public void checkInputsValid(List<PinState> input) {
        if (input.size() != 11) {
            throw new IllegalArgumentException("IC74152LogicMatrix requires 11 input pins");
        }
    }

    @Override
    public List<PinState> map(List<PinState> input) {
        List<PinState> output = new ArrayList<>();

        PinState a = input.get(0);
        PinState b = input.get(1);
        PinState c = input.get(2);
        PinState d0 = input.get(3);
        PinState d1 = input.get(4);
        PinState d2 = input.get(5);
        PinState d3 = input.get(6);
        PinState d4 = input.get(7);
        PinState d5 = input.get(8);
        PinState d6 = input.get(9);
        PinState d7 = input.get(10);

        if (a.equals(PinState.LOW) && b.equals(PinState.LOW) && c.equals(PinState.LOW)) {
            output.add(simulateNot(d0));
        } else if (a.equals(PinState.HIGH) && b.equals(PinState.LOW) && c.equals(PinState.LOW)) {
            output.add(simulateNot(d1));
        } else if (a.equals(PinState.LOW) && b.equals(PinState.HIGH) && c.equals(PinState.LOW)) {
            output.add(simulateNot(d2));
        } else if (a.equals(PinState.HIGH) && b.equals(PinState.HIGH) && c.equals(PinState.LOW)) {
            output.add(simulateNot(d3));
        } else if (a.equals(PinState.LOW) && b.equals(PinState.LOW) && c.equals(PinState.HIGH)) {
            output.add(simulateNot(d4));
        } else if (a.equals(PinState.HIGH) && b.equals(PinState.LOW) && c.equals(PinState.HIGH)) {
            output.add(simulateNot(d5));
        } else if (a.equals(PinState.LOW) && b.equals(PinState.HIGH) && c.equals(PinState.HIGH)) {
            output.add(simulateNot(d6));
        } else if (a.equals(PinState.HIGH) && b.equals(PinState.HIGH) && c.equals(PinState.HIGH)) {
            output.add(simulateNot(d7));
        } else {
            output.add(PinState.UNKNOWN);
        }

        return output;
    }
}
