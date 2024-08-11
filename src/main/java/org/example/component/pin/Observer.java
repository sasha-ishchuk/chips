package org.example.component.pin;

import org.example.edu.uj.po.simulation.interfaces.PinState;

public interface Observer {
        void update(PinState state);
}
