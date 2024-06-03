package component.chip.adder;

import component.ComponentType;
import component.chip.ChipType;
import component.pin.Pin;
import component.pin.PinType;
import component.ChipComponent;
import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.LogicMatrix;
import logicmatrix.adder.IC7482LogicMatrix;

import java.util.ArrayList;
import java.util.List;

public class IC7482 extends ChipComponent {

    private int id;
    private ComponentType componentType;
    private List<Pin> pins = new ArrayList<>();

    private final ChipType chipType;
    private final LogicMatrix logicMatrix;

    public IC7482() {
        this.pins = setDefaultPins();
        this.logicMatrix = new IC7482LogicMatrix();
        this.chipType = ChipType.IC7482;
    }

    @Override
    public List<Pin> setDefaultPins() {
        List<Pin> defaultPins = new ArrayList<>();
        defaultPins.add(new Pin(2, PinState.UNKNOWN, PinType.IN)); // A1
        defaultPins.add(new Pin(3, PinState.UNKNOWN, PinType.IN)); // B1
        defaultPins.add(new Pin(5, PinState.UNKNOWN, PinType.IN)); // C0
        defaultPins.add(new Pin(13, PinState.UNKNOWN, PinType.IN)); // B2
        defaultPins.add(new Pin(14, PinState.UNKNOWN, PinType.IN)); // A2

        defaultPins.add(new Pin(1, PinState.UNKNOWN, PinType.OUT)); // E1
        defaultPins.add(new Pin(12, PinState.UNKNOWN, PinType.OUT)); // E2
        defaultPins.add(new Pin(10, PinState.UNKNOWN, PinType.OUT)); // C2
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

    @Override
    public ComponentType getComponentType() {
        return this.componentType;
    }

    @Override
    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
