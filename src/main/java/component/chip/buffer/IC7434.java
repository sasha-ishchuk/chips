package component.chip.buffer;

import component.chip.ChipType;
import component.pin.Pin;
import component.pin.PinType;
import component.ChipComponent;
import component.ComponentType;
import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.LogicMatrix;
import logicmatrix.buffer.IC7434LogicMatrix;

import java.util.ArrayList;
import java.util.List;

public class IC7434 extends ChipComponent {

    private int id;
    private ComponentType componentType;
    private List<Pin> pins = new ArrayList<>();

    private final ChipType chipType;
    private final LogicMatrix logicMatrix;

    public IC7434() {
        this.pins = setDefaultPins();
        this.logicMatrix = new IC7434LogicMatrix();
        this.chipType = ChipType.IC7434;
    }

    @Override
    public List<Pin> setDefaultPins() {
        List<Pin> defaultPins = new ArrayList<>();
        defaultPins.add(new Pin(1, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new Pin(2, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new Pin(3, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new Pin(4, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new Pin(5, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new Pin(6, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new Pin(8, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new Pin(9, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new Pin(10, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new Pin(11, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new Pin(12, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new Pin(13, PinState.UNKNOWN, PinType.IN));
        return defaultPins;
    }

    @Override
    public Pin getPin(int pinId) {
        return pins.stream()
                .filter(pin -> pin.getId() == pinId)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public LogicMatrix getLogicMatrix() {
        return this.logicMatrix;
    }

    @Override
    public List<Pin> getPins() {
        return this.pins;
    }

    public ChipType getChipType() {
        return this.chipType;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public ComponentType getComponentType() {
        return this.componentType;
    }

    @Override
    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }
}
