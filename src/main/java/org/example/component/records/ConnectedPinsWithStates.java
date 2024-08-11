package org.example.component.records;

import org.example.edu.uj.po.simulation.interfaces.PinState;

public record ConnectedPinsWithStates(int componentId1, int pinId1, PinState state1,
                                      int componentId2, int pinId2, PinState state2) {
}