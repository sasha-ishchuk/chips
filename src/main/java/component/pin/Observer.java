package component.pin;

import edu.uj.po.simulation.interfaces.PinState;

public interface Observer {
        void update(PinState state);
}
