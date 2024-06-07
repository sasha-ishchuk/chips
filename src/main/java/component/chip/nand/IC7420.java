package component.chip.nand;

import component.ChipComponent;
import component.ComponentType;
import component.chip.ChipType;
import component.pin.InputPin;
import component.pin.OutputPin;
import component.pin.Pin;
import component.pin.PinType;
import edu.uj.po.simulation.interfaces.PinState;
import logicmatrix.LogicMatrix;
import logicmatrix.nand.IC7420LogicMatrix;

import java.util.ArrayList;
import java.util.List;

public class IC7420 extends ChipComponent {

    private int id;
    private ComponentType componentType;
    private List<Pin> pins = new ArrayList<>();

    private final ChipType chipType;
    private final LogicMatrix logicMatrix;

    public IC7420() {
        this.pins = setDefaultPins();
        this.logicMatrix = new IC7420LogicMatrix();
        this.chipType = ChipType.IC7420;
    }

    @Override
    public List<Pin> setDefaultPins() {
        List<Pin> defaultPins = this.getPins();
        defaultPins.add(new InputPin(1, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new InputPin(2, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new InputPin(4, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new InputPin(5, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new OutputPin(6, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new OutputPin(8, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new InputPin(9, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new InputPin(10, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new InputPin(12, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new InputPin(13, PinState.UNKNOWN, PinType.IN));
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
