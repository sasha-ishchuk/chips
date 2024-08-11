package org.example.component.chip.not;

import org.example.component.ChipComponent;
import org.example.component.ComponentType;
import org.example.component.chip.ChipType;
import org.example.component.pin.InputPin;
import org.example.component.pin.OutputPin;
import org.example.component.pin.Pin;
import org.example.component.pin.PinType;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.LogicMatrix;
import org.example.logicmatrix.not.IC7404LogicMatrix;

import java.util.ArrayList;
import java.util.List;

public class IC7404 extends ChipComponent {

    private int id;
    private ComponentType componentType;
    private List<Pin> pins = new ArrayList<>();

    private final ChipType chipType;
    private final LogicMatrix logicMatrix;

    public IC7404() {
        this.pins = setDefaultPins();
        this.logicMatrix = new IC7404LogicMatrix();
        this.chipType = ChipType.IC7404;
    }

    @Override
    public List<Pin> setDefaultPins() {
        List<Pin> defaultPins = new ArrayList<>();
        defaultPins.add(new InputPin(1, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new OutputPin(2, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new InputPin(3, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new OutputPin(4, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new InputPin(5, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new OutputPin(6, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new OutputPin(8, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new InputPin(9, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new OutputPin(10, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new InputPin(11, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new OutputPin(12, PinState.UNKNOWN, PinType.OUT));
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
