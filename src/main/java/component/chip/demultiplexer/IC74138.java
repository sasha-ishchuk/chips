package component.chip.demultiplexer;

import component.ComponentType;
import component.chip.ChipType;
import component.pin.Pin;
import component.pin.PinType;
import component.ChipComponent;
import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.LogicMatrix;
import logicmatrix.demultiplexer.IC74138LogicMatrix;

import java.util.ArrayList;
import java.util.List;

// decoder from 3 to 8
public class IC74138 extends ChipComponent {
    private int id;
    private ComponentType componentType;
    private List<Pin> pins = new ArrayList<>();

    private final ChipType chipType;
    private final LogicMatrix logicMatrix;

    public IC74138() {
        this.pins = setDefaultPins();
        this.logicMatrix = new IC74138LogicMatrix();
        this.chipType = ChipType.IC74138;
    }

    @Override
    public List<Pin> setDefaultPins() {
        List<Pin> defaultPins = new ArrayList<>();
        defaultPins.add(new Pin(1, PinState.UNKNOWN, PinType.IN)); // A
        defaultPins.add(new Pin(2, PinState.UNKNOWN, PinType.IN)); // B
        defaultPins.add(new Pin(3, PinState.UNKNOWN, PinType.IN)); // C
        defaultPins.add(new Pin(4, PinState.UNKNOWN, PinType.IN)); // ~G2A
        defaultPins.add(new Pin(5, PinState.UNKNOWN, PinType.IN)); // ~G2B
        defaultPins.add(new Pin(6, PinState.UNKNOWN, PinType.IN)); // G1

        defaultPins.add(new Pin(15, PinState.UNKNOWN, PinType.OUT)); // ~Y0
        defaultPins.add(new Pin(14, PinState.UNKNOWN, PinType.OUT)); // ~Y1
        defaultPins.add(new Pin(13, PinState.UNKNOWN, PinType.OUT)); // ~Y2
        defaultPins.add(new Pin(12, PinState.UNKNOWN, PinType.OUT)); // ~Y3
        defaultPins.add(new Pin(11, PinState.UNKNOWN, PinType.OUT)); // ~Y4
        defaultPins.add(new Pin(10, PinState.UNKNOWN, PinType.OUT)); // ~Y5
        defaultPins.add(new Pin(9, PinState.UNKNOWN, PinType.OUT)); // ~Y6
        defaultPins.add(new Pin(7, PinState.UNKNOWN, PinType.OUT)); // ~Y7
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
