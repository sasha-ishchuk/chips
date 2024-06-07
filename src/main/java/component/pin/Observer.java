package component.pin;

import component.records.ConnectedPinsWithStates;
import edu.uj.po.simulation.interfaces.PinState;

import java.util.List;

public interface Observer {
//    void updatePinStates(List<ConnectedPinsWithStates> connectedPinsWithStates);


    void update(PinState state);
}
